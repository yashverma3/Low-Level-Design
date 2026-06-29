package InterviewProblems.ATM.entity;

public class Card {
    private String number;
    private int pin;

    public Card(String number, int pin) {
        this.number = number;
        this.pin = pin;
    }

    public boolean validatePIN(int pin) {
        return this.pin == pin;
    }
}