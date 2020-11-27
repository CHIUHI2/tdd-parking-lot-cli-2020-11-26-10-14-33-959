package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private Integer capacity;
    private HashMap<Ticket, Car> ticketCarMapping;

    ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMapping = new HashMap<>();
    }

    public Ticket parkCar(Car car) {
        if(isFull()) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticketCarMapping.put(ticket, car);

        return ticket;
    }

    private boolean isFull() {
        return ticketCarMapping.values().size() == capacity;
    }
}
