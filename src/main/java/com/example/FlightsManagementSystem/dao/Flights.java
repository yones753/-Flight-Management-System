package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;
import org.aspectj.lang.annotation.After;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Flights extends BaseDAO {

    public List<Flight> getFlights() {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\"");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flights ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public Flight getFlightById(int _id) {
        super.open();
        Flight flight = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\" where \"id\"=" + _id);
            if (result.next()) {
                flight = new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flight;
    }

    public boolean addFlight(Flight newFlight) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Flights\" (airline_company_id," +
                    "origin_country_id,destination_country_id," +
                    "departure_time,landing_time,remaining_tickets) " +
                    "VALUES " +
                    "(\'" + newFlight._airlineCompanyId + "\'," +
                    "\'" + newFlight._originCountryId + "\'," +
                    "\'" + newFlight._destinationCountryId + "\'," +
                    "\'" + newFlight._departureTime + "\'," +
                    "\'" + newFlight._landingTime + "\'," +
                    "" + newFlight._remainingTickets + ")");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't add flights ,Already exists");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }

        return result != 0;
    }

    public boolean removeFlightById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Flights\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't delete flight,before deleting tickets");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateFlight(Flight flight, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Flights\" SET " +
                    "airline_company_id= \'" + flight._airlineCompanyId + "\'" +
                    ",origin_country_id=\'" + flight._originCountryId + "\'" +
                    ",destination_country_id=\'" + flight._destinationCountryId + "\'" +
                    ",departure_time=\'" + flight._departureTime + "\'" +
                    ",landing_time=\'" + flight._landingTime + "\'" +
                    ",remaining_tickets=\'" + flight._remainingTickets + "\'"
                    + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }

        return result != 0;
    }

    public boolean updateRemainingFlight(int newRemaining, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Flights\" SET " +
                    "remaining_tickets=\'" + newRemaining + "\'"
                    + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean isAlreadyExist(Flight flight) {
        super.open();
        List<Flight> list = getFlights();
        for (var item : list) {
            if ((flight._departureTime.equals(item._departureTime)) &&
                    (flight._landingTime.equals(item._landingTime)) &&
                    (flight._airlineCompanyId == item._airlineCompanyId) &&
                    (flight._originCountryId == item._originCountryId) &&
                    (flight._destinationCountryId == item._destinationCountryId)) {
                System.out.println("Flight already exist");
                return false;
            }
        }
        return true;
    }

    public boolean validTime(Flight flight) {
        if (flight._departureTime.before(flight._landingTime)) {
            return true;
        } else {
            System.out.println("Time not valid");
        }
        return false;
    }

    public boolean isExist(int flightId) {
        var list = getFlights();
        for (var f : list) {
            if (f._id == flightId) {
                return true;
            }
        }
        return false;
    }

    public boolean validCountries(Flight flight) {
        Countries countries = new Countries();
        if (countries.isExist(flight._originCountryId) && countries.isExist(flight._destinationCountryId)) {
            return flight._originCountryId != flight._destinationCountryId;
        } else {
            System.out.println("It is impossible for the country to be the same destination country");
        }
        return false;
    }

    public List<Flight> getFlightsByParameters(int origin_country_id, int destination_country_id, Timestamp date) {
        super.open();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_flights_by_parameters(" + origin_country_id + "," + destination_country_id + "," + "\'" + date + "\'" + ")");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> get_flights_by_airline_id(int id) {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_flights_by_airline_id(" + id + ")");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> getFlightsByOriginCountryId(int country_id) {
        super.open();
        List<Flight> flightsListByCountry = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\" where origin_country_id=" + country_id);
            while (result.next()) {
                flightsListByCountry.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsListByCountry;
    }

    public List<Flight> getFlightsByDestinationCountryId(int country_id) {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\" where destination_country_id=" + country_id);
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> getFlightsByDepartureDate(Timestamp date) {
        super.open();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\" where DATE(\"departure_time\")=" + "\'" + dateFormat.format(date) + "\'");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> getFlightsByLandingDate(Timestamp date) {
        super.open();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Flights\" where DATE(\"landing_time\")=" + "\'" + dateFormat.format(date) + "\'");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> getFlightsByCustomer(Customer customer) {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_flights_by_customer_id(" + customer._id + ")");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> get_departure_flights(int countryId) {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_departure_flights(" + countryId + ")");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    public List<Flight> get_arrival_flights(int countryId) {
        super.open();
        List<Flight> flightsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_arrival_flights(" + countryId + ")");
            while (result.next()) {
                flightsList.add(new Flight(
                        result.getInt("id"),
                        result.getInt("airline_company_id"),
                        result.getInt("origin_country_id"),
                        result.getInt("destination_country_id"),
                        result.getTimestamp("departure_time"),
                        result.getTimestamp("landing_time"),
                        result.getInt("remaining_tickets")));
            }
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            throw new NullPointerException("Can't get flight ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return flightsList;
    }

    @Override
    public Ipoco Get(int id) {
        return getFlightById(id);
    }

    @Override
    public List GetAll() {
        return getFlights();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addFlight((Flight) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeFlightById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateFlight((Flight) ipoco, id);
    }
}
