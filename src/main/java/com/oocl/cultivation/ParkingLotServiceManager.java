package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;

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

    public Car assignParkingBoyTakeCar(Ticket ticket, ParkingBoy parkingBoy) throws UnrecognizedTicketException {
        return parkingBoy.takeCar(ticket);
    }

    private boolean isManagedParkingBoy(ParkingBoy parkingBoy) { return this.managedParkingBoyList.contains(parkingBoy); }
}
