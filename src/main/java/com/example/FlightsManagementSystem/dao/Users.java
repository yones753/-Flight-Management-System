package com.example.FlightsManagementSystem.dao;

import com.example.FlightsManagementSystem.poco.*;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users extends BaseDAO {

    public List<User> getUsers() {
        super.open();
        List<User> usersList = new ArrayList<>();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Users\"");
            while (result.next()) {
                usersList.add(new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getInt("user_role")
                ));
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get users,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return usersList;
    }

    public User getUserById(int _id) {
        super.open();
        User user = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM \"Users\" where \"id\"=" + _id);
            if (result.next()) {
                user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getInt("user_role"));}
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get user,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        } finally {
            super.closeAll();
        }
        return user;
    }

    public User getUserByUsername(String username) {
        super.open();
        User user = null;
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM get_user_by_username(\'" + username + "\')");
            if(result.next()) {
                user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getInt("user_role")
                );
            }
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't get users,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return user;
    }

    @SneakyThrows
    public ResultSet getRoleAndPassByUsername(String username) {
        super.open();
           return stm.executeQuery("SELECT \"User_roles\".\"role_name\",\"Users\".\"password\" FROM \"User_roles\" JOIN \"Users\" ON \"Users\".\"user_role\"=\"User_roles\".\"id\" WHERE \"Users\".\"username\" = \'"+username+ "\'");
    }

    public boolean addUser(User newUser) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO \"Users\" (username,password,email,user_role) " +
                    "VALUES " +
                    "(\'" + newUser._username + "\'," +
                    "\'" + newUser._password + "\'," +
                    "\'" + newUser._email + "\'," +
                    "" + newUser._userRole + ")");
            stm.close();
        } catch (SQLException e) {
            throw new NullPointerException("Can't add user : username/email exists in the system,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    public boolean removeUserById(int _id) {
        super.open();
        int res = 0;
        try {
            res = stm.executeUpdate("delete from \"Users\" where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't delete user,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return res != 0;
    }

    public boolean updateUser(User user, int _id) {
        super.open();
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE \"Users\" SET " +
                    "username= \'" + user._username + "\'" +
                    ",password=\'" + user._password + "\'" +
                    ",email=\'" + user._email + "\'" +
                    ",user_role=\'" + user._userRole + "\'" + "where id=" + _id);
            stm.close();
        } catch (SQLException e) {
            e.getMessage();
            throw new NullPointerException("Can't update user,try again");
        } catch (Exception e1) {
            e1.getMessage();
            throw new NullPointerException("Something wrong!");
        }finally {
            super.closeAll();
        }
        return result != 0;
    }

    @Override
    public Ipoco Get(int id) {
        return getUserById(id);
    }

    @Override
    public List GetAll() {
        return getUsers();
    }

    @Override
    public boolean Add(Ipoco ipoco) {
        return addUser((User) ipoco);
    }

    @Override
    public boolean Remove(int id) {
        return removeUserById(id);
    }

    @Override
    public boolean Update(Ipoco ipoco, int id) {
        return updateUser((User) ipoco, id);
    }
}
