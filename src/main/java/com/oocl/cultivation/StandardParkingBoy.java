package com.oocl.cultivation;

import com.oocl.cultivation.Strategy.StandardParkingBoyStrategy;

public class StandardParkingBoy extends ParkingBoy {
    StandardParkingBoy() {
        super(new StandardParkingBoyStrategy());
    }
}
