package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.SmartParkingBoyParkingStrategy;

public class SmartParkingBoy extends ParkingBoy {
    SmartParkingBoy() {
        super(new SmartParkingBoyParkingStrategy());
    }
}
