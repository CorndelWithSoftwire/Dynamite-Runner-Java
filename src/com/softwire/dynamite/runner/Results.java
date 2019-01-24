package com.softwire.dynamite.runner;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private List<Result> results = new ArrayList<>();

    public List<Result> getResults() {
        return results;
    }
    void addResults(Result result) {
        this.results.add(result);
    }

    @Override
    public String toString() {
        long numberWon = getResults().stream().filter(r -> r.getResult().equals("WIN")).count();
        long numberLost = getResults().stream().filter(r -> r.getResult().equals("LOSE")).count();
        long numberDrew = getResults().stream().filter(r -> r.getResult().equals("DRAW")).count();
        return "Won " + numberWon + ", Lost " + numberLost + ", Drew " + numberDrew;
    }
}
