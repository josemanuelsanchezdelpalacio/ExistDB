package FP2;

import Conexiones.ConexionExistDB;
import libs.Leer;

import java.sql.Connection;
import java.sql.SQLException;

public class ManejoBD {

    public static void main(String[] args) {

        boolean salir = false;
        int opcion;
        do {
            System.out.println("1. Conexión a existDB.");
            System.out.println("0. Salir");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    ConexionExistDB.conexionExistDb();
                    break;
                default:
                    System.out.println("La opcion seleccionada no existe");
            }

        } while (!salir);
    }
}