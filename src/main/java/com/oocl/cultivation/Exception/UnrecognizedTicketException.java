package com.oocl.cultivation.Exception;

public class UnrecognizedTicketException extends Exception{
    public UnrecognizedTicketException(){
        super("Unrecognized parking ticket.");
    }
}
