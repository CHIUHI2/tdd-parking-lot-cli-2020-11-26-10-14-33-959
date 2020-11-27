package com.oocl.cultivation;

public class ParkingBoy {
    public Ticket parkCar(Car car, ParkingLot parkingLot) {
        return parkingLot.parkCar(car);
    }

    public Car takeCar(Ticket ticket, ParkingLot parkingLot) throws UnrecognizedTicketException{
        return parkingLot.takeCar(ticket);
    }
}
