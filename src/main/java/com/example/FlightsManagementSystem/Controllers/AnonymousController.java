package com.example.FlightsManagementSystem.Controllers;


import com.example.FlightsManagementSystem.Facades.AnonymousFacade;
import com.example.FlightsManagementSystem.JWT.JWTUtil;
import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.Login.UserInfo;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
//@RequestMapping("/anonymous")
public class AnonymousController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    AnonymousFacade anonymousFacade = new AnonymousFacade();

    @PostMapping("/authenticate")
    public Map<String, Object> loginHandler(@RequestBody UserInfo body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getUsername(), anonymousFacade.getUserId(body.getUsername()), anonymousFacade.getUserRole(body.getUsername()));
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    @GetMapping("/")
    public String welcome() {
        return "<h1>Hello</h1>";
    }

    @GetMapping("/flights")
    public List<Flight> getAll() {
        return anonymousFacade.get_all_flights();
    }

    @GetMapping("/{id}")
    public Flight getOneById(@PathVariable int id) {
        return anonymousFacade.get_flight_by_id(id);
    }

    @GetMapping("/{originId}/{destinationId}/{date}")
    public List<Flight> getOnePyParameters(@PathVariable int originId, @PathVariable int destinationId, @PathVariable String date) {
        return anonymousFacade.get_flights_by_parameters(originId, destinationId, Timestamp.valueOf(date));
    }

    @GetMapping("/airline")
    public List<Airline_company> getAirlines() {
        return anonymousFacade.get_all_airlines();
    }

    @GetMapping("/airline/{id}")
    public Airline_company getAirlineById(@PathVariable int id) {
        return anonymousFacade.get_airline_by_id(id);
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return anonymousFacade.get_all_countries();
    }

    @GetMapping("/countries/{id}")
    public Country getCountryById(@PathVariable int id) {
        return anonymousFacade.get_country_by_id(id);
    }

    @PostMapping("/user")
    public void add(@RequestBody User user) {
        anonymousFacade.create_new_user(user);
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer customer, @RequestBody User user) {
        anonymousFacade.add_customer(customer, user);
    }


}
