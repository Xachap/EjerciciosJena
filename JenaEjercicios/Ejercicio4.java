package JenaEjercicios;

import java.io.InputStream;

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

public class Ejercicio4 {
	
	// EJERCICIO 4: Consultas SPARQL
	
		public static void main (String[] args){
			
			String filename = "example3.rdf";
			
			// Creamos un modelo vacío
			Model model = ModelFactory.createDefaultModel();
			
			// Usamos FileManager para localizar el fichero de entrada
			InputStream in = FileManager.get().open(filename);
			
			if(in == null)
				throw new IllegalArgumentException("File: "+filename+" not found");
			
			// Leemos el fichero RDF/XML
			model.read(in, null);
			
			// Listamos todos los recursos con la propiedad "VCARD:FN"
			String queryString = "PREFIX vcard: <"+VCARD.getURI()+"> "+
							"SELECT ?Subject "+
							"WHERE { ?Subject vcard:FN ?FullName.} ";
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query,model);
			ResultSet results = qexec.execSelect();
			
			while(results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				Resource subj = (Resource) binding.get("Subject");
				System.out.println("Subject: "+subj.getURI());
			}
			
			// ** TAREA 4.1: Listar todos los recursos con la propiedad "VCARD:FN" e imprimir sus full names **
			System.out.println("------------------------");
			queryString = "PREFIX vcard: <"+VCARD.getURI()+"> "+
					"SELECT ?FullName "+
					"WHERE { ?Subject vcard:FN ?FullName.} ";
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query,model);
			results = qexec.execSelect();
			
			while(results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				Literal lit = binding.getLiteral("FullName");
				System.out.println("Full name: "+lit);
			}

			// ** TAREA 4.2: Obtener todos los recursos con family name "Smith" **
			System.out.println("------------------------");
			queryString = "PREFIX vcard: <"+VCARD.getURI()+"> "+
					"SELECT ?Subject "+
					"WHERE { ?Subject vcard:Family 'Smith'} ";
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query,model);
			results = qexec.execSelect();
			
			while(results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				Resource subj = (Resource) binding.get("Subject");
				System.out.println("Subject: "+subj.getURI());
			}
			
			// ** TAREA 4.3: Obtener todos los recursos con email **
			System.out.println("------------------------");
			queryString = "PREFIX foaf: <"+FOAF.getURI()+"> "+
					"SELECT ?Subject ?Email "+
					"WHERE { ?Subject foaf:email ?Email.} ";
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query,model);
			results = qexec.execSelect();
			
			while(results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				Resource subj = (Resource) binding.get("Subject");
				Literal lit = binding.getLiteral("Email");
				System.out.println("Subject: "+subj.getURI()+" Email: "+lit);
			}
			
			// ** TAREA 4.4 (avanzado): Obtener todos los subjects que conozcan a "Jane Smith" y mostrar sus given names **
			System.out.println("------------------------");
			queryString = "PREFIX foaf: <"+FOAF.getURI()+"> "+
					"PREFIX vcard: <"+VCARD.getURI()+"> "+
					"SELECT ?Subject ?Given "+
					"WHERE { ?Subject foaf:knows ?JaneSmith."
					+ "?JaneSmith vcard:FN 'Jane Smith'."
					+ "?Subject vcard:Given ?Given} ";
			query = QueryFactory.create(queryString);
			qexec = QueryExecutionFactory.create(query,model);
			results = qexec.execSelect();
			
			while(results.hasNext())
			{
				QuerySolution binding = results.nextSolution();
				Resource subj = (Resource) binding.get("Subject");
				Literal lit = binding.getLiteral("Given");
				System.out.println("Subject: "+subj.getURI()+" Given: "+lit);
			}

		}

}
