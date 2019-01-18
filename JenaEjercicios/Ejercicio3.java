package JenaEjercicios;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Ejercicio3 {
	
	// EJERCICIO 3: MANEJO DE STATEMENTS 2
	
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
		
		// Mostramos el modelo en formato Turtle para comprobar los apartados futuros
		model.write(System.out, "TURTLE");
		
		// Listamos todos los recursos con la propiedad "VCARD:FN"
		ResIterator rIter = model.listSubjectsWithProperty(VCARD.FN);
		
		while (rIter.hasNext())
		{
			Resource r = rIter.nextResource();
			System.out.println("Subject: "+r.getURI());
		}
		
		// ** TAREA 3.1: Listar todos los recursos con la propiedad "VCARD:FN" e imprimir sus full names **
		System.out.println("------------------------");
		StmtIterator iter1 = model.listStatements(null, VCARD.FN, (RDFNode)null);
		
		while (iter1.hasNext())
		{
			Statement s = iter1.next();
			Resource r = s.getSubject();
			RDFNode node = s.getObject();
			System.out.println(node.asLiteral()+" -> "+r.getURI());
		}
		
		// ** TAREA 3.2: Obtener todos los recursos con family name "Smith" **
		System.out.println("------------------------");
		iter1 = model.listStatements(null, VCARD.Family, "Smith");
		
		while (iter1.hasNext())
		{
			Statement s = iter1.next();
			Resource r = s.getSubject();
			System.out.println("Subject: "+r.getURI());
		}
		
		// ** TAREA 3.3: Obtener todos los recursos con email **
		System.out.println("------------------------");
		Property email = model.getProperty("foaf:email");
		iter1 = model.listStatements(null, email, (RDFNode)null);

		while (iter1.hasNext())
		{
			Statement s = iter1.next();
			Resource r = s.getSubject();
			RDFNode mail = s.getObject();
			System.out.println(r.getURI()+" ; Email -> "+mail.asLiteral());
		}
		// ** TAREA 3.4 (avanzado): Obtener todos los subjects que conozcan a "Jane Smith" y mostrar sus given names ** 
		System.out.println("------------------------");
		iter1 = model.listStatements(null, VCARD.FN, "Jane Smith");
		
		while(iter1.hasNext())
		{
			Statement s = iter1.next();
			Resource r = s.getSubject();
			StmtIterator iter2 = model.listStatements(null, FOAF.knows, r);
			
			while (iter2.hasNext())
			{
				Statement st = iter2.next();
				Resource subj = st.getSubject();
				
				StmtIterator iter3 = model.listStatements(subj, VCARD.Given, (RDFNode)null);
				while (iter3.hasNext())
				{
					Statement stat = iter3.next();
					RDFNode giv = stat.getObject();
					System.out.println(subj.getURI()+" "+VCARD.Given.getURI()+" "+giv.asLiteral());
				}
				
			}
		}
		
	}
}
