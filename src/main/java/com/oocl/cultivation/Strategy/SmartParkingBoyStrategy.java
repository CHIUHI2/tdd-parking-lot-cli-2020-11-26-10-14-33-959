package com.oocl.cultivation.Strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoyStrategy implements ParkingBoyStrategy {
    public Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> managedParkingLotList) {
        return managedParkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparing(ParkingLot::getAvailableSpaceAmount));
    }
}
