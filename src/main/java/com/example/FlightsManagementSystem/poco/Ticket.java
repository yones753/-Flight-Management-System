package com.example.FlightsManagementSystem.poco;

public class Ticket implements Ipoco {
    //DATA
    public int _id;
    public int _flightId;
    public int _customerId;

    //Func
    public Ticket() {
    }

    public Ticket(int _id) {
        this._id = _id;
    }

    public Ticket(int _id, int _flightId, int _customerId) {
        this._id = _id;
        this._flightId = _flightId;
        this._customerId = _customerId;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "_id=" + this._id +
                ", _flightId=" + this._flightId +
                ", _customerId=" + this._customerId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Ticket other = (Ticket) obj;
        return (_id == other._id
                && this._flightId == other._flightId
                && this._customerId == other._customerId);
    }
}
