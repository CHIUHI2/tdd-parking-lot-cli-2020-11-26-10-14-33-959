package com.oocl.cultivation.Strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;
import java.util.Optional;

public class StandardParkingBoyParkingStrategy implements ParkingBoyParkingStrategy {
    public Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> managedParkingLotList) {
        return managedParkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst();
    }
}
