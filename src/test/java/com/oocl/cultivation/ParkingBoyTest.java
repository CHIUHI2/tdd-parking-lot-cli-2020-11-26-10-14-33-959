package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_call_parking_lot_park_car_once_when_parking_boy_park_car_given_parking_lot_parking_boy_car() {
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
        Car car = new Car();
        //when
        Ticket ticket = parkingBoy.parkCar(car, parkingLot);
        parkingBoy.takeCar(ticket, parkingLot);
        //then
        verify(parkingLot, times(1)).takeCar(ticket);
    }
}
