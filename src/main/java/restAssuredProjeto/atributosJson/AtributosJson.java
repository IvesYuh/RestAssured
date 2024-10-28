package restAssuredProjeto.atributosJson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class AtributosJson {
	@Test
	public void validaAtributosPrimeiroNivel() {
		// Valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200) // Verifica se o código de status é 200 (Sucesso)
			.body("id", is(1)) // Verifica se o campo "id" é igual a 1
			.body("name", containsString("João")) // Verifica se o campo "name" contém "João"
			.body("age", greaterThan(18))  // Verifica se o campo "age" é maior que 18
			; 
	}
	
	@Test
	public void validaAtributosSegundoNivel() {
		// Envia uma requisição GET para http://restapi.wcaquino.me/users/1 e valida o primeiro nível do JSON retornado
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/2")
		.then()
			.log().all()
			.statusCode(200) // Verifica se o código de status é 200 (OK)
			.body("name", containsString("Joaquina")) // Verifica se o campo "name" contém "Joaquina"
			.body("endereco.rua", is("Rua dos bobos")) // Verifica valor encontrado em segundo nivel
			.body("endereco.numero", is(0))
			;
	}
}
