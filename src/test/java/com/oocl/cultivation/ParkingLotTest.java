package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_car_given_parking_lot_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();

        //when
        Ticket ticket = parkingLot.parkCar(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_two_ticket_when_park_car_given_parking_lot_capacity_three_car_two() {
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
    void should_return_one_ticket_one_null_when_park_car_given_parking_lot_capacity_one_car_two() {
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
    void should_return_car_when_take_car_given_car_parked_parking_lot_and_valid_ticket() {
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
    void should_return_null_when_take_car_given_car_parked_parking_lot_and_used_ticket() {
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
    void should_return_null_when_take_car_given_car_parked_parking_lot_and_invalid_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();

        Ticket ticket = new Ticket(parkingLot);

        //when
        parkingLot.parkCar(car);

        Car carTaken = parkingLot.takeCar(ticket);

        //then
        assertNull(carTaken);
    }

    @Test
    void should_return_true_when_check_is_full_given_parking_lot_capacity_0_or_capacity_equal_parked_car_amount() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.parkCar(car);

        //when
        boolean result1 = parkingLot1.isFull();
        boolean result2 = parkingLot2.isFull();

        //then
        assertTrue(result1);
        assertTrue(result2);
    }
}
