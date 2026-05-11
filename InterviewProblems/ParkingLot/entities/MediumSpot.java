package entities;

import enums.VehicleSize;
import factory.Vehicle;

public class MediumSpot implements ParkingSpot {
    private String spotNumber;
    private Vehicle vehicle;

    public MediumSpot(int spotNumber) {
        this.spotNumber = "Medium:" + spotNumber;
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
