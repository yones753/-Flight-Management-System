package com.example.FlightsManagementSystem.Controllers;


import com.example.FlightsManagementSystem.Facades.AdministratorFacade;
import com.example.FlightsManagementSystem.Facades.AirlineFacade;
import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.dao.Users;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

//    @Autowired
 private AdministratorFacade administratorFacade;

    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        System.out.println(role2);
        if(!role2.equals("Admin")){
            throw new Exception("invalid role");
        }
        var usersDAO=new Users();

        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username)._id,username,usersDAO.getUserByUsername(username)._userRole);
        administratorFacade= new AdministratorFacade(loginToken);
    }

    @GetMapping("/")
    public List<Customer> getAll() {
        return administratorFacade.get_all_customers();
    }

    @PostMapping("/airline")
    public void addAirline(@RequestBody Airline_company newAirline, @RequestBody User newUser) {
        administratorFacade.add_airline(newAirline, newUser);
    }

//    @PostMapping("/")
//    public void addAdmin(@RequestBody Administrator newAdmin, @RequestBody User newUser) {
//        administratorFacade.add_administrator(newAdmin, newUser);
//    }

    @PostMapping("/country")
    public void addCountry(@RequestBody Country country) {
        administratorFacade.add_country(country);
    }

    @DeleteMapping("/airline")
    public void deleteAirline(@RequestBody Airline_company airline) {
        administratorFacade.remove_airline(airline);
    }

    @DeleteMapping("/customer")
    public void deleteCustomer(@RequestBody Customer customer) {
        administratorFacade.remove_customer(customer);
    }

    @DeleteMapping("/")
    public void deleteAdmin(@RequestBody Administrator administrator) {
        administratorFacade.remove_administrator(administrator);
    }

}
