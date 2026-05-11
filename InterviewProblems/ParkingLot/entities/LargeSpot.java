package entities;

import enums.VehicleSize;
import factory.Vehicle;

public class LargeSpot implements ParkingSpot {
    private String spotNumber;
    private Vehicle vehicle;

    public LargeSpot(int spotNumber) {
        this.spotNumber = "Large:" + spotNumber;
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void vacate() {
        vehicle = null;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public VehicleSize getVehicleSize() {
        return vehicle.getSize();
    }
}
