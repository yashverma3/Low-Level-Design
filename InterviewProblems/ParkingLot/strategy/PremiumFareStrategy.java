package strategy;

import entities.Ticket;
import enums.VehicleSize;

public class PremiumFareStrategy implements FareStrategy{
    public int calculateFare(Ticket ticket) {
        VehicleSize size = ticket.getVehicle().getSize();
        int totalHours = (int) ticket.getParkingTime();

        int farePerHour;
        int entryAmount;
        
        switch(size) {
            case VehicleSize.SMALL: 
                farePerHour = 100;
                entryAmount = 40;
                break;
            case VehicleSize.MEDIUM: 
                farePerHour = 200;
                entryAmount = 80;
                break;
            case VehicleSize.LARGE: 
                farePerHour = 500;
                entryAmount = 120;
                break;
            default: 
                farePerHour = 150;
                entryAmount = 50;
        }

        int fare = entryAmount + totalHours * farePerHour;
        return fare;
    }
}