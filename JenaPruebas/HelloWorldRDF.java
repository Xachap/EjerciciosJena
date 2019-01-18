package JenaPruebas;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class HelloWorldRDF {
	/* Ejercicio Hello world de Intenet */
	/* Crea un grafo y lo muestra en RDF*/
	static final String resourceURI = "http://somewhere/JohnSmith";
	static final String fullName = "John Smith";
	public static void main (String[] args){
		Model m = ModelFactory.createDefaultModel();
		
		Resource johnSmith = m.createResource(resourceURI);
		
		johnSmith.addProperty(VCARD.FN, fullName);
		
		m.write(System.out);
	}
}
