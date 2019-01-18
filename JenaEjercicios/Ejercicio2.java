package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio2 {
	
	// EJERCICIO 2: MANEJO DE STATEMENTS
		static final String ns = "http://somewhere/";
		public static void main (String[] args){
			// Creamos un model vacío
			Model model = ModelFactory.createDefaultModel();
			
			String fullName = "John Smith";
			String johnURI = ns+"JohnSmith";
			
			// ** TAREA 2.1: Crear un resource John Smith
			Resource johnSmith = model.createResource(johnURI);
			
			// Añadir a johnSmith la datatype property: full name (en base al vocabulario VCARD)
			
			johnSmith.addLiteral(VCARD.FN, fullName);
			
			// ** TAREA 2.2: Crear un resource para Jane Smith, con su full name y email **
			String janeURI = ns+"JaneSmith";
			
			Resource janeSmith = model.createResource(janeURI);
			janeSmith.addLiteral(VCARD.FN, "Jane Smith");
			janeSmith.addLiteral(VCARD.EMAIL, "janesmith@example.com");
			
			// ** TAREA 2.3: Añadir Jane como persona conocida de Jhon mediante un object property del vocabulario FOAF **
			johnSmith.addProperty(FOAF.knows, janeSmith);
			
			// Escribimos el resultado por pantalla
			model.write(System.out, "RDF/XML-ABBREV");
		}
}
