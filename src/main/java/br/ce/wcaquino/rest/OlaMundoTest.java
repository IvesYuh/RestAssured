package br.ce.wcaquino.rest;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {

		@Test
		public void testeOlaMundo() {
			Response response = RestAssured.request(Method.GET, "https://api.adviceslip.com/advice");
			Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
			Assert.assertTrue(response.statusCode() == 200);
			Assert.assertTrue("O Status code deveria ser 200",response.statusCode() == 201);
			Assert.assertEquals("200", response.statusCode());
			
			ValidatableResponse validacao = response.then();
			validacao.statusCode(200);
		}
}
