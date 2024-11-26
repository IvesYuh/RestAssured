package restAssuredProjeto.arquivoTeste;

import static io.restassured.RestAssured.given;

import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import br.yuh.core.Arquivos;
import io.restassured.response.Response;

public class ValidarPdf {
	@Test
	public void validaDeclaracaoPdf() {
		Arquivos.retornaToken();
		String conteudoPdf = "SOLUS COMPUTACAO - CNPJ: 03.533.726/0001-88\r\n"
				+ "RUA JOSE ROQUE SALTON, 285\r\n"
				+ "LONDRINA - PR\r\n"
				+ "86047-622\r\n"
				+ "DECLARAÇÃO DE CARÊNCIA\r\n"
				+ "Informamos os dados cadastrais, constantes em nosso sistema, correspondente ao(s) beneficiário(s) abaixo relacionado(s):\r\n"
				+ "Código Nome Situação Segmentação Modalidade Abrangência Inclusão\r\n"
				+ "3912345678912345BENEFICIARIO AUTOMACAO JOILE Beneficiário ativo. Incluído em 08/07/2021 Ambulatorial mais\r\n"
				+ "Hospitalar com\r\n"
				+ "obstetrícia (06)\r\n"
				+ "Pre-pagamento Nacional 08/07/21\r\n"
				+ "Carência\r\n"
				+ "CARANECIA OCULTA - 03/05/25\r\n"
				+ "TESTE CARENCIA - 03/05/25\r\n"
				+ "Page 1/2\r\n"
				+ "SOLUS COMPUTACAO - CNPJ: 03.533.726/0001-88\r\n"
				+ "RUA JOSE ROQUE SALTON, 285\r\n"
				+ "LONDRINA - PR\r\n"
				+ "86047-622\r\n"
				+ "Código Nome Situação Segmentação Modalidade Abrangência Inclusão\r\n"
				+ "3912345678912268API NOTUS BENEFICIARIO Beneficiário ativo. Incluído em 26/05/2023 Ambulatorial mais\r\n"
				+ "Hospitalar com\r\n"
				+ "obstetrícia (06)\r\n"
				+ "Pre-pagamento Nacional 26/05/23\r\n"
				+ "Carência\r\n"
				+ "DOENÇAS E LESÕES PRÉ-EXISTENTES - \r\n"
				+ "Impresso Em: 19/11/2024\r\n"
				+ "Page 2/2";
		
		Response pdf = given()
			.pathParam("idBeneficiario", 49)
		.when()
			.get("/carencia/beneficiario/{idBeneficiario}/declaracao/pdf")
		.then()
			.statusCode(200)
			.extract()
			.response()
			;
		
		// Verifica se o tipo de conteúdo da resposta é "application/octet-stream" ou contém "application/pdf".
		 if (pdf.getContentType().equals("application/octet-stream") || pdf.getContentType().contains("application/pdf")) {
			 	// Tenta criar um fluxo de entrada para ler o conteúdo da resposta e carregar o PDF usando a biblioteca PDFBox.
	            try (InputStream pdfStream = pdf.asInputStream();
	                 PDDocument document = PDDocument.load(pdfStream)) {
	            	
	            	// Criação de um objeto `PDFTextStripper` que será usado para extrair o texto do documento PDF.
	                PDFTextStripper pdfStripper = new PDFTextStripper();
	                // Extrai o texto do documento PDF e o armazena na variável `pdfText`.
	                String pdfText = pdfStripper.getText(document);

	                // Verifica se o texto extraído do PDF contém um conteúdo esperado representado pela variável `conteudoPdf`.
	                assert pdfText.contains(conteudoPdf);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("A resposta não é um PDF.");
	        }
	}
}
