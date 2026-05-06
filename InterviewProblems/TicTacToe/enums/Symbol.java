package InterviewProblems.TicTacToe.enums;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('_');

    private final char displayChar;

    Symbol(char displayChar) {
        this.displayChar = displayChar;
    }

    public char getDisplayChar() {
        return displayChar;
    }
}

// X('X') -> internally Symbol X = new Symbol('X'); and this calls constructor
// enums can be accessed using Symbol.X