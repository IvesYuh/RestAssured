package restAssuredProjeto.schema;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.xml.sax.SAXException;

import br.yuh.core.BaseTestXml;
import br.yuh.entidades.DadosSchemaXml;

public class SchemaXml extends BaseTestXml {
	@Test
	public void validaSchemaXml() throws IOException, SAXException {
		// Carregando o schema XML
	    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    Schema schema = schemaFactory.newSchema(new File("C:\\Ives Yuh\\RestAssuredProjeto\\src\\main\\resources\\schema\\schema.xml"));

	    // Validando a resposta com o schema XML
	    Validator validator = schema.newValidator();
	    Source xmlSource = new StreamSource(new StringReader(DadosSchemaXml.solicitacaoProcedimento));

	    try {
	        validator.validate(xmlSource); // Validação do XML de resposta (xmlSource) contra o esquema (schema).
	        System.out.println("Schema validado com sucesso !");
	    } catch (SAXException e) {
	    	System.out.println("Schema validado com falha: " + e.getMessage());
	        throw new AssertionError("Schema validado com falha!", e);
	    }
	}
}
