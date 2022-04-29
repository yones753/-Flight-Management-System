package com.example.FlightsManagementSystem.poco;

public class User_role implements Ipoco{
    //DATA
    public int _id;
    public String _roleName;

    //FUNC
    public User_role(){}

    public User_role(int _id, String _roleName) {
        this._id = _id;
        this._roleName = _roleName;
    }

    @Override
    public String toString() {
        return "User_roles{" +
                "_id=" + this._id +
                ", _roleName='" + this._roleName + '\'' +
                '}';
    }
}
