package com.oocl.cultivation.Exception;

public class ParkingLotFullException extends Exception{
    ParkingLotFullException(){
        super("Not enough position.");
    }
}
