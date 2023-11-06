package org.example.curd.INT;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.collections4.Put;
import org.example.base.BaseTest;
import org.example.endpoints.ApiConstants;
import org.example.payloads.request.Booking;
import org.example.payloads.response.BookingRespons;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MATCHER;

public class TC_Integration extends BaseTest {
    String token;
    String bookingid;
    String bookingIDpojo;

    // get token


    //create booking
    @Test(groups = "P0")
    public void testCreateBooking() throws JsonProcessingException {

        token = getToken();
        System.out.println(token);

        assertThat(token).isNotNull().isNotEmpty();

        requestSpecification.basePath(ApiConstants.Create_booking);
        response = requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        bookingid = jsonPath.getString("bookingid");

        BookingRespons bookingRespons = payloadManager.JsontoObject(response.asString());
        bookingIDpojo = bookingRespons.getBookingid().toString();

        System.out.println("JSON BookingID:" + bookingid);
        System.out.println("bookingID pojo:" + bookingIDpojo);
        assertThat(bookingIDpojo).isNotNull().isNotEmpty();

    }

    //PatchRequest
    @Test(groups = "P0")
    public void testCreateandPatchBooking() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.Create_booking);
        response = requestSpecification.when().body(payloadManager.PatchPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        bookingid = jsonPath.getString("bookingid");

        BookingRespons bookingRespons = payloadManager.JsontoObject(response.asString());
        bookingIDpojo = bookingRespons.getBookingid().toString();

        System.out.println("JSON BookingID:" + bookingid);
        System.out.println("bookingID pojo:" + bookingIDpojo);
        assertThat(bookingIDpojo).isNotNull().isNotEmpty();

    }


        //Update Booking
    @Test(groups = "P0" , dependsOnMethods = {"testCreateBooking"})
    public void testCreateandUpdateBooking() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.Update_booking + "/" + bookingid);
        response = RestAssured.given().spec(requestSpecification).cookie("token", token)
                .when().body(payloadManager.UpdatePayload()).put();
        validatableResponse = response.then().log().all();

        //  validatableResponse.body("firstname", Matchers.is("honey"));
        Booking bookingRespons = payloadManager.JsontoObjectPUT(response.asString());
        assertThat(bookingRespons.getFirstname()).isEqualTo("honey").isNotNull();
        assertThat(bookingRespons.getLastname()).isNotNull();
        assertThat(bookingRespons.getDepositpaid()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckout()).isNotEmpty();

    }

    //Delete Booking
    @Test(groups = "P0")
    public void testCreateDeleteBooking() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.Update_booking + "/" + bookingid).cookie("token", token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(groups = "P0",dependsOnMethods = {"testCreateDeleteBooking"})
    public void testCreateDeleteBookingByGet() throws JsonProcessingException {

        requestSpecification.basePath(ApiConstants.Update_booking + "/" + bookingid);
       response = RestAssured.given().spec(requestSpecification)
                       .when().get();
       validatableResponse=response.then().log().all();
        validatableResponse.statusCode(404);
    }
}
