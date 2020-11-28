package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SuperSmartParkingBoyTest extends ParkingBoyTest{
    private final SuperSmartParkingBoy parkingBoy = this.getParkingBoy();

    @Override
    protected SuperSmartParkingBoy getParkingBoy() {
        return new SuperSmartParkingBoy();
    }

    @Test
    void should_call_parking_lot_with_largest_availability_rate_park_car_once_when_park_car_given_super_smart_parking_boy_parking_lot_car() throws ParkingLotFullException {
        //given
        ParkingLot parkingLotLowerAvailabilityRate = Mockito.mock(ParkingLot.class);
        when(parkingLotLowerAvailabilityRate.getAvailabilityRate()).thenReturn(0.3);

        ParkingLot parkingLotHigherAvailabilityRate = Mockito.mock(ParkingLot.class);
        when(parkingLotHigherAvailabilityRate.getAvailabilityRate()).thenReturn(0.5);

        ParkingLot parkingLotHighestAvailabilityRate = Mockito.mock(ParkingLot.class);
        when(parkingLotHighestAvailabilityRate.getAvailabilityRate()).thenReturn(0.8);

        parkingBoy.setManagedParkingLotList(Arrays.asList(parkingLotLowerAvailabilityRate, parkingLotHigherAvailabilityRate, parkingLotHighestAvailabilityRate));

        Car car = new Car();

        //when
        parkingBoy.parkCar(car);

        //then
        verify(parkingLotLowerAvailabilityRate, times(0)).parkCar(car);
        verify(parkingLotHigherAvailabilityRate, times(0)).parkCar(car);
        verify(parkingLotHighestAvailabilityRate, times(1)).parkCar(car);
    }
}
