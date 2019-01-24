package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;

public class DynamiteOnDrawBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        if (numberOfDynamitesPlayed(gamestate) == 100) {
            return getRandomMove();
        }
        if (gamestate.getRounds().size() == 0) {
            return getRandomMove();
        }

        Round lastRound = gamestate.getRounds().get(gamestate.getRounds().size() - 1);
        if (lastRound.getP1() == lastRound.getP2()) {
            return Move.D;
        }
        else {
            return getRandomMove();
        }
    }

    private int numberOfDynamitesPlayed(Gamestate gamestate) {
        int dynamites = 0;
        for (Round round: gamestate.getRounds()) {
            if (round.getP1() == Move.D) {
                dynamites++;
            }
        }
        return dynamites;
    }

    public Move getRandomMove() {
        int randomNumberBetween0And3 = (int) Math.floor(Math.random() * 3);
        Move[] possibleMoves = {Move.R, Move.P, Move.S};

        Move randomMove = possibleMoves[randomNumberBetween0And3];
        return randomMove;
    }
}
