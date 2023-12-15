package CSVtoXML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeerCSV_Proyectos {

    static List<Proyecto> listaProyectos = new ArrayList<>();

    public static void leerProyectos(){

        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/proyectos.csv"))){
            String linea;
            while ((linea = br.readLine()) != null){
                String[] comas = linea.split(",");

                Proyecto p = new Proyecto();
                p.setCoordinadorCentro(comas[0]);
                p.setTituloProyecto(comas[1]);
                p.setAutorizacion(comas[2]);
                p.setContinuidad(comas[3]);
                p.setCoordinacion(comas[4]);
                p.setContacto(comas[5]);
                p.setCentrosAnexion(comas[6]);

                listaProyectos.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }

}
