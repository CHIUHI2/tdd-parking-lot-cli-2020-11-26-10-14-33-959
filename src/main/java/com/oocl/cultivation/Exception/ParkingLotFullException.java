package com.oocl.cultivation.Exception;

public class ParkingLotFullException extends Exception {
    public ParkingLotFullException() {
        super("Not enough position.");
    }
}
