package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.SuperSmartParkingBoyParkingStrategy;

public class SuperSmartParkingBoy extends ParkingBoy {
    SuperSmartParkingBoy() {
        super(new SuperSmartParkingBoyParkingStrategy());
    }
}
