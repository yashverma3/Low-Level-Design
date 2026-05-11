package strategy;

import entities.Ticket;

public interface FareStrategy {
    public int calculateFare(Ticket ticket);
}