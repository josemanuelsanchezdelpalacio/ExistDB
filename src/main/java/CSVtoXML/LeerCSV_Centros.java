package CSVtoXML;

import conexion.ConexionExistDB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import objetos.Centro;
import objetos.Centros;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static libs.FicheroEscribible.ficheroEscribible;

public class LeerCSV_Centros {

    static ArrayList<Centro> centrosLista = new ArrayList<>();

    public static void leerCentros() {
        try {
            List<String> lineas = Files.readAllLines(Path.of("src/main/resources/CentrosCFGMyS.csv"));
            lineas.remove(lineas.get(0));

            Centros centros = new Centros();
            for (String linea : lineas) {
                linea = linea.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "");
                String[] centro = linea.split(",");

                Centro centroAux = new Centro();

                centroAux.setDistancia(centro[0]);
                centroAux.setProvincia(centro[1]);
                centroAux.setLocalidad(centro[2]);
                centroAux.setCodigo(centro[3]);
                centroAux.setDenomCorta(centro[4]);
                centroAux.setNombre(centro[5]);
                centroAux.setDenominacion(centro[6]);
                centroAux.setDireccion(centro[7]);
                centroAux.setNaturaleza(centro[8]);
                centroAux.setTelefono(centro[9]);
                centroAux.setCorreoElectronico(centro[10]);
                centroAux.setCodigoPostal(centro[11]);

                centrosLista.add(centroAux);
            }
            centros.setCentros(centrosLista);
            try {
                JAXBContext contexto = JAXBContext.newInstance(Centros.class);
                Marshaller marshaller = contexto.createMarshaller();
                marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(centros, new FileWriter("target/centros.xml"));
                System.out.println("XML Centros creado");
            } catch (JAXBException ex) {
                System.out.println("La clase seleccionada no permite usar JAXB");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error de lectura o a la hora de transformar");
        }
    }
}
