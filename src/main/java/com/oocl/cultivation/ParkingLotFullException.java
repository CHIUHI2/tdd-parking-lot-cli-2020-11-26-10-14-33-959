package com.oocl.cultivation;

public class ParkingLotFullException extends Exception{
    ParkingLotFullException(){
        super("Not enough position.");
    }
}
