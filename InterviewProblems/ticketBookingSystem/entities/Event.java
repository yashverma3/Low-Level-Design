package InterviewProblems.ticketBookingSystem.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Event {
    private final String id;
    private final String artist;
    private final String venue;
    private final LocalDateTime dateTime;
    private final List<Seat> seats;

    public Event(String artist, String venue, LocalDateTime dateTime, List<Seat> seats) {
        this.id = "event_" + UUID.randomUUID();
        this.artist = artist;
        this.venue = venue;
        this.dateTime = dateTime;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
