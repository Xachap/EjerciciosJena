package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio5 {
	
	// EJERCICIO 5: Lectura y escritura de ontologías
	
			public static void main (String[] args){
				
				String filename = "example4.rdf";
				
				// Creamos un modelo vacío
				OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
				
				// ** TAREA 5.1: Leer la ontología del fichero **
				// Usamos FileManager para localizar el fichero de entrada
				InputStream in = FileManager.get().open(filename);
				
				if(in == null)
					throw new IllegalArgumentException("File: "+filename+" not found");
				
				// Leemos el fichero RDF/XML
				model.read(in, null);
				
				// ** TAREA 5.2: Mostrar ontologia por pantalla **
				model.write(System.out, "RDF/XML-ABBREV");
				
			}
}
