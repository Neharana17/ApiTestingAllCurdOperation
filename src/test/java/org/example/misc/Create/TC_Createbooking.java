package org.example.misc.Create;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.example.actions.AssertAction;
import org.example.base.BaseTest;
import org.example.endpoints.ApiConstants;
import org.example.modules.PayloadManager;
import org.example.payloads.response.BookingRespons;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TC_Createbooking extends BaseTest {

    @Test(groups = {"stage","P0"})
    @Owner("Neha")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_001 Verify that booking is working and get an ID")
    public void testCreatebooking_Postive() throws JsonProcessingException {



        payloadManager=new PayloadManager();
        action= new AssertAction();

        requestSpecification= RestAssured.given()
                .baseUri(ApiConstants.Base_URL)
                .contentType(ContentType.JSON);




       requestSpecification.basePath(ApiConstants.Create_booking);
       response=requestSpecification.when().body(payloadManager.createPayload()).post();
       validatableResponse=response.then().log().all();
       validatableResponse.statusCode(200);
        BookingRespons bookingRespons=payloadManager.JsontoObject(response.asString());
        assertThat(bookingRespons.getBookingid().toString()).isNotEmpty().isNotNull();
    }
    @Test(groups = {"stage","P0"})
    @Description("TC_002 Verify that booking with no paload")

    public void testCreateBooking2_Negative() throws JsonProcessingException {


        payloadManager=new PayloadManager();
        action= new AssertAction();

        requestSpecification= RestAssured.given()
                .baseUri(ApiConstants.Base_URL)
                .contentType(ContentType.JSON);




        requestSpecification.basePath(ApiConstants.Create_booking);
        response=requestSpecification.when().body(" ").post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(400);


    }
}
