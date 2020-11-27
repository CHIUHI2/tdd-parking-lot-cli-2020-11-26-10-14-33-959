package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_parking_lot_park_car_given_parking_lot_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.parkCar(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_park_multiple_car_when_parking_lot_park_car_given_parking_lot_capacity_three_car_two() {
        //given
        ParkingLot parkingLot = new ParkingLot(3);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        Ticket ticket1 = parkingLot.parkCar(car1);
        Ticket ticket2 = parkingLot.parkCar(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

    @Test
    void should_park_one_car_when_parking_lot_park_car_given_parking_lot_capacity_one_car_two() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        Ticket ticket1 = parkingLot.parkCar(car1);
        Ticket ticket2 = parkingLot.parkCar(car2);
        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
    }

    @Test
    void should_return_car_when_parking_lot_take_car_given_car_parked_parking_lot_and_valid_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.parkCar(car);
        Car carTaken = parkingLot.takeCar(ticket);
        //then
        assertEquals(car, carTaken);
    }

    @Test
    void should_return_null_when_parking_lot_take_car_given_car_parked_parking_lot_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.parkCar(car);
        parkingLot.takeCar(ticket);
        Car carTaken = parkingLot.takeCar(ticket);
        //then
        assertNull(carTaken);
    }

    @Test
    void should_return_null_when_parking_lot_take_car_given_car_parked_parking_lot_and_invalid_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = new Ticket();
        //when
        parkingLot.parkCar(car);
        Car carTaken = parkingLot.takeCar(ticket);
        //then
        assertNull(carTaken);
    }
}
