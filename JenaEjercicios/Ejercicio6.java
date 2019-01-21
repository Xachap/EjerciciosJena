package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
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
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio6 {
	
	// EJERCICIO 6: Modificando ontologías (RDFs)
	static final String NS = "http://somewhere#";
	public static void main (String[] args){
		
		String filename = "example5.rdf";
		
		// Creamos un modelo vacío
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// ** TAREA 5.1: Leer la ontología del fichero **
		// Usamos FileManager para localizar el fichero de entrada
		InputStream in = FileManager.get().open(filename);
		
		if(in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		
		// Leemos el fichero RDF/XML
		model.read(in, null);
		
		// Creamos una nueva clase llamada "Researcher"
		OntClass researcher = model.createClass(NS+"Researcher");
		
		// ** TAREA 6.1: Crear una nueva clase llamada "University" **
		OntClass university = model.createClass(NS+"University");
		
		// ** TAREA 6.2: Añadir "Researcher" como subclase de "Person" **
		OntClass person = model.getOntClass(NS+"Person");
		person.addSubClass(researcher);
		
		// ** TAREA 6.3: Crear una nueva object property llamada "workIn" **
		Property workIn = model.createProperty(NS+"workIn");
		
		// ** TAREA 6.4: Crear una nueva instancia de Researcher llamada "Jane Smith" **
		Individual janeSmith = researcher.createIndividual(NS+"JaneSmith");
		
		// ** TAREA 6.5: Añadir a la instancia JaneSmith su fullName, given name y family name **
		janeSmith.addLiteral(VCARD.FN, "Jane Smith");
		janeSmith.addLiteral(VCARD.Given, "Jane");
		janeSmith.addLiteral(VCARD.Family, "Smith");
		
		// ** TAREA 6.6: Añadir UPM como la universidad donde John Smith trabaja **
		Individual upm = university.createIndividual(NS+"UPM");
		Resource johnSmith = model.getResource(NS+"JohnSmith");
		johnSmith.addProperty(workIn, NS+"UPM");
		
		model.write(System.out, "RDF/XML-ABBREV");
		//
		
	}

}
