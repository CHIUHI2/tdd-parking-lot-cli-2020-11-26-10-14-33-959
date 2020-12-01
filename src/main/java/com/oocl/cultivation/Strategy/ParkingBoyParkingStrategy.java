package com.oocl.cultivation.Strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;
import java.util.Optional;

public interface ParkingBoyParkingStrategy {
    Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> managedParkingLotList);
}
