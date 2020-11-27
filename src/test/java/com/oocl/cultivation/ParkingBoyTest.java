package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_call_parking_lot_park_car_once_when_parking_boy_park_car_given_parking_lot_parking_boy_car() throws ParkingLotFullException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //when
        parkingBoy.parkCar(car, parkingLot);
        //then
        verify(parkingLot, times(1)).parkCar(car);
    }

    @Test
    void should_call_parking_lot_take_car_once_when_parking_boy_take_car_given_parking_lot_parking_boy_ticket() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy();
        Ticket ticket = new Ticket();
        //when
        assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket, parkingLot));
        //then
        verify(parkingLot, times(1)).takeCar(ticket);
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_parking_boy_take_car_given_parking_lot_invalid_ticket() {
       //given
       ParkingLot parkingLot = new ParkingLot(1);
       ParkingBoy parkingBoy = new ParkingBoy();
       Ticket ticket = new Ticket();
       //when
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket, parkingLot));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_parking_boy_take_car_given_car_parked_parking_lot_used_ticket() throws UnrecognizedTicketException, ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.parkCar(car, parkingLot);
        parkingBoy.takeCar(ticket, parkingLot);
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket, parkingLot));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_parking_lot_full_exception_when_parking_boy_park_car_given_parking_lot_capacity_one_car_two() throws ParkingLotFullException {

        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Car car2 = new Car();
        //when
        parkingBoy.parkCar(car1, parkingLot);
        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> parkingBoy.parkCar(car2, parkingLot));
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
