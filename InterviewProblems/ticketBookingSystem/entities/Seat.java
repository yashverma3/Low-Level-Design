package InterviewProblems.ticketBookingSystem.entities;

import java.util.UUID;

import InterviewProblems.ticketBookingSystem.enums.SeatStatus;
import InterviewProblems.ticketBookingSystem.enums.SeatType;

public class Seat {
    private String id;
    private String seatNumber;
    private SeatType type;
    private SeatStatus status;
    private double price;
   
    public Seat(String seatNumber, SeatType type, double price) {
        this.id = "seat_" + UUID.randomUUID();
        this.seatNumber = seatNumber;
        this.type = type;
        this.status = SeatStatus.AVAILABLE;
        this.price = price;
    }
   
    public synchronized void book() {
        if (status == SeatStatus.AVAILABLE) {
            status = SeatStatus.BOOKED;
            System.out.println("You booked your seat: " + seatNumber + " at price Rs. " + price);
        } else {
            System.out.println("This seat is already booked or reserved");
        }
    }
   
    public synchronized void release() {
        if (status == SeatStatus.BOOKED) {
            status = SeatStatus.AVAILABLE;
            System.out.println("Seat is released");
        } else {
            System.out.println("This seat has not been booked");
        }
    }
   
    public String getId() {
        return id;
    }
   
    public String getSeatNumber() {
        return seatNumber;
    }
   
    public SeatType getType() {
        return type;
    }
   
    public SeatStatus getStatus() {
        return status;
    }
   
    public double getPrice() {
        return price;
    }
}