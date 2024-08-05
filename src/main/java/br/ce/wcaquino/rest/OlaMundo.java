package br.ce.wcaquino.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class OlaMundo {
	public static void main(String[] args) {
		Response response = RestAssured.request(Method.GET, "https://api.adviceslip.com/advice");
		System.out.println(response.getBody().asString());
	}
}
