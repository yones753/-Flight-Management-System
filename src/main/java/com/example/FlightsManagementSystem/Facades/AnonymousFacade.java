package com.example.FlightsManagementSystem.Facades;


import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.dao.*;
import com.example.FlightsManagementSystem.poco.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


public class AnonymousFacade extends FacadeBase {


    public AnonymousFacade() {
    }

    Customers customers = new Customers();
    Tickets tickets = new Tickets();
    LoginToken loginToken;

    public int getUserId(String username) {
        var users = new Users();
       return users.getUserByUsername(username)._id;
    }

    @SneakyThrows
    public String getUserRole(String username)
    {
        var resultSet =users.getRoleAndPassByUsername(username);
        resultSet.next();
        var role=resultSet.getString("role_name");
        resultSet.close();
        users.closeAll();
        return role;
    }

    public AnonymousFacade login(String username, String password) {
        int userRole = -1;
        AnonymousFacade anonymousFacade;
        LoginToken loginToken;
        User user = users.getUserByUsername(username);
        if (user == null) {
            System.out.println("User not exist in the system");
            return null;
        } else {
            if (password.equals(user._password)) {
                userRole = user._userRole;
            } else {
                System.out.println("Password not match !!!");
            }
        }

//       return new LoginToken(user._id,user._username,user._userRole);


        switch (userRole) {
            case 1: {
                Administrator admin = administrators.getAdministratorsByUsername(user._username);
                loginToken = new LoginToken(admin._id, admin._firstName, userRole);
                anonymousFacade = new AdministratorFacade(loginToken);
                break;
            }
            case 2: {
                Customer custumer = customers.getCustomerByUsername(user._username);
                loginToken = new LoginToken(custumer._id, custumer._firstName, userRole);
                anonymousFacade = new CustomerFacade(loginToken);
                break;
            }
            case 3: {
                Airline_company airline = airlineCompanies.getAirlineByUsername(user._username);
                loginToken = new LoginToken(airline._id, airline._name, userRole);
                anonymousFacade = new AirlineFacade(loginToken);
                break;
            }
            default: {
                anonymousFacade = null;
            }
        }
        return anonymousFacade;

    }

    public boolean add_customer(Customer newCustomer, User newUser) {
        User user = users.getUserByUsername(newUser._username);
        if (user != null) {
            newCustomer._userId = user._id;
            try {
                customers.Add(newCustomer);
            } catch (Exception e) {
                e.printStackTrace();
                users.removeUserById(user._id);
            }
        }
        return false;
    }
}
