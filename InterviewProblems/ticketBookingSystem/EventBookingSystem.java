package InterviewProblems.ticketBookingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import InterviewProblems.ticketBookingSystem.entities.Booking;
import InterviewProblems.ticketBookingSystem.entities.Event;
import InterviewProblems.ticketBookingSystem.entities.Seat;
import InterviewProblems.ticketBookingSystem.entities.User;
import InterviewProblems.ticketBookingSystem.enums.SeatStatus;

public class EventBookingSystem {
    private static EventBookingSystem instance;
    private ConcurrentHashMap<String,Event> events;
    private ConcurrentHashMap<String,Booking> bookings;
    private final static Object lock = new Object();
   
    private EventBookingSystem() {
        events = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
    }
   
    public static EventBookingSystem getInstance() {
        if(instance == null) {
            synchronized (lock) {
                if(instance == null) {
                    instance = new EventBookingSystem();
                }
            }
        }
        return instance;
    }
   
    public void addEvent(Event event) {
        events.put(event.getId(), event);
    }
   
    public Event getEvent(String id) {
        return events.get(id);
    }
   
    public List<Event> searchEvents(String artist, String venue, LocalDateTime dateTime) {
        List<Event> filteredEvents = new ArrayList<>();
        for (Map.Entry<String,Event> entry: events.entrySet()) {
            Event event = entry.getValue();
            if (event.getArtist().equals(artist) && event.getVenue().equals(venue) && event.getDateTime().equals(dateTime)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }
   
    public synchronized Booking bookTickets(User user, Event event, List<Seat>seats) {
        for(Seat seat: seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                System.out.println("Selected seats are not available");
                return null;
            }
        }
        Booking booking = new Booking(user,event,seats);
        bookings.put(booking.getId(), booking);
       
        for(Seat seat: seats) {
            seat.book();
        }
       
        booking.paymentSuccessful(); // Write payment logic
       
        System.out.println("Requested seats are booked");
        return booking;
    }
   
    public synchronized void cancelBooking(String id) {
        Booking booking = bookings.get(id);
        booking.cancelBooking();
        System.out.println("Requested booking has been cancelled");
    }
}