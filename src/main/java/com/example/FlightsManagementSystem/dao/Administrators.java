package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Administrators extends BaseDAO {

    public List<Administrator> getAdministrators() {
        super.open();
        List<Administrator> administratorsList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Adminstrators\"");
            while (result.next()) {
                administratorsList.add(new Administrator(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("user_id")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get admins ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return administratorsList;
    }

    public Administrator getAdministratorsById(int id) {
        super.open();
        Administrator administrator = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Adminstrators\" where id=" + id);
            if (result.next()) {
                administrator = new Administrator(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("user_id"));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            throw new NullPointerException("Can't get admin ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }

        return administrator;
    }

    public Administrator getAdministratorsByName(String name) {
        super.open();
        Administrator administrator = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Adminstrators\" where first_name=\'" + name + "\'");
            result.next();
            administrator = new Administrator(
                    result.getInt("id"),
                    result.getString("first_name"),
                    result.getString("last_name"),
                    result.getInt("user_id"));
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get admin name ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return administrator;
    }

    public Administrator getAdministratorsByUsername(String adminName) {
        super.open();
        Administrator administrator = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_administrator_by_username(\'" + adminName + "\')");
            if(result.next()){
                administrator = new Administrator(
                        result.getInt("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("user_id"));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            throw new NullPointerException("Can't get admin name ,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return administrator;
    }

    public boolean addAdministrator(Administrator newAdministrator) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Adminstrators\" (first_name,last_name,user_id) " +
                    "VALUES " +
                    "(\'" + newAdministrator._firstName + "\',\'" + newAdministrator._lastName + "\'," + newAdministrator._userId + ")");
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException(newAdministrator._firstName + " already exists");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeAdministratorsById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Adminstrators\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't remove admin,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateAdministrators(Administrator administrators, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Adminstrators\" SET " +
                    "first_name= \'" + administrators._firstName + "\'" +
                    ",last_name=\'" + administrators._lastName + "\'" +
                    ",user_id=\'" + administrators._userId + "\'" + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException(administrators._firstName + " already exists");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean isExist(int adminId) {
        boolean flag = false;
        List<Administrator> list = GetAll();
        for (var item : list) {
            if (item._id == adminId) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Ipoco Get(int id) {
        return getAdministratorsById(id);
    }

    @Override
    public List GetAll() {
        return getAdministrators();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addAdministrator((Administrator) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeAdministratorsById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateAdministrators((Administrator) ipoco, id);
    }

}
