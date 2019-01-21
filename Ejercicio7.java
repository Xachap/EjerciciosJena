package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
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
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio7 {
	
	// EJERCICIO 7: Consultas sobre ontologías (RDFs)
	static final String NS = "http://somewhere#";
	public static void main (String[] args){
		
		String filename = "example6.rdf";
		
		// Creamos un modelo vacío
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// ** TAREA 5.1: Leer la ontología del fichero **
		// Usamos FileManager para localizar el fichero de entrada
		InputStream in = FileManager.get().open(filename);
		
		if(in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		
		// Leemos el fichero RDF/XML
		model.read(in, null);	
		
		// ** TASK 7.1: Listar todos los individuos de la clase "Person" **
		OntClass person = model.getOntClass(NS+"Person");
		ExtendedIterator iter = person.listInstances(false);
		
		while (iter.hasNext())
		{
			Individual r = (Individual) iter.next();
			System.out.println("Person: "+r.getURI());
		}
		
		// ** TASK 7.2: Listar todas las subclases de "Person" **
		System.out.println("------------------------");
		iter = person.listSubClasses(false);
		
		while (iter.hasNext())
		{
			OntClass c = (OntClass) iter.next();
			System.out.println("Subclass: "+c.getURI());
		}
		
		// ** TASK 7.3: Realizar los cambios necesarios para obtener también las instancias y subclases indirectas. Considera aplicar inferencia en tu modelo... **
		OntModel model_inf = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF);
		
		InputStream in2 = FileManager.get().open(filename);
		
		if(in2 == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
		
		model_inf.read(in2, null);
		
		// Para las instancias de Person
		System.out.println("------------------------");
		person = model_inf.getOntClass(NS+"Person");
		iter = person.listInstances(false);
		
		while (iter.hasNext())
		{
			Individual r = (Individual) iter.next();
			System.out.println("Person: "+r.getURI());
		}
		
		//Para las subclases de Person
		System.out.println("------------------------");
		iter = person.listSubClasses(false);
		
		while (iter.hasNext())
		{
			OntClass c = (OntClass) iter.next();
			System.out.println("Subclass: "+c.getURI());
		}
	}

}
