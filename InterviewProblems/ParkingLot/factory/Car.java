package factory;

import enums.VehicleSize;

public class Car implements Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;

    public Car(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        this.size = VehicleSize.MEDIUM;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }
    public VehicleSize getSize() {
        return size;
    }
}
