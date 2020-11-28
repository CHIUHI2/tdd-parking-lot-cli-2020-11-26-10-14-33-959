package com.oocl.cultivation;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SmartParkingBoyTest extends ParkingBoyTest{
    private final SmartParkingBoy parkingBoy = this.getParkingBoy();

    @Override
    protected SmartParkingBoy getParkingBoy() {
        return new SmartParkingBoy();
    }

    @Test
    void should_call_parking_lot_with_most_available_space_park_car_once_when_park_car_given_smart_parking_boy_parking_lot_car() throws ParkingLotFullException {
        //given
        ParkingLot parkingLotLessSpace = Mockito.mock(ParkingLot.class);
        when(parkingLotLessSpace.getAvailableSpaceAmount()).thenReturn(1);

        ParkingLot parkingLotMoreSpace = Mockito.mock(ParkingLot.class);
        when(parkingLotMoreSpace.getAvailableSpaceAmount()).thenReturn(2);

        ParkingLot parkingLotMostSpace = Mockito.mock(ParkingLot.class);
        when(parkingLotMostSpace.getAvailableSpaceAmount()).thenReturn(3);

        parkingBoy.setManagedParkingLotList(Arrays.asList(parkingLotLessSpace, parkingLotMoreSpace, parkingLotMostSpace));

        Car car = new Car();

        //when
        parkingBoy.parkCar(car);

        //then
        verify(parkingLotLessSpace, times(0)).parkCar(car);
        verify(parkingLotMoreSpace, times(0)).parkCar(car);
        verify(parkingLotMostSpace, times(1)).parkCar(car);
    }
}
