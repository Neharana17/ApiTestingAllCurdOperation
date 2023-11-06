package org.example.modules;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.payloads.request.Auth;
import org.example.payloads.request.Booking;
import org.example.payloads.request.Bookingdates;
import org.example.payloads.response.BookingRespons;
import org.example.utils.FakerUtil;

public class PayloadManager {

    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname(FakerUtil.getUseName());
        booking.setLastname("rana");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }




    public BookingRespons JsontoObject(String jsonString) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        BookingRespons bookingRespons=objectMapper.readValue(jsonString,BookingRespons.class);
        return bookingRespons;
    }



//    public  String CreatePayload_Negative() throws JsonProcessingException {
//        objectMapper = new ObjectMapper();
//
//        Booking booking = new Booking();
//        booking.setFirstname(FakerUtil.getUseName());
//        booking.setLastname("rana");
//        booking.setTotalprice(123);
//        booking.setDepositpaid(true);
//        booking.setAdditionalneeds("BreakFast");
//
//        Bookingdates bookingdates = new Bookingdates();
//        bookingdates.setCheckin("2022-01-01");
//        bookingdates.setCheckout("2022-01-10");
//        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
//        return payload;



   // }

    public  String PatchPayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname("honey");
        booking.setLastname("Sharma");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);
        String payload= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }
    public Booking JsontoObjectPatch(String jsonString) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        Booking bookingRespons=objectMapper.readValue(jsonString,Booking.class);
        return bookingRespons;

    }
    public  String UpdatePayload() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        Booking booking = new Booking();
        booking.setFirstname("honey");
        booking.setLastname("rana");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);
        String payload= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;
    }
    public Booking JsontoObjectPUT(String jsonString) throws JsonProcessingException {

        objectMapper = new ObjectMapper();
       Booking bookingRespons=objectMapper.readValue(jsonString,Booking.class);
        return bookingRespons;

    }

    public String setToken() throws JsonProcessingException {
        objectMapper= new ObjectMapper();
        Auth auth= new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);

    }


}
