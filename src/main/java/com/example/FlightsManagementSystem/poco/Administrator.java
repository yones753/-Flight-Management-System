package com.example.FlightsManagementSystem.poco;

public class Administrator implements Ipoco {
    //DATA
    public int _id;
    public String _firstName;
    public String _lastName;
    public int _userId;

    //FUNC
    public Administrator() {
    }

    public Administrator(String _firstName, String _lastName, int _userId) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._userId = _userId;
    }

    public Administrator(int _id, String _firstName, String _lastName, int _userId) {
        this._id = _id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._userId = _userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "_id=" + this._id +
                ", _firstName='" + this._firstName + '\'' +
                ", _lastName='" + this._lastName + '\'' +
                ", _userId=" + this._userId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Administrator other = (Administrator) obj;
        return (_id == other._id
                && _firstName.equals(other._firstName)
                && _lastName.equals(other._lastName)
                && _userId == other._userId);
    }
}
