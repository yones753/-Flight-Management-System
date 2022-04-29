package com.example.FlightsManagementSystem.poco;

public class Airline_company implements Ipoco {
    //DATA
    public int _id;
    public String _name;
    public int _countryId;
    public int _userId;

    //Func
    public Airline_company() {
    }

    public Airline_company(String _name, int _countryId, int _userId) {
        this._name = _name;
        this._countryId = _countryId;
        this._userId = _userId;
    }

    public Airline_company(int _id, String _name, int _countryId, int _userId) {
        this._id = _id;
        this._name = _name;
        this._countryId = _countryId;
        this._userId = _userId;
    }

    @Override
    public String toString() {
        return "Airline_companies{" +
                "_id=" + this._id +
                ", _name='" + this._name + '\'' +
                ", _countryId=" + this._countryId +
                ", _userId=" + this._userId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Airline_company other = (Airline_company) obj;
        return (_id == other._id
                && _name.equals(other._name)
                && _countryId == other._countryId
                && _userId == other._userId);
    }
}
