package conexion;

import libs.Leer;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;

import java.io.File;

public class ConexionExistDB {

    //driver para exist-db
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String usuario = "admin";
    private static String password = "";

    static Collection col = null;

    public static void conexionExistDb() {
        try {
            //carga del driver
            Class cl = Class.forName(driver);
            //instancia de la base de datos
            Database database = (Database) cl.newInstance();
            //registro del driver
            DatabaseManager.registerDatabase(database);

            //conexion a la coleccion
            col = DatabaseManager.getCollection(URI + "/db", usuario, password);
            //si la coleccion no existe se crea
            if(col == null){
                CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
                col = service.createCollection("/coleccionXML");
            }

            // Crear el recurso
            File file = new File(Leer.pedirCadena("src/main/resources/familias.xml"));
            File file2 = new File(Leer.pedirCadena("src/main/resources/proyectosFP.xml"));
            File centrosFile = new File(Leer.pedirCadena("target/centros.xml"));
            File proyectosFile = new File(Leer.pedirCadena("target/proyectos.xml"));

            if (!file.canRead() || !file2.canRead() || !centrosFile.canRead() || !proyectosFile.canRead()) {
                System.out.println("Error al leer el documento XML.");
            } else {
                //compruebo si es un archivo
                Resource resource = col.createResource(file.getName(), "XMLResource");
                Resource resource2 = col.createResource(file2.getName(), "XMLResource");
                Resource resource3 = col.createResource(centrosFile.getName(), "XMLResource");
                Resource resource4 = col.createResource(proyectosFile.getName(), "XMLResource");

                //asigno el recurso a un archivo XML
                resource.setContent(file);
                resource2.setContent(file2);
                resource3.setContent(centrosFile);
                resource4.setContent(proyectosFile);

                System.out.println("Subido los archivos XML a la base de datos eXist");

                //guardo el recurso
                col.storeResource(resource);
                col.storeResource(resource2);
                col.storeResource(resource3);
                col.storeResource(resource4);
                System.out.println("Guardado el recurso");
            }

            //cerro la conexion
            col.close();
            System.out.println("Cerrada la conexion");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
        } catch (InstantiationException e) {
            System.out.println("Error de instancia de BD");
        } catch (IllegalAccessException e) {
            System.out.println("Error al acceder a la base de datos");
        } catch (XMLDBException e) {
            System.out.println("Error de XMLDB en newInstance");
        }
    }
}