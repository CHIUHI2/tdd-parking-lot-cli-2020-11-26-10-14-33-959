package com.oocl.cultivation;

import com.oocl.cultivation.Exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StandardParkingBoyTest extends ParkingBoyTest{
    private final StandardParkingBoy parkingBoy = this.getParkingBoy();

    @Override
    protected StandardParkingBoy getParkingBoy() {
        return new StandardParkingBoy();
    }

    @Test
    void should_park_two_cars_when_park_car_given_two_parking_lot_each_capacity_one_car_two_parking_boy() throws ParkingLotFullException {
        //given
        parkingBoy.setManagedParkingLotList(Arrays.asList(new ParkingLot(1), new ParkingLot(1)));

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
        parkingBoy.setManagedParkingLotList(Arrays.asList(new ParkingLot(0), new ParkingLot(1)));

        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.parkCar(car);

        //then
        assertNotNull(ticket);
    }
}
