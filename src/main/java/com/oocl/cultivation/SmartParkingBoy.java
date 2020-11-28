package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
    SmartParkingBoy() {
        super();
    }

    @Override
    public Ticket parkCar(Car car) throws ParkingLotFullException {
        Optional<ParkingLot> availableParkingLotWithMostAvailableSpace = super.getManagedParkingLotList().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparing(ParkingLot::getAvailableSpaceAmount));

        if (!availableParkingLotWithMostAvailableSpace.isPresent()) throw new ParkingLotFullException();

        return availableParkingLotWithMostAvailableSpace.get().parkCar(car);
    }
}
