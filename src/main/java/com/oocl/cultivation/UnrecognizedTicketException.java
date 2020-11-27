package com.oocl.cultivation;

public class UnrecognizedTicketException extends Exception{
    UnrecognizedTicketException(){
        super("Unrecognized parking ticket.");
    }
}
