package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private Integer capacity = 10;
    private HashMap<Ticket, Car> ticketCarMapping;

    ParkingLot() {
    }

    ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMapping = new HashMap<>();
    }

    public Ticket parkCar(Car car) {
        if (isFull() || car == null || this.ticketCarMapping.containsValue(car)) return null; //need to define exception by confirming requirements

        Ticket ticket = new Ticket(this);
        this.ticketCarMapping.put(ticket, car);

        return ticket;
    }

    public boolean isFull() {
        return this.ticketCarMapping.values().size() == this.capacity;
    }

    public Integer getAvailableSpaceAmount() {
        return this.capacity - this.ticketCarMapping.values().size();
    }

    public Double getAvailabilityRate() {
        return this.getAvailableSpaceAmount().doubleValue() / this.capacity.doubleValue();
    }

    public Car takeCar(Ticket ticket) {
        if (ticket == null) return null;

        Car car = this.ticketCarMapping.get(ticket);
        this.ticketCarMapping.remove(ticket);

        return car;
    }
}
