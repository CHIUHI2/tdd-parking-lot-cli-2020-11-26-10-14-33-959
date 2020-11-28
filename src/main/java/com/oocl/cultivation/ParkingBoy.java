package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    private List<ParkingLot> managedParkingLotList;

    ParkingBoy() {
        this.managedParkingLotList = new ArrayList<>();
    }

    public void setManagedParkingLotList(List<ParkingLot> managedParkingLotList) { this.managedParkingLotList = managedParkingLotList; }

    public List<ParkingLot> getManagedParkingLotList() { return managedParkingLotList; }

    public Ticket parkCar(Car car) throws ParkingLotFullException {
        Optional<ParkingLot> availableParkingLot = this.managedParkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst();

        if (!availableParkingLot.isPresent()) throw new ParkingLotFullException();

        return availableParkingLot.get().parkCar(car);
    }

    public Car takeCar(Ticket ticket) throws UnrecognizedTicketException {
        if(ticket == null || !isManagedParkingLot(ticket.getParkingLot())) return null;

        ParkingLot parkingLot = ticket.getParkingLot();
        Car car = parkingLot.takeCar(ticket);

        if (car == null) throw new UnrecognizedTicketException();

        return car;
    }

    private boolean isManagedParkingLot(ParkingLot parkingLot) { return this.managedParkingLotList.contains(parkingLot); }
}
