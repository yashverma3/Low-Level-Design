import factory.Vehicle;
import strategy.FareStrategy;
import enums.VehicleSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entities.LargeSpot;
import entities.MediumSpot;
import entities.ParkingSpot;
import entities.SmallSpot;
import entities.Ticket;

public class ParkingManager {

    private Map<VehicleSize,List<ParkingSpot>> availableSpots;
    private Map<Vehicle,ParkingSpot> vehicleToSpotMapping;

    private static volatile ParkingManager instance;
    private static final Object lock = new Object();

    private ParkingManager() {
        availableSpots = new HashMap<>();
        vehicleToSpotMapping = new HashMap<>();

        // 20 small sized spots
        List<ParkingSpot> smallSpots = new ArrayList<>();
        for(int i=0;i<20;i++) {
            ParkingSpot spot = new SmallSpot(i);
            smallSpots.add(spot);
        }
        availableSpots.put(VehicleSize.SMALL, smallSpots);

        // 10 medium sized spots
        List<ParkingSpot> mediumSpots = new ArrayList<>();
        for(int i=0;i<10;i++) {
            ParkingSpot spot = new MediumSpot(i);
            mediumSpots.add(spot);
        }
        availableSpots.put(VehicleSize.MEDIUM, mediumSpots);

        // 5 large sized spots
        List<ParkingSpot> largeSpots = new ArrayList<>();
        for(int i=0;i<5;i++) {
            ParkingSpot spot = new LargeSpot(i);
            largeSpots.add(spot);
        }
        availableSpots.put(VehicleSize.LARGE, largeSpots);

    }

    public static ParkingManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new ParkingManager();
                }
            }
        }
        return instance;
    }

    public Ticket park(Vehicle vehicle) {

        VehicleSize size = vehicle.getSize();

        boolean isAvailable = isSpotAvailable(size);
        if (!isAvailable) {
            System.out.println("Parking is full. Your vehicle with license number " + vehicle.getLicenseNumber() + " was not parked.");
            return null;
        }

        List<ParkingSpot> spots = availableSpots.get(size);
        ParkingSpot spot = spots.removeLast();

        vehicleToSpotMapping.put(vehicle,spot);
        Ticket ticket = new Ticket(vehicle, spot);
        System.out.println("Your vehicle with license number " + vehicle.getLicenseNumber() + " was parked in the spotNumber " + spot.getSpotNumber());
        return ticket;
    }

    public void unpark(Ticket ticket) {
        ticket.setExitTime(LocalDateTime.now());

        Vehicle vehicle = ticket.getVehicle();
        String licenseNumber = vehicle.getLicenseNumber();
        VehicleSize size = vehicle.getSize();

        FareStrategy fareStrategy = ticket.findFareStrategy();
        int fare = fareStrategy.calculateFare(ticket);
        System.out.println("Your parking fare with license number: " + licenseNumber + " is: " + fare);

        ParkingSpot spot = vehicleToSpotMapping.remove(vehicle);
        availableSpots.get(size).add(spot);
    }

    private boolean isSpotAvailable(VehicleSize size) {
        return availableSpots.containsKey(size) && !availableSpots.get(size).isEmpty();
    }
}
