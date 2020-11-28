package com.oocl.cultivation.Exception;

public class UnrecognizedTicketException extends Exception{
    UnrecognizedTicketException(){
        super("Unrecognized parking ticket.");
    }
}
