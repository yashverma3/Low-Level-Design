package InterviewProblems.ticketBookingSystem;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import InterviewProblems.ticketBookingSystem.entities.Booking;
import InterviewProblems.ticketBookingSystem.entities.Event;
import InterviewProblems.ticketBookingSystem.entities.Seat;
import InterviewProblems.ticketBookingSystem.entities.User;
import InterviewProblems.ticketBookingSystem.enums.SeatType;

public class Main {
    public static void main(String[] args) throws InterruptedException {
       
        EventBookingSystem bookingSystem = EventBookingSystem.getInstance();

        User user1 = new User("Yash");
        User user2 = new User("Rahul");

        Seat s1 = new Seat("A1", SeatType.SILVER, 500);
        Seat s2 = new Seat("A2", SeatType.GOLD, 1000);
        Seat s3 = new Seat("A3", SeatType.PLATINUM, 2000);

        List<Seat> seats = Arrays.asList(s1, s2, s3);

        Event event = new Event(
                "Arijit Singh",
                "Bangalore Palace",
                LocalDateTime.now().plusDays(5),
                seats
        );

        bookingSystem.addEvent(event);

        System.out.println("\n============================");
        System.out.println("SEARCH EVENT TEST");
        System.out.println("============================");

        List<Event> events = bookingSystem.searchEvents(
                "Arijit Singh",
                "Bangalore Palace",
                event.getDateTime()
        );

        System.out.println("Events Found : " + events.size());

        System.out.println("\n============================");
        System.out.println("BOOKING TEST");
        System.out.println("============================");

        Booking booking1 = bookingSystem.bookTickets(user1, event, Arrays.asList(s1, s2));

        if (booking1 != null) {
            System.out.println("Booking Id : " + booking1.getId());
            System.out.println("Total Price : " + booking1.getTotalPrice());
            System.out.println("Status : " + booking1.getStatus());
        }

        System.out.println("\n============================");
        System.out.println("DOUBLE BOOKING TEST");
        System.out.println("============================");

        Booking booking2 = bookingSystem.bookTickets(user2, event, Arrays.asList(s1));

        if (booking2 == null) {
            System.out.println("Double booking prevented successfully");
        }

        System.out.println("\n============================");
        System.out.println("CANCELLATION TEST");
        System.out.println("============================");

        bookingSystem.cancelBooking(booking1.getId());

        System.out.println("Seat A1 Status : " + s1.getStatus());
        System.out.println("Seat A2 Status : " + s2.getStatus());

        System.out.println("\n============================");
        System.out.println("CONCURRENCY TEST");
        System.out.println("============================");

        Seat vipSeat = new Seat("VIP1", SeatType.PLATINUM, 5000);

        Event vipEvent = new Event(
                "Coldplay",
                "Mumbai Stadium",
                LocalDateTime.now().plusDays(20),
                Arrays.asList(vipSeat)
        );

        bookingSystem.addEvent(vipEvent);

        Runnable task = () -> {
            User user = new User(Thread.currentThread().getName());

            Booking booking = bookingSystem.bookTickets(
                    user,
                    vipEvent,
                    Arrays.asList(vipSeat)
            );

            if (booking != null) {
                System.out.println(user.getName() + " booked successfully");
            } else {
                System.out.println(user.getName() + " failed to book");
            }
        };

        Thread t1 = new Thread(task, "User-1");
        Thread t2 = new Thread(task, "User-2");
        Thread t3 = new Thread(task, "User-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }
}