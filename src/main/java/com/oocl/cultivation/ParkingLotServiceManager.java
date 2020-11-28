package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managedParkingBoyList;

    ParkingLotServiceManager() {
        super();
        this.managedParkingBoyList = new ArrayList<>();
    }

    public void setManagedParkingBoyList(List<ParkingBoy> managedParkingBoyList) {
        this.managedParkingBoyList = managedParkingBoyList;
    }

    public Ticket assignParkingBoyParkCar(Car car, ParkingBoy parkingBoy) {
        return null;
    }
}
