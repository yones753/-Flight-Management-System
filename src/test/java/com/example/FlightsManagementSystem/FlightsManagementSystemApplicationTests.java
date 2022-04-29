package com.example.FlightsManagementSystem;


import com.example.FlightsManagementSystem.dao.Airline_companies;
import com.example.FlightsManagementSystem.dao.Customers;
import com.example.FlightsManagementSystem.dao.Flights;
import com.example.FlightsManagementSystem.dao.Tickets;
import com.example.FlightsManagementSystem.poco.Airline_company;
import com.example.FlightsManagementSystem.poco.Customer;
import com.example.FlightsManagementSystem.poco.Flight;
import com.example.FlightsManagementSystem.poco.Ticket;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class FlightsManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    //Admin
    @Test
    void getFromAdminControllerTest() {
        String url = "http://localhost:8080/admin/";
        var result = HttpFunctionTest.generateEntityOfType(Customer[].class, url);
        Customer expected = Arrays.stream(result).filter((m) -> m._id == 65).findFirst().orElse(new Customer());
        Customers customers = new Customers();
        Customer current = customers.getCustomerByUserId(65);
        Assert.assertEquals(current, expected);
    }

    //Customer
    @Test
    void getCustomerControllerTest() {
        String url = "http://localhost:8080/customer/";
        var result = HttpFunctionTest.generateEntityOfType(Ticket[].class, url);
        Ticket expected = Arrays.stream(result).filter((t) -> t._id == 62).findFirst().orElse(new Ticket());
        var tickets = new Tickets();
        Ticket current = tickets.getTicketById(62);
        Assert.assertEquals(current, expected);
    }

    //Airline
    @Test
    void getAirlineControllerTest() {
        String url = "http://localhost:8080/airline/";
        var result = HttpFunctionTest.generateEntityOfType(Flight[].class, url);
        Flight expected = Arrays.stream(result).filter((t) -> t._id == 35).findFirst().orElse(new Flight());
        var flights = new Flights();
        var current = flights.getFlightById(35);
        Assert.assertEquals(current, expected);
    }

    //Anonymous
    @Test
    void getAnonymousControllerTest() {
        String url = "http://localhost:8080/anonymous/35";
        var result = HttpFunctionTest.generateEntityOfType(Flight.class, url);
        System.out.println(result);
        var flights = new Flights();
        var current = flights.getFlightById(35);
        Assert.assertEquals(current, result);
    }


}
