package com.example.FlightsManagementSystem.Facades;


import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.dao.*;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;



public class AdministratorFacade extends AnonymousFacade {


    Administrators admin=new Administrators();

    public AdministratorFacade(){}

    public AdministratorFacade(LoginToken loginToken) {
        System.out.println("<<<<<<<<<<<<<<<<<<<< Welcome " + loginToken.name + " >>>>>>>>>>>>>>>>>>>>>>>>>");
        this.loginToken = loginToken;
        this.loginToken.id = admin.getAdministratorsByUsername(loginToken.name)._id;
    }

    public boolean identifierToken() {
        return loginToken.role == 1;
    }

    public List<Customer> get_all_customers() {
        if (identifierToken()) {
            List<Customer> list = super.customers.GetAll();
            return list;
        }
        return null;
    }

    public boolean add_customer(Customer newCustomer, User newUser) {
        if (identifierToken()) {
            User user = users.getUserByUsername(newUser._username);
            if (user != null) {
                newCustomer._userId = user._id;
                try {
                    return customers.Add(newCustomer);
                } catch (Exception e) {
                    e.printStackTrace();
                    users.removeUserById(user._id);
                }
            }
        } else {
            System.out.println("Token not match!!");
        }
        return false;
    }

    public boolean add_airline(Airline_company newAirline, User newUser) {
        if (identifierToken()) {
            if (super.countries.isExist(newAirline._countryId)) {
                User user = super.users.getUserByUsername(newUser._username);
                newAirline._userId = user._id;
                if (!super.airlineCompanies.Add(newAirline)) {
                    users.removeUserById(newAirline._userId);
                } else {
                    user._userRole = 3;
                    super.users.updateUser(user, user._id);
                }
            } else {
                System.out.println("CountryId not found");
            }
        } else {
            System.out.println("Token not match");
        }
        return false;
    }

    public boolean add_administrator(Administrator newAdmin, User newUser) {
        if (identifierToken()) {
            User user = super.users.getUserByUsername(newUser._username);
            newAdmin._userId = user._id;
            if (!super.administrators.Add(newAdmin)) {
                users.removeUserById(newAdmin._userId);
            } else {
                user._userRole = 1;
                super.users.updateUser(user, user._id);
            }
        } else {
            System.out.println("Token not match");
        }
        return false;
    }

    public boolean add_country(Country newCountry) {

        if (identifierToken()) {
            return countries.addCountry(newCountry);
        } else {
            System.out.println("Token not match");
        }
        return false;
    }

    public boolean remove_airline(Airline_company airline) {
        if (identifierToken() && super.airlineCompanies.isExist(airline._id)) {
            if (airlineCompanies.getAirlineCompanyById(airline._id)._userId == airline._userId && super.airlineCompanies.Remove(airline._id)) {
                users.removeUserById(airline._userId);
            }
        } else {
            System.out.println("Airline not exist");
        }
            return false;
        }

    public boolean remove_customer(Customer customer) {
        if (identifierToken() && super.customers.isExist(customer._id)) {
            if (super.customers.Remove(customer._id)) {
                users.removeUserById(customer._userId);
            }
        } else {
            System.out.println("Customer not exist");
        }
        return false;

    }

    public boolean remove_administrator(Administrator administrator) {
        if (identifierToken() && loginToken.id != administrator._id) {
            if (admin.Remove(administrator._id)) {
                users.removeUserById(administrator._userId);
            }
        } else {
            System.out.println("Admin not exist");
        }
        return false;
    }
}
