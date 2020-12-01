package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import com.oocl.cultivation.Strategy.ParkingBoyParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ParkingBoy {
    private List<ParkingLot> managedParkingLotList;
    private ParkingBoyParkingStrategy parkingBoyParkingStrategy;

    ParkingBoy(ParkingBoyParkingStrategy parkingBoyParkingStrategy) {
        this.parkingBoyParkingStrategy = parkingBoyParkingStrategy;
        this.managedParkingLotList = new ArrayList<>();
    }

    public void addManagedParkingLot(ParkingLot parkingLot) {
        this.managedParkingLotList.add(parkingLot);
    }

    public List<ParkingLot> getManagedParkingLotList() {
        return this.managedParkingLotList;
    }

    public Ticket parkCar(Car car) throws ParkingLotFullException {
        Optional<ParkingLot> availableParkingLot = this.parkingBoyParkingStrategy.getAvailableParkingLot(this.managedParkingLotList);

        if (!availableParkingLot.isPresent()) throw new ParkingLotFullException();

        return availableParkingLot.get().parkCar(car);
    }

    public boolean hasAvailableParkingLot() {
        return this.parkingBoyParkingStrategy.getAvailableParkingLot(this.managedParkingLotList).isPresent();
    }

    public Car takeCar(Ticket ticket) throws UnrecognizedTicketException {
        if (ticket == null || !isManagedParkingLot(ticket.getParkingLot())) return null;

        ParkingLot parkingLot = ticket.getParkingLot();
        Car car = parkingLot.takeCar(ticket);

        if (car == null) throw new UnrecognizedTicketException();

        return car;
    }

    public boolean isManagedParkingLot(ParkingLot parkingLot) {
        return this.managedParkingLotList.contains(parkingLot);
    }
}
