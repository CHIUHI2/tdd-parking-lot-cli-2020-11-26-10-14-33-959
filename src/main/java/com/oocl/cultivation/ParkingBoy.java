package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import com.oocl.cultivation.Strategy.ParkingBoyStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ParkingBoy {
    private List<ParkingLot> managedParkingLotList;
    private ParkingBoyStrategy parkingBoyStrategy;

    ParkingBoy(ParkingBoyStrategy parkingBoyStrategy) {
        this.parkingBoyStrategy = parkingBoyStrategy;
        this.managedParkingLotList = new ArrayList<>();
    }

    public void addManagedParkingLot(ParkingLot parkingLot) {
        this.managedParkingLotList.add(parkingLot);
    }

    public List<ParkingLot> getManagedParkingLotList() {
        return this.managedParkingLotList;
    }

    public Ticket parkCar(Car car) throws ParkingLotFullException {
        Optional<ParkingLot> availableParkingLot = this.parkingBoyStrategy.getAvailableParkingLot(this.managedParkingLotList);

        if (!availableParkingLot.isPresent()) throw new ParkingLotFullException();

        return availableParkingLot.get().parkCar(car);
    }

    public boolean hasAvailableParkingLot() {
        return this.parkingBoyStrategy.getAvailableParkingLot(this.managedParkingLotList).isPresent();
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
