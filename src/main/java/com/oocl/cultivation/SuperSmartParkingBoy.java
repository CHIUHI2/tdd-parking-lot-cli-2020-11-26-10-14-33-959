package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy{
    SuperSmartParkingBoy() {
        super();
    }

    @Override
    public Ticket parkCar(Car car) throws ParkingLotFullException {
        Optional<ParkingLot> availableParkingLotWithHighestAvailabilityRate = super.getManagedParkingLotList().stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparing(ParkingLot::getAvailabilityRate));

        if (!availableParkingLotWithHighestAvailabilityRate.isPresent()) throw new ParkingLotFullException();

        return availableParkingLotWithHighestAvailabilityRate.get().parkCar(car);
    }
}
