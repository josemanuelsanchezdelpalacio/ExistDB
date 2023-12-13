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
                p.setAutorizacion(comas[0]);
                listaProyectos.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }

}
