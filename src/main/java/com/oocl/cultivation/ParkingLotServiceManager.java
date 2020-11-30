package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import com.oocl.cultivation.Strategy.StandardParkingBoyStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends StandardParkingBoy {
    private List<ParkingBoy> managedParkingBoyList;

    ParkingLotServiceManager() {
        super();
        this.managedParkingBoyList = new ArrayList<>();
    }

    public void setManagedParkingBoyList(List<ParkingBoy> managedParkingBoyList) {
        this.managedParkingBoyList = managedParkingBoyList;
    }

    //todo
    public Ticket assignParkingBoyParkCar(Car car) throws ParkingLotFullException {
        if (car == null) return null; //need to define exception by confirming requirements

        return managedParkingBoyList.stream()
                .filter(ParkingBoy::hasAvailableParkingLot)
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .parkCar(car);
    }

    //todo
    public Car assignParkingBoyTakeCar(Ticket ticket) throws UnrecognizedTicketException {
        if (ticket == null) return null; //need to define exception by confirming requirements

        return managedParkingBoyList.stream()
                .filter(parkingBoy -> parkingBoy.isManagedParkingLot(ticket.getParkingLot()))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .takeCar(ticket);
    }
}
