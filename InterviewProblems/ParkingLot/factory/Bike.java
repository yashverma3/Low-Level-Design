package factory;

import enums.VehicleSize;

public class Bike implements Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;

    public Bike(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        this.size = VehicleSize.SMALL;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
    public VehicleSize getSize() {
        return size;
    }
}
