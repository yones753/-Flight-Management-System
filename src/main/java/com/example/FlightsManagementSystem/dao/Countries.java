package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Countries extends BaseDAO {

    public List<Country> getCountries() {
        super.open();
        List<Country> countriesList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Countries\"");
            while (result.next()) {
                countriesList.add(new Country(
                        result.getInt("id"),
                        result.getString("name")));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get countries ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return countriesList;
    }

    public Country getCountryById(int _id) {
        super.open();
        Country country = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Countries\" where \"id\"=" + _id);
            if (result.next()) {
                country = new Country(result.getInt("id"), result.getString("name"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get country ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return country;
    }

    public boolean isExist(int countryId) {
        for (var c : getCountries()) {
            if (c._id == countryId) return true;
        }
        return false;
    }

    public boolean addCountry(Country newCountry) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Countries\" (name) " +
                    "VALUES" + "(\'" + newCountry._name + "\')");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException(newCountry._name + " already exists");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeCountryById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Countries\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't remove country,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateCountry(Country country, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Countries\" SET name= \'" + country._name + "\'" + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update country,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    @Override
    public Ipoco Get(int id) {
        return getCountryById(id);
    }

    @Override
    public List GetAll() {
        return getCountries();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addCountry((Country) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeCountryById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateCountry((Country) ipoco, id);
    }

}
