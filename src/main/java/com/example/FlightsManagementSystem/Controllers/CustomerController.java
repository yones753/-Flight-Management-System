package com.example.FlightsManagementSystem.Controllers;


import com.example.FlightsManagementSystem.Facades.AdministratorFacade;
import com.example.FlightsManagementSystem.Facades.CustomerFacade;
import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.dao.Users;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerFacade customerFacade;

    @PostMapping("/authenticate")
    public void authenticate() throws Exception {
        var username =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var role= SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        var role2=role.replace("ROLE_","");
        System.out.println(role2);
        if(!role2.equals("User")){
            throw new Exception("invalid role");
        }
        var usersDAO=new Users();
        LoginToken loginToken=new LoginToken(usersDAO.getUserByUsername(username)._id,username,usersDAO.getUserByUsername(username)._userRole);
        customerFacade= new CustomerFacade(loginToken);
    }

    @GetMapping("/")
    public List<Ticket> getAll() {
        return customerFacade.get_my_tickets();
    }


    @PostMapping("/")
    public void add(@RequestBody Ticket ticket){
        customerFacade.add_ticket(ticket);
    }

    @DeleteMapping("/")
    public void delete(@RequestBody Ticket ticket){
        customerFacade.remove_ticket(ticket);
    }

    @PutMapping("/")
    public void update(@RequestBody Customer customer){
        customerFacade.update_customer(customer);
    }

}
