package Conexiones;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;

import java.io.File;

public class ConexionExistDB {

    //Driver para eXist-DB
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private static String URI = "xmldb:exist://localhost:8181";
    private static String usuario = "admin";
    private static String password = "";

    //colección para almacenar las consultas sobre la colección referenciada con URI
    static Collection col = null;

    //Conexión a la base de datos eXist
    public static void conexionExistDb() {
        //Carga del driver
        Class cl = null;
        //Instancia de la BD
        Database database = null;
        try {
            cl = Class.forName(driver);
            database = (Database) cl.newInstance();
            //Registro del driver
            DatabaseManager.registerDatabase(database);

            //conexion a la coleccion
            col = DatabaseManager.getCollection(URI + "/db", usuario, password);
            CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
            col = service.createCollection("xml");

            //Crear el recurso
            Resource res = col.createResource("familias.xml", "XMLResource");
            Resource res1 = col.createResource("proyectosFP.xml", "XMLResource");

            //Asignar el recurso a un archivo XML
            File file = new File("src/main/resources/familias.xml");
            File file1 = new File("src/main/resources/proyectosFP.xml");
            res.setContent(file);
            res1.setContent(file1);
            System.out.println("Subido los archivos XML a la base de datos eXist");

            //Guardar el recurso
            col.storeResource(res);
            col.storeResource(res1);
            System.out.println("Guardado el recurso");

            //Cerrar la conexión
            col.close();
            System.out.println("Cerrada la conexión");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
        } catch (InstantiationException e) {
            System.out.println("Error de instancia de BD");
        } catch (IllegalAccessException e) {
            System.out.println("Error al acceder a la base de datos");
        } catch (XMLDBException e) {
            System.out.println("Error al registrar el driver");
        }
    }
}