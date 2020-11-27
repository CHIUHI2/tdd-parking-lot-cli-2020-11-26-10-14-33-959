package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_parking_lot_park_car_given_parking_lot_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.parkCar(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_park_car_when_parking_lot_park_car_given_parking_lot_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.parkCar(car);
        //then
        assertNotNull(ticket);
    }
}
