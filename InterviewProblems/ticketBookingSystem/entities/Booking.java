package InterviewProblems.ticketBookingSystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import InterviewProblems.ticketBookingSystem.enums.BookingStatus;

public class Booking {
    private String id;
    private User user;
    private Event event;
    private List<Seat> seats;
    private double totalPrice;
    private BookingStatus status;
   
    public Booking(User user, Event event, List<Seat> seats) {
        this.id = "bkg_" + UUID.randomUUID();
        this.user = user;
        this.event = event;
        this.seats = new ArrayList<>(seats);
        this.totalPrice = calculatePrice(seats);
        this.status = BookingStatus.PENDING;
    }
   
    private double calculatePrice(List<Seat> seats) {
        double price = 0;
        for(Seat seat: seats) {
            price += seat.getPrice();
        }
        return price;
    }
   
    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }
   
    public void paymentSuccessful() {
        status = BookingStatus.CONFIRMED;
    }
   
    public void cancelBooking() {
        status = BookingStatus.CANCELLED;
        for(Seat seat: seats) {
            seat.release();
        }
    }
}