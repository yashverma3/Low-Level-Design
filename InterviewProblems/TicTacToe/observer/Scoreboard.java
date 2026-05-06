package InterviewProblems.TicTacToe.observer;

import java.util.HashMap;

import InterviewProblems.TicTacToe.entities.Game;
import InterviewProblems.TicTacToe.entities.Player;

public class Scoreboard implements GameObserver {
    private final HashMap<String,Integer> scores;

    public Scoreboard() {
        this.scores = new HashMap<>();
    }

    @Override
    public void update(Game game) {
        Player winner = game.getWinner();
        if (winner != null) {
            scores.merge(winner.getName(), 1, Integer::sum);
            System.out.println("Scoreboard updated: " + winner.getName() + " wins!");
        }
    }

    public int getScore(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }

    public void printScoreboard() {
        for (String playerName: scores.keySet()) {
            System.out.println(playerName + ": " + scores.get(playerName));
        }
    }
}
