package com.example.FlightsManagementSystem.Facades;

import com.example.FlightsManagementSystem.dao.*;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;


public abstract class FacadeBase {

    public FacadeBase(){

    }

    Users users = new Users();
    Flights flights = new Flights();
    Administrators administrators = new Administrators();
    Airline_companies airlineCompanies = new Airline_companies();
    Countries countries = new Countries();

    public List<Flight> get_all_flights() {
        return flights.GetAll();
    }

    public Flight get_flight_by_id(int id) {
        return (Flight) flights.Get(id);
    }

    public List<Flight> get_flights_by_parameters(int origin_country_id, int destination_country_id, Timestamp date) {
        return flights.getFlightsByParameters(origin_country_id, destination_country_id, date);
    }

    public List<Airline_company> get_all_airlines() {
        return airlineCompanies.GetAll();
    }

    public Airline_company get_airline_by_id(int id) {
        return (Airline_company) airlineCompanies.Get(id);
    }

    public List<Country> get_all_countries() {
        return countries.GetAll();
    }

    public Country get_country_by_id(int id) {
        return (Country) countries.Get(id);
    }

    public boolean create_new_user(User user) {
        user._userRole = 2;
        if (user._password.trim().length() < 7) {
            System.out.println("Minimum password length of 6 characters ,try again");
        } else {
            return users.Add(user);
        }
        return false;
    }


}
