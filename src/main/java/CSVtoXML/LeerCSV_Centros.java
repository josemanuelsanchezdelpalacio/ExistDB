package CSVtoXML;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV_Centros {

    static List<Centro> listaCentros = new ArrayList<>();

    public static void leerCentros(){

        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/CentrosCFGMyS.csv"))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] separar = linea.split(",");

                Centro c = new Centro();
                c.setDistancia(Integer.parseInt(separar[0]));
                c.setCodigo(Integer.parseInt(separar[1]));
                c.setTelefono(Integer.parseInt(separar[2]));
                c.setCodigoPostal(Integer.parseInt(separar[3]));
                c.setProvincia(separar[4]);
                c.setLocalidad(separar[5]);
                c.setDenomCorta(separar[6]);
                c.setNombre(separar[7]);
                c.setDireccion(separar[8]);
                c.setDenominacion(separar[9]);
                c.setNaturaleza(separar[10]);
                c.setCorreo(separar[11]);
                c.setWeb(separar[12]);

                listaCentros.add(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }
}
