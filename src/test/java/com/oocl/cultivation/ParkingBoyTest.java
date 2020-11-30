package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    private final ParkingBoy parkingBoy = this.getParkingBoy();

    protected ParkingBoy getParkingBoy() {
        return new StandardParkingBoy();
    }

    @Test
    void should_add_parking_boy_when_add_managed_parking_lot_given_parking_boy_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();

        //when
        parkingBoy.addManagedParkingLot(parkingLot);

        //then
        assertTrue(parkingBoy.getManagedParkingLotList().contains(parkingLot));
    }

    @Test
    void should_call_parking_lot_park_car_once_when_park_car_given_parking_lot_parking_boy_car() throws ParkingLotFullException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);

        parkingBoy.addManagedParkingLot(parkingLot);

        Car car = new Car();

        //when
        parkingBoy.parkCar(car);

        //then
        verify(parkingLot, times(1)).parkCar(car);
    }

    @Test
    void should_call_parking_lot_take_car_once_when_take_car_given_managed_parking_lot_parking_boy_ticket() {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);

        parkingBoy.addManagedParkingLot(parkingLot);

        Ticket ticket = new Ticket(parkingLot);

        //when
        assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));

        //then
        verify(parkingLot, times(1)).takeCar(ticket);
    }

    @Test
    void should_not_call_parking_lot_take_car_when_take_car_given_not_managed_parking_lot_parking_boy_ticket() throws UnrecognizedTicketException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);

        Ticket ticket = new Ticket(parkingLot);

        //when
        parkingBoy.takeCar(ticket);

        //then
        verify(parkingLot, times(0)).takeCar(ticket);
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_take_car_given_parking_lot_invalid_ticket_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        parkingBoy.addManagedParkingLot(parkingLot);

        Ticket ticket = new Ticket(parkingLot);

        //when
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_take_car_given_car_parked_parking_lot_used_ticket_parking_boy() throws UnrecognizedTicketException, ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        parkingBoy.addManagedParkingLot(parkingLot);

        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.parkCar(car);

        parkingBoy.takeCar(ticket);
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> parkingBoy.takeCar(ticket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_parking_lot_full_exception_when_park_car_given_parking_lot_capacity_one_car_two_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);

        parkingBoy.addManagedParkingLot(parkingLot);

        Car car = new Car();

        //when
        ParkingLotFullException exception = assertThrows(ParkingLotFullException.class, () -> parkingBoy.parkCar(car));

        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
