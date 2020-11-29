package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import com.oocl.cultivation.Strategy.StandardParkingBoyStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotServiceManagerTest extends StandardParkingBoyTest {
    private final ParkingLotServiceManager manager = this.getParkingBoy();

    @Override
    protected ParkingLotServiceManager getParkingBoy() {
        return new ParkingLotServiceManager();
    }

    @Test
    void should_call_parking_boy_park_car_once_when_assign_parking_boy_park_car_given_managed_parking_boy_manager_car() throws ParkingLotFullException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);

        manager.setManagedParkingBoyList(Collections.singletonList(parkingBoy));

        Car car = new Car();

        //when
        manager.assignParkingBoyParkCar(car, parkingBoy);

        //then
        verify(parkingBoy, times(1)).parkCar(car);
    }

    @Test
    void should_not_call_parking_boy_park_car_when_assign_parking_boy_park_car_given_not_managed_parking_boy_manager_car() throws ParkingLotFullException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);

        Car car = new Car();

        //when
        manager.assignParkingBoyParkCar(car, parkingBoy);

        //then
        verify(parkingBoy, times(0)).parkCar(car);
    }

    @Test
    void should_call_parking_boy_take_car_once_when_assign_parking_boy_take_car_given_managed_parking_boy_manager_ticket() throws UnrecognizedTicketException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);

        manager.setManagedParkingBoyList(Collections.singletonList(parkingBoy));

        Ticket ticket = new Ticket(new ParkingLot());

        //when
        manager.assignParkingBoyTakeCar(ticket, parkingBoy);

        //then
        verify(parkingBoy, times(1)).takeCar(ticket);
    }

    @Test
    void should_not_call_parking_boy_take_car_when_assign_parking_boy_take_car_given_not_managed_parking_boy_manager_ticket() throws UnrecognizedTicketException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);

        Ticket ticket = new Ticket(new ParkingLot());

        //when
        manager.assignParkingBoyTakeCar(ticket, parkingBoy);

        //then
        verify(parkingBoy, times(0)).takeCar(ticket);
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_assign_parking_boy_take_car_given_parking_lot_invalid_ticket_managed_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoyStrategy());
        parkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        manager.setManagedParkingBoyList(Collections.singletonList(parkingBoy));

        Ticket ticket = new Ticket(parkingLot);

        //when
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> manager.assignParkingBoyTakeCar(ticket, parkingBoy));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_unrecognized_ticket_exception_when_assign_parking_boy_take_car_given_car_parked_parking_lot_used_ticket_parking_boy() throws UnrecognizedTicketException, ParkingLotFullException {
        //given
        ParkingLot parkingLot = new ParkingLot(1);

        ParkingBoy parkingBoy = new ParkingBoy(new StandardParkingBoyStrategy());
        parkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        manager.setManagedParkingBoyList(Collections.singletonList(parkingBoy));

        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.parkCar(car);

        parkingBoy.takeCar(ticket);
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> manager.assignParkingBoyTakeCar(ticket, parkingBoy));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_get_parking_lot_full_exception_when_assign_parking_boy_park_car_given_parking_lot_full_car_all_kinds_of_managed_parking_boy() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);

        StandardParkingBoy standardParkingBoy = new StandardParkingBoy();
        standardParkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        manager.setManagedParkingBoyList(Arrays.asList(standardParkingBoy, smartParkingBoy, superSmartParkingBoy));

        Car car = new Car();

        //when
        ParkingLotFullException exception1 = assertThrows(ParkingLotFullException.class, () -> manager.assignParkingBoyParkCar(car, standardParkingBoy));
        ParkingLotFullException exception2 = assertThrows(ParkingLotFullException.class, () -> manager.assignParkingBoyParkCar(car, smartParkingBoy));
        ParkingLotFullException exception3 = assertThrows(ParkingLotFullException.class, () -> manager.assignParkingBoyParkCar(car, superSmartParkingBoy));

        //then
        assertEquals("Not enough position.", exception1.getMessage());
        assertEquals("Not enough position.", exception2.getMessage());
        assertEquals("Not enough position.", exception3.getMessage());
    }
}
