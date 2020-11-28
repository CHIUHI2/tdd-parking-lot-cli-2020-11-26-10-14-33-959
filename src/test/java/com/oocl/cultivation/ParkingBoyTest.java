package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_call_parking_lot_park_car_once_when_park_car_given_parking_lot_parking_boy_car() throws ParkingLotFullException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        Car car = new Car();
        //when
        parkingBoy.parkCar(car);
        //then
        verify(parkingLot, times(1)).parkCar(car);
    }

    @Test
    void should_call_parking_lot_take_car_once_when_take_car_given_parking_lot_parking_boy_ticket() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        Ticket ticket = new Ticket();
        //when
        assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));
        //then
        verify(parkingLot, times(1)).takeCar(ticket);
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_take_car_given_parking_lot_invalid_ticket_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        Ticket ticket = new Ticket();
        //when
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_take_car_given_car_parked_parking_lot_used_ticket_parking_boy() throws UnrecognizedTicketException, ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        parkingBoy.takeCar(ticket);
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_parking_lot_full_exception_when_park_car_given_parking_lot_capacity_one_car_two_parking_boy() throws ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        parkingBoy.parkCar(car1);
        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> parkingBoy.parkCar(car2));
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    void should_park_two_cars_when_park_car_given_two_parking_lot_each_capacity_one_car_two_parking_boy() throws ParkingLotFullException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        Ticket ticket1 = parkingBoy.parkCar(car1);
        Ticket ticket2 = parkingBoy.parkCar(car2);
        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
    }

    @Test
    void should_park_one_car_when_park_car_given_two_parking_lot_first_full_second_available_car_parking_boy() throws ParkingLotFullException {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(0));
        parkingLotList.add(new ParkingLot(1));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.parkCar(car);
        //then
        assertNotNull(ticket);
    }
}
