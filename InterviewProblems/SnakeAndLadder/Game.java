package InterviewProblems.SnakeAndLadder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import InterviewProblems.SnakeAndLadder.entities.Board;
import InterviewProblems.SnakeAndLadder.entities.BoardEntity;
import InterviewProblems.SnakeAndLadder.entities.Dice;
import InterviewProblems.SnakeAndLadder.entities.Player;
import InterviewProblems.SnakeAndLadder.enums.GameStatus;

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private GameStatus status;
    private Player winner;

    private Game(Builder builder) {
        this.board = builder.board;
        this.players = new LinkedList<>(builder.players);
        this.dice = builder.dice;
        this.status = GameStatus.NOT_STARTED;
    }

    // Facade Design Pattern - single point of entrance
    public void play() {
        status = GameStatus.RUNNING;
        while (status == GameStatus.RUNNING) {
            Player currentPlayer = players.poll();
            takeTurn(currentPlayer);
            if (status == GameStatus.RUNNING) {
                players.add(currentPlayer);
            }
        }
        System.out.println("Winner: " + winner.getName());
    }

    private void takeTurn(Player player) {
        int roll = dice.roll();
        int currentPosition = player.getPosition();
        int nextPosition = currentPosition + roll;

        if(nextPosition > board.getSize()) {
            System.out.println("OOPS, you can't make a move");
            return;
        }

        if(nextPosition == board.getSize()) {
            player.setPosition(nextPosition);
            winner = player;
            status = GameStatus.FINISHED;
            System.out.println(player.getName() + " won");
            return;
        }

        int finalPosition = board.getFinalPosition(nextPosition);

        if(finalPosition > nextPosition) { // Ladder
            System.out.println("Wow! there is a ladder. " + player.getName() + " moves to " + finalPosition); 
        } else if(finalPosition < nextPosition) { // Snake
            System.out.println("Oh! there is a snake. " + player.getName() + " moves to " + finalPosition); 
        } else {
            System.out.println(player.getName() + " moves to " + finalPosition); 
        }

        player.setPosition(finalPosition);

        if (roll == 6) {
            System.out.println(player.getName() + " will get another chance.");
            takeTurn(player);
        }
    }

    // Builder Design Pattern
    public static class Builder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public Builder setBoard(int size, List<BoardEntity> entities) {
            this.board = new Board(size, entities);
            return this;
        }

        public Builder setPlayers(List<String> playerNames) {
            this.players = new LinkedList<>();
            for (String name: playerNames) {
                players.add(new Player(name));
            }
            return this;
        }

        public Builder setDice(int minValue, int maxValue) {
            this.dice = new Dice(minValue,maxValue);
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalStateException("Board, Players, and Dice must be set.");
            }
            return new Game(this);
        }
    }
}
