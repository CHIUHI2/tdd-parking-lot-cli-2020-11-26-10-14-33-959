package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.SuperSmartParkingBoyStrategy;

public class SuperSmartParkingBoy extends ParkingBoy {
    SuperSmartParkingBoy() {
        super(new SuperSmartParkingBoyStrategy());
    }
}
