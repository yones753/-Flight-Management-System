package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Customers extends BaseDAO {

    public List<Customer> getCustomers() {
        super.open();
        List<Customer> customersList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Customers\"");
            while (result.next()) {
                customersList.add(new Customer(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getInt("user_id")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get customers,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return customersList;
    }

    public Customer getCustomerById(int _id) {
        super.open();
        Customer customer = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Customers\" where \"id\"=" + _id);
            if (result.next()) {
                customer = new Customer(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get customer,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return customer;
    }

    public Customer getCustomerByUserId(int userId) {
        super.open();
        Customer customer = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Customers\" where \"id\"=" + userId);
            if (result.next()) {
                customer = new Customer(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get customer,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return customer;
    }

    public Customer getCustomerByUsername(String username) {
        super.open();
        Customer customer = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_customer_by_username(\'" + username + "\')");
            if (result.next()) {
                customer = new Customer(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get customer,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return customer;
    }

    public boolean addCustomer(Customer newCustomer) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Customers\" (first_name,last_name,address,phone_no,credit_card_no,user_id) " +
                    "VALUES " +
                    "(\'" + newCustomer._firstName + "\'," +
                    "\'" + newCustomer._lastName + "\'," +
                    "\'" + newCustomer._address + "\'," +
                    "\'" + newCustomer._phoneNo + "\'," +
                    "\'" + newCustomer._creditCardNo + "\'," +
                    "" + newCustomer._userId + ")");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't add customer ,Already exists credit card/phone number/username ");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeCustomerById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Customers\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't remove customer ,remove tickets before and try aging");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateCustomer(Customer customer, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Customers\" SET " +
                    "first_name= \'" + customer._firstName + "\'" +
                    ",last_name=\'" + customer._lastName + "\'" +
                    ",address=\'" + customer._address + "\'" +
                    ",phone_no=\'" + customer._phoneNo + "\'" +
                    ",credit_card_no=\'" + customer._creditCardNo + "\'" +
                    ",user_id=\'" + customer._userId + "\'" +
                    "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update customer ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean isExist(int customerId) {
        boolean flag = false;
        List<Customer> list = GetAll();
        for (var item : list) {
            if (item._id == customerId) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Ipoco Get(int id) {
        return getCustomerById(id);
    }

    @Override
    public List GetAll() {
        return getCustomers();
    }

    @Override
    public boolean Add(Ipoco custumer) {
        return addCustomer(((Customer) custumer));
    }

    @Override
    public boolean Remove(int id) {
        return removeCustomerById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateCustomer((Customer) ipoco, id);
    }
}
