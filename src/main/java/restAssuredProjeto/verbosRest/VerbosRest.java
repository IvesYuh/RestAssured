package restAssuredProjeto.verbosRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.yuh.entidades.DadosVerbosRest;
import io.restassured.http.ContentType;

public class VerbosRest {
    @Test
    public void validaBodyPadrao() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Joaozinho\",\"age\": 26}") // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
             .body("name", is("Joaozinho"))
             ; // Verifica se o campo "name" da resposta é "Joaozinho"
    }
    
    @Test
    public void validaBodyUtilizandoMap() {
        // Cria um mapa com os dados do usuário
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("name", "Usuario via map");
        body.put("age", 25);
        
        given()
            .log().all() // Log de todas as informações da requisição
            .contentType("application/json") // Tipo de conteúdo da requisição
            .body(body) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
            .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
             .body("name", is("Usuario via map")) // Verifica se o campo "name" da resposta é "Usuario via map"
             .body("age", is(25));	// Verifica se o campo "age" da resposta é 25
    }
    
    @Test
    public void validaBodyUtilizandoObjeto() {
        // Cria um objeto do tipo User com os dados do usuário
    	DadosVerbosRest dados = new DadosVerbosRest("Usuario via objeto", 35);
         
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body(dados) // Corpo da requisição com os dados do usuário
        .when()
            .post("http://restapi.wcaquino.me/users") // Envia a requisição POST para o endpoint especificado
        .then()
             .log().all() // Log de todas as informações da resposta
             .statusCode(201) // Verifica se o código de status da resposta é 201
             .body("name", is("Usuario via objeto")) // Verifica se o campo "name" da resposta é "Usuario via objeto"
             .body("age", is(35));	// Verifica se o campo "age" da resposta é 35
    }
    
    @Test
    public void validaBodyUtilizandoXml() {
        given()
	        .log().all() // Log de todas as informações da requisição
	        .contentType(ContentType.XML) // Tipo de conteúdo da requisição
	        .body("<user><name>Joaozinho</name><age>26</age></user>") // Corpo da requisição com os dados do usuário em XML
	    .when()
        	.post("http://restapi.wcaquino.me/usersXML") // Envia a requisição POST para o endpoint especificado
        .then()
	        .log().all() // Log de todas as informações da resposta
	         .statusCode(201) // Verifica se o código de status da resposta é 201 (Created)
	         .body("id.@id", is(notNullValue())) // Verifica se o campo "id" da resposta não é nulo
	         .body("user.name", is("Joaozinho")) // Verifica se o campo "name" da resposta é "Joaozinho"
	         .body("user.age", is("26"));	// Verifica se o campo "age" da resposta é 26
    }
    
    @Test
    public void validaCustomizacaoUrl() {
        given()
            .contentType(ContentType.JSON) // Tipo de conteúdo da requisição
            .body("{\"name\": \"Usuario Alterado\",\"age\": 80}") // Corpo da requisição com os dados do usuário
            .pathParam("entidade", "users") //adicionado uma entidade como parametro para ser substituido na url
            .pathParam("userId", "1") //adicionado uma entidade como parametro para ser substituido na url
        .when()
            .put("http://restapi.wcaquino.me/{entidade}/{userId}") // Envia a requisição PUT com url customizada
        .then()
            .log().all() // Log de todas as informações da resposta
            .statusCode(200)
            ; // Verifica se o código de status da resposta é 200
    }
}
