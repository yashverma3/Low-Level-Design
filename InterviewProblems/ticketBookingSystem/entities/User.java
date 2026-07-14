package InterviewProblems.ticketBookingSystem.entities;

import java.util.UUID;

public class User {
    private String id;
    private String name;
   
    public User(String name) {
        this.id = "user_" + UUID.randomUUID();
        this.name = name;
    }
   
    public String getId() {
        return id;
    }
   
    public String getName() {
        return name;
    }
}