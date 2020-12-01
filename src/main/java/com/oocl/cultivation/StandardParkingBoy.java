package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.StandardParkingBoyParkingStrategy;

public class StandardParkingBoy extends ParkingBoy {
    StandardParkingBoy() {
        super(new StandardParkingBoyParkingStrategy());
    }
}
