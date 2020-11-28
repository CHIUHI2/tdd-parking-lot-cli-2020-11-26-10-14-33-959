package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;

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

    public Ticket assignParkingBoyParkCar(Car car, ParkingBoy parkingBoy) throws ParkingLotFullException {
        if(!isManagedParkingBoy(parkingBoy)) return null;

        return parkingBoy.parkCar(car);
    }

    private boolean isManagedParkingBoy(ParkingBoy parkingBoy) { return this.managedParkingBoyList.contains(parkingBoy); }
}
