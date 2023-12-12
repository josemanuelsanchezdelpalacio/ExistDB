package Conexiones;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XQueryService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

/**********************************************************************************************************************************************
 *   APLICACIÓN: "JLC_SAM"                                                                                                                    *
 **********************************************************************************************************************************************
 *   ACCESO A DATOS - FACTORÍA DE PROYECTOS 2DAM  -  Intellij Ultimate IDE v2022.3.2                                                          *
 **********************************************************************************************************************************************
 *   @author Jorge Otin Caba, Laura Butler Gracia, Jose Manuel Sanchez del Palacio                  										  *
 *   @since 13-ENERO-2023                                                                                                                     *
 **********************************************************************************************************************************************
 *   COMENTARIOS:                                                                                                                             *
 *        - Clase ConexionExistDB.                                                                                                            *
 *        - Clase que contiene métodos para conectar con la base de datos eXist.                                                              *
 *        - Se ha utilizado la librería eXist-db-5.3.0.                                                                                       *
 *        - Se ha utilizado la librería exist-optional-5.3.0.                                                                                 *
 *        - Se ha utilizado la librería exist-core-5.3.0.                                                                                     *
 **********************************************************************************************************************************************/
public class ConexionExistDB {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String usuario = "admin";
    private static String password = "";

    //Conexión a la base de datos eXist
    public static Connection conexionExistDb() throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        //Registrar el driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);

        //Crear la colección
        Collection col = DatabaseManager.getCollection(URI + "/db", usuario, password);
        CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        col = service.createCollection("centros");

        //Crear el recurso
        Resource res = col.createResource("proyectos.xml", "XMLResource");
        Resource res1 = col.createResource("centrosFP2.xml", "XMLResource");
        Resource res2 = col.createResource("familias.xml", "XMLResource");

        //Asignar el recurso a un archivo XML
        File file = new File("target/proyectos.xml");
        File file1 = new File("target/centrosFP2.xml");
        File file2 = new File("target/familias.xml");
        res.setContent(file);
        res1.setContent(file1);
        res2.setContent(file2);
        System.out.println("Subido el archivo XML a la base de datos eXist");

        //Guardar el recurso
        col.storeResource(res);
        col.storeResource(res1);
        col.storeResource(res2);
        System.out.println("Guardado el recurso");

        //Cerrar la conexión
        col.close();
        System.out.println("Cerrada la conexión");
        return null;
    }

    public static void consultaExist() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, IOException {
        final String driver = "org.exist.xmldb.DatabaseImpl";

        // Inicializar el driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;
        // Obtener la colección
        try {
            col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/");
            XQueryService xqs = (XQueryService) col.getService("XQueryService", "1.0");
            xqs.setProperty("indent", "yes");

            //Consulta para obtener los centros
            ResourceSet resultCentros = xqs.query("<entidades>\n" +
                    "{\n" +
                    "let $x := doc(\"centros/centrosFP2.xml\")//*:root/worksheet/Row\n" +
                    "return \n" +
                    "    for $y in $x\n" +
                    "    return\n" +
                    "    <entidad>\n" +
                    "        <CODIGO>{$y/CODIGOCENTRO/text()}</CODIGO>\n" +
                    "        <NOMBRE>{$y/NOMBRECENTRO/text()}</NOMBRE>\n" +
                    "        <ID_PROFESION>1</ID_PROFESION>\n" +
                    "        <WEB>-</WEB>\n" +
                    "        <CONTACTO>-</CONTACTO>\n" +
                    "    </entidad>\n" +
                    "}\n" +
                    "</entidades>\n");

            //Consulta para obtener los proyectos
            ResourceSet resultProyecto = xqs.query("<proyectos>\n" +
                    "{\n" +
                    "let $x := doc(\"centros/proyectos.xml\")//*:root/worksheet/Row\n" +
                    "return \n" +
                    "    for $y in $x\n" +
                    "    return\n" +
                    "    <proyecto>\n" +
                    "        <TITULO>{$y/TÍTULODELPROYECTO/text()}</TITULO>\n" +
                    "        <DESCRIPCION>-</DESCRIPCION>\n" +
                    "        <ID_TAGS>1</ID_TAGS>\n" +
                    "        <ID_PROFESION></ID_PROFESION>\n" +
                    "        <VALORACION>POSITIVO</VALORACION>\n" +
                    "        <VISIBILIDAD>true</VISIBILIDAD>\n" +
                    "        <ID_ESTADO>1</ID_ESTADO>\n" +
                    "        <FECHAINICIO>{$y/fn:substring-after(AUTORIZACIÓN, 'Resolución ')}</FECHAINICIO>\n" +
                    "        <FECHAFIN>{$y/fn:substring-after(CONTINUIDAD, 'Resolución ')}</FECHAFIN>\n" +
                    "    </proyecto>\n" +
                    "}\n" +
                    "</proyectos>\n");

            //Consulta para obtener las familias profesionales
            ResourceSet resultFamilia = xqs.query("<familias_profesionales>\n" +
                    "{\n" +
                    "let $x := doc(\"centros/familias.xml\")//*:options/option\n" +
                    "return \n" +
                    "    for $y in $x\n" +
                    "    return\n" +
                    "    <familia_profesional>" +
                    "    <nombre>{$y/text()}</nombre>" +
                    "    </familia_profesional>\n" +
                    "}\n" +
                    "</familias_profesionales>\n");

            //Obtener los resultados
            ResourceIterator i = resultCentros.getIterator();
            ResourceIterator i1 = resultProyecto.getIterator();
            ResourceIterator i2 = resultFamilia.getIterator();
            Resource res = null;
            Resource res1 = null;
            Resource res2 = null;

            //Escribir los resultados en un archivo XML
            while (i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    res1 = i1.nextResource();
                    res2 = i2.nextResource();
                    FileWriter fw = new FileWriter("target/centrosFinal.xml");
                    FileWriter fw1 = new FileWriter("target/proyectosFinal.xml");
                    FileWriter fw2 = new FileWriter("target/familiasFinal.xml");
                    fw.write(res.getContent().toString());
                    fw1.write(res1.getContent().toString());
                    fw2.write(res2.getContent().toString());
                    fw.close();
                    fw1.close();
                    fw2.close();
                    System.out.println("Consulta realizada correctamente");
                } finally {
                    //dont forget to cleanup resources
                    try {
                        ((EXistResource) res).freeResources();
                        ((EXistResource) res1).freeResources();
                        ((EXistResource) res2).freeResources();
                    } catch (XMLDBException xe) {
                        System.out.println("Error al liberar los recursos");
                    }
                }
            }
        } finally {

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    System.out.println("Error al cerrar la conexión");
                }
            }
        }
    }
}