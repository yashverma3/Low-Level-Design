package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import factory.Vehicle;
import strategy.BasicFareStrategy;
import strategy.FareStrategy;
import strategy.PremiumFareStrategy;

public class Ticket {
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private FareStrategy fareStrategy;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();

        if (entryTime.getHour() >= 10 && entryTime.getHour() <=17) {
            fareStrategy = new PremiumFareStrategy();
        } else {
            fareStrategy = new BasicFareStrategy();
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public long getParkingTime() {
        return ChronoUnit.HOURS.between(entryTime, exitTime);
    }

    public int getEntryTimeHour() {
        return entryTime.getHour();
    }

    public FareStrategy findFareStrategy() {
        return fareStrategy;
    }
}
