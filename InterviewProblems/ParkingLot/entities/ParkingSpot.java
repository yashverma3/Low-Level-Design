package entities;

import enums.VehicleSize;
import factory.Vehicle;

public interface ParkingSpot {
    public boolean isAvailable();
    public void occupy(Vehicle vehicle);
    public void vacate();
    public String getSpotNumber();
    public VehicleSize getVehicleSize();
}
