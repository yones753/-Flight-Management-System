package com.example.FlightsManagementSystem.poco;

public class Customer implements Ipoco {
    //DATA
    public int _id;
    public String _firstName;
    public String _lastName;
    public String _address;
    public String _phoneNo;
    public String _creditCardNo;
    public int _userId;

    public Customer() {
    }

    public Customer(int _id) {
        this._id = _id;
    }

    public Customer(String _firstName, String _lastName, String _address, String _phoneNo, String _creditCardNo, int _userId) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._address = _address;
        this._phoneNo = _phoneNo;
        this._creditCardNo = _creditCardNo;
        this._userId = _userId;
    }

    public Customer(int _id, String _firstName, String _lastName, String _address, String _phoneNo, String _creditCardNo, int _userId) {
        this._id = _id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._address = _address;
        this._phoneNo = _phoneNo;
        this._creditCardNo = _creditCardNo;
        this._userId = _userId;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "_id=" + _id +
                ", _firstName='" + this._firstName + '\'' +
                ", _lastName='" + this._lastName + '\'' +
                ", _address='" + this._address + '\'' +
                ", _phoneNo='" + this._phoneNo + '\'' +
                ", _creditCardNo='" + this._creditCardNo + '\'' +
                ", _userId=" + this._userId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Customer other = (Customer) obj;
        return (this._id == other._id
                && this._firstName.equals(other._firstName)
                &&this. _lastName.equals(other._lastName)
                && this._phoneNo.equals(other._phoneNo)
                && this._creditCardNo.equals(other._creditCardNo)
                && this._address.equals(other._address)
                && this._userId == other._userId);
    }
}
