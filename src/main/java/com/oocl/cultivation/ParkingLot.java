package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private Integer capacity;
    private HashMap<Ticket, Car> ticketCarMapping;

    ParkingLot() {
        this.capacity = 10;
        this.ticketCarMapping = new HashMap<>();
    }

    ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMapping = new HashMap<>();
    }

    public Ticket parkCar(Car car) {
        if(isFull() || car == null || this.ticketCarMapping.values().contains(car)) return null;

        Ticket ticket = new Ticket();
        this.ticketCarMapping.put(ticket, car);

        return ticket;
    }

    public boolean isFull() {
        return this.ticketCarMapping.values().size() == this.capacity;
    }

    public Car takeCar(Ticket ticket) {
        if(ticket == null) return null;

        Car car = this.ticketCarMapping.get(ticket);
        this.ticketCarMapping.remove(ticket);

        return car;
    }
}
