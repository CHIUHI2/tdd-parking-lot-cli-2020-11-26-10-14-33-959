package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot managedParkingLot;

    ParkingBoy(ParkingLot managedParkingLot) {
        this.managedParkingLot = managedParkingLot;
    }

    public Ticket parkCar(Car car) throws ParkingLotFullException{
        if(this.managedParkingLot.isFull()) throw new ParkingLotFullException();

        return this.managedParkingLot.parkCar(car);
    }

    public Car takeCar(Ticket ticket) throws UnrecognizedTicketException{
        Car car = this.managedParkingLot.takeCar(ticket);

        if(car == null) throw new UnrecognizedTicketException();

        return car;
    }
}
