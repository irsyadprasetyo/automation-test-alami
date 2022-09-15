package controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class SampleApiController {

  private static String URL = "https://thisurl.com";

  public ValidatableResponse postSomething(String payload) {
    return RestAssured.given()
        .header("Content-Type", "application/json")
        .body(payload).baseUri(URL).basePath("/post/v1/endpoints")
        .when().post().then().log().all();
  }

  public ValidatableResponse getSomeResponse(String response) {
    return RestAssured.given().queryParam("email", response)
        .header("Content-Type", "application/json")
        .baseUri(URL).basePath("/get/v1/endpoints/")
        .when().get().then().log().all();
  }

  public ValidatableResponse postSampleLogin(String email, String password) {
    String payload = "{" +
        "   \"email\":\"${email}\",\n" +
        "   \"password\":\"${password}\"\n" +
        "}";

    return RestAssured.given()
        .header("Content-Type", "application/json")
        .body(payload).baseUri(URL).basePath("/post/v1/endpoints")
        .when().post().then().log().all();
  }
}
