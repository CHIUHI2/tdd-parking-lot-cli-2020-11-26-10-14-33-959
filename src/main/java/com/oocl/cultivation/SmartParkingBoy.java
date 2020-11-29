package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.SmartParkingBoyStrategy;

public class SmartParkingBoy extends ParkingBoy {
    SmartParkingBoy() {
        super(new SmartParkingBoyStrategy());
    }
}
