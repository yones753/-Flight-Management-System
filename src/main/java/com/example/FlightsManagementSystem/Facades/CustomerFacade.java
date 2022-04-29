package com.example.FlightsManagementSystem.Facades;


import com.example.FlightsManagementSystem.Login.LoginToken;
import com.example.FlightsManagementSystem.poco.*;
import org.springframework.stereotype.Component;


import java.util.List;


public class CustomerFacade extends AnonymousFacade {
    public CustomerFacade(){}

    public CustomerFacade(LoginToken loginToken) {
        System.out.println("<<<<<<<<<<<<<<<<<<<< Welcome " + loginToken.name + " >>>>>>>>>>>>>>>>>>>>>>>>>");
        super.loginToken = loginToken;
        this.loginToken.id = customers.getCustomerByUsername(loginToken.name)._id;
    }

    public boolean update_customer(Customer customer) {
        Customer customer1 = customers.getCustomerById(customer._id);
        if(customer1 == null){
            System.out.println("Customer id not valid ");
            return false;
        }
        if (customer1._id == loginToken.id && customer1._userId == customer._userId) {
            return super.customers.Update(customer, loginToken.id);
        } else {
            System.out.println("Token not match");
        }
        return false;
    }

    public boolean add_ticket(Ticket ticket) {
        if (loginToken.id == ticket._customerId) {
            if (flights.isExist(ticket._flightId) && !tickets.customerHasTicket(ticket._flightId, loginToken.id)) {
                ticket._customerId = loginToken.id;
                if (super.tickets.Add(ticket)) {
                    int newRemainig = flights.getFlightById(ticket._flightId)._remainingTickets;
                    flights.updateRemainingFlight(--newRemainig, ticket._flightId);
                }
            } else {
                System.out.println("Customer has this flight or flight not exist in system");
            }
        } else {
            System.out.println("Token not match");
        }

        return false;
    }

    public boolean remove_ticket(Ticket ticket) {
        if (loginToken.id == ticket._customerId) {
            if (tickets.isExist(ticket._id) && tickets.customerHasTicket(ticket._flightId, ticket._customerId)) {
                if (super.tickets.Remove(ticket._id)) {
                    int newRemainig = flights.getFlightById(ticket._flightId)._remainingTickets;
                    flights.updateRemainingFlight(++newRemainig, ticket._flightId);
                    return true;
                }
            } else {
                System.out.println("Ticket not found in system or in customer tickets");
            }
        }
        return false;
    }

    public List<Ticket> get_my_tickets() {
        List<Ticket> list = null;
        list = super.tickets.get_tickets_by_customer(loginToken.id);
        return list;
    }

}
