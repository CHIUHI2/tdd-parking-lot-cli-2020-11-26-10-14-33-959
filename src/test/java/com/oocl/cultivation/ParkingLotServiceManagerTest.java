package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotServiceManagerTest extends ParkingBoyTest {
    private final ParkingLotServiceManager manager = this.getParkingBoy();

    @Override
    protected ParkingLotServiceManager getParkingBoy() {
        return new ParkingLotServiceManager();
    }

    @Test
    void should_call_all_parking_boy_park_car_once_when_assign_parking_boy_park_car_given_all_kinds_of_managed_parking_boy_manager_car() throws ParkingLotFullException {
        //given
        ParkingBoy parkingBoy = Mockito.mock(ParkingBoy.class);
        SmartParkingBoy smartParkingBoy = Mockito.mock(SmartParkingBoy.class);
        SuperSmartParkingBoy superSmartParkingBoy = Mockito.mock(SuperSmartParkingBoy.class);

        manager.setManagedParkingBoyList(Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy));

        Car car = new Car();

        //when
        manager.assignParkingBoyParkCar(car, parkingBoy);
        manager.assignParkingBoyParkCar(car, smartParkingBoy);
        manager.assignParkingBoyParkCar(car, superSmartParkingBoy);

        //then
        verify(parkingBoy, times(1)).parkCar(car);
        verify(smartParkingBoy, times(1)).parkCar(car);
        verify(superSmartParkingBoy, times(1)).parkCar(car);
    }
}
