package Conexiones;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import java.io.File;

public class ConexionExistDB {

    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private static String URI = "xmldb:exist://localhost:8181/exist/xmlrpc/db/coleccionXML";
    private static String usuario = "admin";
    private static String password = "";

    static Collection col = null;

    public static void conexionExistDb() {
        try {
            // Carga del driver
            Class cl = Class.forName(driver);
            //instancia de la base de datos
            Database database = (Database) cl.newInstance();
            //registro del driver
            DatabaseManager.registerDatabase(database);

            //conexion a la coleccion
            col = DatabaseManager.getCollection(URI, usuario, password);
            if (col == null) {
                // Si no existe, la creamos
                CollectionManagementService service = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
                service.createCollection("coleccionXML");
            }


            // Crear el recurso
            File file = new File("src/main/resources/familias.xml");
            File file2 = new File("src/main/resources/proyectosFP.xml");
            if (!file.canRead() || !file2.canRead()) {
                System.out.println("Error al leer el documento XML.");
            } else {
                // comprobar si es un archivo
                Resource resource = col.createResource(file.getName(), "XMLResource");
                Resource resource2 = col.createResource(file2.getName(), "XMLResource");

                // Asignar el recurso a un archivo XML
                resource.setContent(file);
                resource2.setContent(file2);
                System.out.println("Subido los archivos XML a la base de datos eXist");

                // Guardar el recurso
                col.storeResource(resource);
                col.storeResource(resource2);
                System.out.println("Guardado el recurso");
            }

            // Cerrar la conexión
            col.close();
            System.out.println("Cerrada la conexión");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
        } catch (InstantiationException e) {
            System.out.println("Error de instancia de BD");
        } catch (IllegalAccessException e) {
            System.out.println("Error al acceder a la base de datos");
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }
}
