package com.softwire.dynamite.runner;

import com.softwire.dynamite.game.Round;

import java.util.List;

public class Result {
    private String opponentName;
    private String result;
    private int yourScore;
    private int opponentScore;
    private List<Round> rounds;
    private String message = "";

    public String getOpponentName() {
        return opponentName;
    }
    void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getResult() {
        return result;
    }
    void setResult(String result) {
        this.result = result;
    }

    public int getYourScore() {
        return yourScore;
    }
    void setYourScore(int yourScore) {
        this.yourScore = yourScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }
    void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public List<Round> getRounds() {
        return rounds;
    }
    void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return result + " against " + opponentName + " (" + yourScore + " : " + opponentScore + ") " + message;
    }

}
