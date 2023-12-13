package CSVtoXML;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.nio.file.Path;

import static CSVtoXML.LeerCSV_Centros.listaCentros;
import static CSVtoXML.LeerCSV_Proyectos.listaProyectos;
import static libs.FicheroEscribible.ficheroEscribible;

public class EscribirXML {

    public static void escribirXML(){

        try {
            Path p = Path.of("target/proyectos.xml");
            Path p2 = Path.of("target/centros.xml");

            //creo contexto JAXB para la clase de Centro y Proyecto
            if (ficheroEscribible(p) && ficheroEscribible(p2)) {
                JAXBContext contexto = JAXBContext.newInstance(Centro.class);
                JAXBContext contexto2 = JAXBContext.newInstance(Proyecto.class);

                Marshaller marshaller = contexto.createMarshaller();
                Marshaller marshaller2 = contexto2.createMarshaller();

                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                //guardo la lista de centros y proyectos con la info de los CSV en los ficheros XML
                marshaller.marshal(listaCentros, p.toFile());
                marshaller.marshal(listaProyectos, p.toFile());

                System.out.println("Archivo XML creado correctamente: " + p);
            } else {
                System.out.println("El archivo no se puede escribir.");
            }
        } catch (Exception e) {
            System.err.println("Error al generar el archivo XML: " + e.getMessage());
        }

    }

}
