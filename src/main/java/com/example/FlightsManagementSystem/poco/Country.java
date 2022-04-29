package com.example.FlightsManagementSystem.poco;

public class Country implements Ipoco{
    //DATA
    public int _id;
    public String _name;


    //FUNC
    public Country() {
    }

    public Country( String _name) {

        this._name = _name;
    }
    public Country(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "_id=" + this._id +
                ", _name='" + this._name + '\'' +
                '}';
    }
}
