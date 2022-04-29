package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class User_roles extends BaseDAO {

    public List<User_role> getUserRoles() {
        super.open();
        List<User_role> userRolesList = new ArrayList<>();
        try {
             ResultSet result = stm.executeQuery("SELECT * FROM \"User_roles\"");
            while (result.next()) {
                userRolesList.add(new User_role(
                        result.getInt("id"),
                        result.getString("role_name")));
            }
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return userRolesList;
    }

    public User_role getUserRoleById(int _id) {
        super.open();
        User_role userRole = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"User_roles\" where \"id\"=" + _id);
            if(result.next()){
                userRole = new User_role(
                        result.getInt("id"), result.getString("role_name"));
            }
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return userRole;
    }


    @Override
    public Ipoco Get(int id) {
      return getUserRoleById(id);
    }

    @Override
    public List GetAll() {
        return getUserRoles();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return false;
    }

    @Override
    public boolean Remove(int id) {
        return false;
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return false;
    }
}
