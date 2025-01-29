package io.learn.api;

import io.learn.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final ConfigReader configReader = new ConfigReader();
    private static final String BASE_URL = configReader.getProperty("api.base.url");

    private ApiClient() {
    }

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath("/v2/pet")
                .setContentType("application/json")
                .build();
    }

    public static RequestSpecification getRequestSpecificationWithHeader() {
        return getRequestSpecification().header("Content-Type", "application/json");
    }

    public static Response get(String resource) {
        return given()
                .spec(getRequestSpecification())
                .log().all()
                .when()
                .log().all()
                .get(resource);
    }

    public static Response post(String resource, String body) {
        return given()
                .spec(getRequestSpecificationWithHeader())
                .body(body)
                .when()
                .post(resource);
    }

    public static Response put(String resource, String body) {
        return given()
                .spec(getRequestSpecificationWithHeader())
                .body(body)
                .when()
                .put(resource);
    }

    public static Response delete(String resource) {
        return given()
                .spec(getRequestSpecification())
                .when()
                .delete(resource);
    }

}
