package strategy;

import entities.Ticket;
import enums.VehicleSize;

public class BasicFareStrategy implements FareStrategy{
    public int calculateFare(Ticket ticket) {
        VehicleSize size = ticket.getVehicle().getSize();
        int totalHours = (int) ticket.getParkingTime();

        int farePerHour;
        int entryAmount;
        
        switch(size) {
            case VehicleSize.SMALL: 
                farePerHour = 50;
                entryAmount = 20;
                break;
            case VehicleSize.MEDIUM: 
                farePerHour = 150;
                entryAmount = 50;
                break;
            case VehicleSize.LARGE: 
                farePerHour = 300;
                entryAmount = 100;
                break;
            default: 
                farePerHour = 100;
                entryAmount = 30;
        }

        int fare = entryAmount + totalHours * farePerHour;
        return fare;
    }
}