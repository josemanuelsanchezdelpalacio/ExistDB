import CSVtoXML.LeerCSV_Centros;
import CSVtoXML.LeerCSV_Proyectos;
import conexion.ConexionExistDB;
import libs.Leer;

public class Main {

    public static void main(String[] args) {

        boolean salir = false;
        int opcion;
        do {
            System.out.println("1. Leer proyectos desde CSV y guardar en XML");
            System.out.println("2. Subir archivos XML a la base de datos eXist");
            System.out.println("0. Salir");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 1:
                    LeerCSV_Centros.leerCentros();
                    LeerCSV_Proyectos.leerProyectos();
                    break;
                case 2:
                    ConexionExistDB.conexionExistDb();
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("La opcion seleccionada no existe");
            }

        } while (!salir);
    }
}