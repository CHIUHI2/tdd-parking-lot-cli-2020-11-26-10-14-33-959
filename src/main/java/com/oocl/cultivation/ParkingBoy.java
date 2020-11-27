package com.oocl.cultivation;

public class ParkingBoy {
    public Ticket parkCar(Car car, ParkingLot parkingLot) {
        return parkingLot.parkCar(car);
    }

    public Car takeCar(Ticket ticket, ParkingLot parkingLot) throws UnrecognizedTicketException{
        Car car = parkingLot.takeCar(ticket);

        if(car == null) { throw new UnrecognizedTicketException(); }

        return parkingLot.takeCar(ticket);
    }
}
