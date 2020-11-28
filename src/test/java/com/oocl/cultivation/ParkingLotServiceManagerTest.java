package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import com.oocl.cultivation.Exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotServiceManagerTest extends ParkingBoyTest {
    private final ParkingLotServiceManager manager = this.getParkingBoy();

    @Override
    protected ParkingLotServiceManager getParkingBoy() {
        return new ParkingLotServiceManager();
    }

    @Test
    void should_call_each_parking_boy_park_car_once_when_assign_parking_boy_park_car_given_all_kinds_of_managed_parking_boy_manager_car() throws ParkingLotFullException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        SmartParkingBoy smartParkingBoy = Mockito.mock(SmartParkingBoy.class);
        SuperSmartParkingBoy superSmartParkingBoy = Mockito.mock(SuperSmartParkingBoy.class);

        manager.setManagedParkingBoyList(Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy));

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        //when
        manager.assignParkingBoyParkCar(car1, parkingBoy);
        manager.assignParkingBoyParkCar(car2, smartParkingBoy);
        manager.assignParkingBoyParkCar(car3, superSmartParkingBoy);

        //then
        verify(parkingBoy, times(1)).parkCar(car1);
        verify(smartParkingBoy, times(1)).parkCar(car2);
        verify(superSmartParkingBoy, times(1)).parkCar(car3);
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
    void should_call_parking_boy_take_car_when_assign_parking_boy_take_car_given_managed_parking_boy_manager_ticket() throws UnrecognizedTicketException {
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

        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setManagedParkingLotList(Collections.singletonList(parkingLot));

        manager.setManagedParkingBoyList(Collections.singletonList(parkingBoy));

        Ticket ticket = new Ticket(parkingLot);

        //when
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> manager.assignParkingBoyTakeCar(ticket, parkingBoy));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
