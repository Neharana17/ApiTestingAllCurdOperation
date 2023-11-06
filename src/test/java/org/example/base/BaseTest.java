package org.example.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.actions.AssertAction;
import org.example.endpoints.ApiConstants;
import org.example.modules.PayloadManager;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static RequestSpecification requestSpecification;
    public static AssertAction action;

    public static PayloadManager payloadManager;
    public static JsonPath jsonPath;
    public static Response response;

    public ValidatableResponse validatableResponse;

    @BeforeMethod(alwaysRun = true)
    public void Config() {
        payloadManager = new PayloadManager();
        action = new AssertAction();

        requestSpecification = RestAssured.given()
                .baseUri(ApiConstants.Base_URL)
                .contentType(ContentType.JSON);
    }

    public String getToken() throws JsonProcessingException {


        requestSpecification = RestAssured.given().baseUri(ApiConstants.Base_URL).basePath("/auth");
        String payload = payloadManager.setToken();
                response = requestSpecification.contentType(ContentType.JSON)
                        .body(payload)
                        .when().post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");

    }
}
