package com.codecool.fas.model;
import com.codecool.fas.entity.Flight;

public class FlightQuery {

    private Flight ticket;
    private Flight returnTicket;
    private Integer person;

    public FlightQuery(Flight ticket, Flight returnTicket, Integer person) {
        this.ticket = ticket;
        this.returnTicket = returnTicket;
        this.person = person;
    }

    public Flight getTicket() {
        return ticket;
    }

    public void setTicket(Flight ticket) {
        this.ticket = ticket;
    }

    public Flight getReturnTicket() {
        return returnTicket;
    }

    public void setReturnTicket(Flight returnTicket) {
        this.returnTicket = returnTicket;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }
}
