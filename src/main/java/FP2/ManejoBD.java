package FP2;

import Conexiones.ConexionMySQL;
import Conexiones.ConexionExistDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejoBD {

    public static void main(String[] args) {

        boolean salir = false;
        int opcion;

        try (Connection conexion = ConexionMySQL.handleBD()) {

            if (conexion != null) System.out.println("conexion establecida");

            while (!salir) {

                System.out.println("1. Crear BD");
                System.out.println("2. Borrar BD");
                System.out.println("3. Crear tablas");
                System.out.println("4. Transformar de csv a xml.");
                System.out.println("5. Conexión a existDB.");
                System.out.println("6. Exportar datos XQuery a XML.");
                System.out.println("7. Importar datos XML a la base de datos.");
                System.out.println("0. Salir");

                try {
                    opcion = Clases.LeerDatos.pideInt("");
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido");
                    continue;
                }

                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        crearBD();
                        break;
                    case 2:
                        borrarBD();
                        break;
                    case 3:
                        CreacionTablas.crear();
                        System.out.println("Tablas creadas");
                        break;
                    case 4:
                        Transformaciones.TransformarCsvXml.transformarCsvXml();
                        System.out.println("Conversión realizada.");
                        break;
                    case 5:
                        ConexionExistDB.conexionExistDb();
                        break;
                    case 6:
                        ConexionExistDB.consultaExist();
                        break;
                    case 7:
                        Transformaciones.TransformarXMLEstados.transformarXmlEstados();
                        System.out.println("Estados importados");
                        Transformaciones.TransformarXMLRoles.transformarXmlRoles();
                        System.out.println("Roles importados");
                        Transformaciones.TransformarXMLTags.transformarXmlTags();
                        System.out.println("Tags importados");
                        Transformaciones.TransformarXMLProfesiones.transformarXmlProfesiones();
                        System.out.println("Profesiones importadas");
                        Transformaciones.TransformarXMLEntidades.transformarXmlEntidades();
                        System.out.println("Entidades importadas");
                        Transformaciones.TransformarXMLProyectos.transformarXmlProyectos();
                        System.out.println("Proyectos importados");
                        Transformaciones.TransformarXMLUsuarios.transformarXmlUsuarios();
                        System.out.println("Usuarios importados");
                        Transformaciones.TransformarXMLParticipaciones.transformarXmlParticipaciones();
                        System.out.println("Participaciones importadas");
                        break;
                    default:
                        System.out.println("Ingrese una opción válida");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }


    // Crear y borrar base de datos
    static void crearBD() {
        try (Connection con = ConexionMySQL.handleBD()) {
            try (Statement stmt = con.createStatement()) {
                stmt.execute("CREATE DATABASE BdFactoriaProyectos");
                System.out.println("BD creada");
            }
        } catch (SQLException e) {
            System.out.println("La base de datos ya existe.");
        }
    }

    static void borrarBD() {
        try (Connection con = ConexionMySQL.conectar()) {
            try (Statement stmt = con.createStatement()) {
                stmt.execute("DROP DATABASE BdFactoriaProyectos");
                System.out.println("BD borrada");
            }
        } catch (SQLException e) {
            System.out.println("La base de datos no existe.");
        }
    }
}