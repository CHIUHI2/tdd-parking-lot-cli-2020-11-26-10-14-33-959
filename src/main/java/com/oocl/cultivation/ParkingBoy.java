package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> managedParkingLotList;

    ParkingBoy(List<ParkingLot> managedParkingLotList) {
        this.managedParkingLotList = managedParkingLotList;
    }

    public Ticket parkCar(Car car) throws ParkingLotFullException{
        ParkingLot availableParkingLot = this.managedParkingLotList.get(0);

        if(availableParkingLot.isFull()) throw new ParkingLotFullException();

        return availableParkingLot.parkCar(car);
    }

    public Car takeCar(Ticket ticket) throws UnrecognizedTicketException{
        Car car = this.managedParkingLotList.get(0).takeCar(ticket);

        if(car == null) throw new UnrecognizedTicketException();

        return car;
    }
}
