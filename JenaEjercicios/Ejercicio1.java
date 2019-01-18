package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio1 {
	
	// EJERCICIO 1: LEER Y ESCRIBIR FICHEROS EN RDF
	
	public static void main (String[] args){
		String filename = "example1.rdf";
		
		//Creamos un modelo vacio
		Model model = ModelFactory.createDefaultModel();
		
		//Usamos FileManager para localizar el fichero de entrada
		InputStream in = FileManager.get().open(filename);
		
		if (in == null)
			throw new IllegalArgumentException("Fichero: "+filename+" no encontrado");
		
		//Leemos el fichero RDF/XML
		model.read(in, null);
		
		//Escribimos por pantalla
		model.write(System.out);
		
		// ** TAREA 1.1: Escribir el modelo en formato Turtle **
		System.out.println("\n Formato turtle:\n");
		model.write(System.out, "TURTLE");
		
		// Otros formatos
				System.out.println("\n Formato JSON-LD:\n");
				model.write(System.out, "JSON-LD");
				
		// ** TAREA 1.2: Leer un nuevo modelo y combinarlo con el anterior **
		String filename2 = "example2.rdf";
		
		in = FileManager.get().open(filename2);
		
		if (in == null)
			throw new IllegalArgumentException("Fichero: "+filename2+" no encontrado");
		
		model.read(in, null);
		
		System.out.println("\n ------------------------------\n");
		System.out.println("\n Tarea 2:\n");
		model.write(System.out, "TURTLE");
	}
}
