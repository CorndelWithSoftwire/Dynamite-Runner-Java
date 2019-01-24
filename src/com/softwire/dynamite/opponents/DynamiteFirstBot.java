package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

public class DynamiteFirstBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        if (gamestate.getRounds().size() < 100) {
            return Move.D;
        }
        else {
            return getRandomMove();
        }
    }

    public Move getRandomMove() {
        int randomNumberBetween0And3 = (int) Math.floor(Math.random() * 3);
        Move[] possibleMoves = {Move.R, Move.P, Move.S};

        Move randomMove = possibleMoves[randomNumberBetween0And3];
        return randomMove;
    }
}
