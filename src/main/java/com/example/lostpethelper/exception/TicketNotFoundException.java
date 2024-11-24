package com.example.lostpethelper.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Integer id) {
        super("Ticket with id = " + id + " not found");
    }
}
