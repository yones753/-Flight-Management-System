package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Airline_companies extends BaseDAO {

    public List<Airline_company> getAirlineCompanies() {
        super.open();
        List<Airline_company> airlineCompaniesList = new ArrayList<>();
        try {
             ResultSet result = stm.executeQuery("SELECT * FROM \"Airline_companies\"");
            while (result.next()) {
                airlineCompaniesList.add(new Airline_company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("country_id"),
                        result.getInt("user_id")));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get airline companies,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return airlineCompaniesList;
    }

    public Airline_company getAirlineCompanyById(int _id) {
        super.open();
        Airline_company airlineCompany = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Airline_companies\" where \"id\"=" + _id);
            if (result.next()) {
                airlineCompany = new Airline_company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("country_id"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return airlineCompany;
    }

    public Airline_company getAirlineByUsername(String username) {
        super.open();
        Airline_company airlineCompany = null;
        try {
             ResultSet result = stm.executeQuery("SELECT * FROM get_airline_by_username(\'" + username + "\')");
            if (result.next()) {
                airlineCompany = new Airline_company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("country_id"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return airlineCompany;
    }

    public List<Airline_company> getAirlinesByCountry(int country_id) {
        super.open();
        List<Airline_company> airlineCompaniesCountry = new ArrayList<>();
        try {
             ResultSet result = stm.executeQuery("SELECT * FROM \"Airline_companies\" where country_id=" + country_id);
            while (result.next()) {
                airlineCompaniesCountry.add(new Airline_company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("country_id"),
                        result.getInt("user_id")
                ));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return airlineCompaniesCountry;
    }

    public List<Airline_company> get_airline_by_parameters(int originCountryId,int destinationCountryId){
        super.open();
        List<Airline_company> airlineCompaniesList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_airline_by_parameters("+originCountryId+","+destinationCountryId+")");
            while (result.next()) {
                airlineCompaniesList.add(new Airline_company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("country_id"),
                        result.getInt("user_id")
                ));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return airlineCompaniesList;
    }

    public boolean addAirlineCompany(Airline_company newAirlineCompany) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Airline_companies\" (name,country_id,user_id) " +
                    "VALUES " +
                    "(\'" + newAirlineCompany._name + "\'," +
                    "" + newAirlineCompany._countryId + "," +
                    "" + newAirlineCompany._userId + ")");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't add airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeAirlineCompanyById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Airline_companies\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't delete airline company,delete flights before and try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateAirlineCompany(Airline_company airlineCompany, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Airline_companies\" SET " +
                    "name= \'" + airlineCompany._name + "\'" +
                    ",country_id=\'" + airlineCompany._countryId + "\'" +
                    ",user_id=\'" + airlineCompany._userId + "\'" + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update airline company,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean isExist(int airlineId) {
        boolean flag = false;
        List<Airline_company> list = GetAll();
        for (var item : list) {
            if (item._id == airlineId) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
//            com.example.FlightsManagementSystem.Aspect.AspectFlightsManagement.allAddMethod()
//    @After("allAddMethod()")
    public Ipoco Get(int id) {
        return getAirlineCompanyById(id);
    }

    @Override
    public List GetAll() {
        return getAirlineCompanies();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addAirlineCompany((Airline_company) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeAirlineCompanyById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateAirlineCompany((Airline_company) ipoco, id);
    }
}
