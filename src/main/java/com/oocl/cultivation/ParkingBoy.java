package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> managedParkingLotList;

    ParkingBoy(List<ParkingLot> managedParkingLotList) {
        this.managedParkingLotList = managedParkingLotList;
    }

    public Ticket parkCar(Car car) throws ParkingLotFullException{
        Optional<ParkingLot> availableParkingLot = this.managedParkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst();

        if(!availableParkingLot.isPresent()) throw new ParkingLotFullException();

        return availableParkingLot.get().parkCar(car);
    }

    public Car takeCar(Ticket ticket) throws UnrecognizedTicketException{
        Optional<ParkingLot> respectiveParkingLot = this.managedParkingLotList.stream()
                .filter(parkingLot -> parkingLot.takeCar(ticket) != null)
                .findFirst();

        if(!respectiveParkingLot.isPresent()) throw new UnrecognizedTicketException();

        return respectiveParkingLot.get().takeCar(ticket);
    }
}
