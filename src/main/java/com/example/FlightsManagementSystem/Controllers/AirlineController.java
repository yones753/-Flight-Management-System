package com.example.FlightsManagementSystem.Controllers;


import com.example.FlightsManagementSystem.Facades.*;
import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.dao.Users;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    private AirlineFacade airlineFacade;
    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        if(!role2.equals("Airline Company")){
            throw new Exception("invalid role");
        }
        var usersDAO=new Users();
        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username)._id,username,usersDAO.getUserByUsername(username)._userRole);
        System.out.println(loginToken);
        airlineFacade=new AirlineFacade(loginToken);
    }


    @GetMapping("/")
    public List<Flight> getAll() {
        return airlineFacade.get_my_flights();
    }

    @PostMapping("/")
    public void add(@RequestBody Flight flight) {
        airlineFacade.add_flight(flight);
    }

    @DeleteMapping("/")
    public void delete(@RequestBody Flight flight) {
        airlineFacade.remove_flight(flight);
    }

    @PutMapping("/")
    public void update(@RequestBody Airline_company airline) {
        airlineFacade.update_airline(airline);
    }

    @PutMapping("/flight")
    public void updateFlight(@RequestBody Flight flight) {
        airlineFacade.update_flight(flight);
    }

}
