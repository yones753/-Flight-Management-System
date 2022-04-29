package com.example.FlightsManagementSystem.dao;


import com.example.FlightsManagementSystem.poco.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Tickets extends BaseDAO {

    public List<Ticket> getTickets() {
        super.open();
        List<Ticket> ticketsList = new ArrayList<>();
        try {
              ResultSet result = stm.executeQuery("SELECT * FROM \"Tickets\"");
            while (result.next()) {
                ticketsList.add(new Ticket(
                        result.getInt("id"),
                        result.getInt("flight_id"),
                        result.getInt("customer_id")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get tickets,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return ticketsList;
    }

    public Ticket getTicketById(int _id) {
        super.open();
        Ticket ticket = null;
        try {
             ResultSet result = stm.executeQuery("SELECT * FROM \"Tickets\" where \"id\"=" + _id);
            if (result.next()) {
                ticket = new Ticket(
                        result.getInt("id"),
                        result.getInt("flight_id"),
                        result.getInt("customer_id"));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get ticket,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return ticket;
    }

    public boolean addTicket(Ticket newTicket) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Tickets\" (flight_id,customer_id) " +
                    "VALUES " +
                    "(\'" + newTicket._flightId + "\'," +
                    "" + newTicket._customerId + ")");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't add ticket,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeTicketById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Tickets\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't delete tickets,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean isExist(int ticketId) {
        boolean flag = false;
        List<Ticket> list = GetAll();
        for (var item : list) {
            if (item._id == ticketId) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean customerHasTicket(int flightId ,int currentCustomer) {
        var l =get_tickets_by_customer(currentCustomer);
        for(var t : l){
            if(t._flightId == flightId){
                return true;
            }
        }
        return false;
    }

    public boolean updateTicket(Ticket updateTickets, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Tickets\" SET " +
                    "flight_id= \'" + updateTickets._flightId + "\'" +
                    ",customer_id=\'" + updateTickets._customerId + "\'" + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update tickets,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    public List<Ticket> get_tickets_by_customer(int customerId) {
        super.open();
        List<Ticket> ticketsList = new ArrayList<>();
        try {
           ResultSet  result = stm.executeQuery("SELECT * FROM get_tickets_by_customer(" + customerId + ")");
            while (result.next()) {
                ticketsList.add(new Ticket(
                        result.getInt("id"),
                        result.getInt("flight_id"),
                        result.getInt("customer_id")));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get tickets,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return ticketsList;
    }

    public boolean removeTicketByCustomerId(int customerId) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Tickets\" where customer_id=" + customerId);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't delete ticket,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return res != 0;
    }

    @Override
    public Ipoco Get(int id) {
        return getTicketById(id);
    }

    @Override
    public List GetAll() {
        return getTickets();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addTicket((Ticket) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeTicketById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateTicket((Ticket) ipoco, id);
    }
}
