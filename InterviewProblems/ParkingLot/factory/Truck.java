package factory;

import enums.VehicleSize;

public class Truck implements Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;

    public Truck(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        this.size = VehicleSize.LARGE;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
    public VehicleSize getSize() {
        return size;
    }
}
