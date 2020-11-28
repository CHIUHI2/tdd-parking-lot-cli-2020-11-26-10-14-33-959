package com.oocl.cultivation;

public class Ticket {
    private final ParkingLot parkingLot;

    Ticket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
