package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

public class RandomRPSBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        int randomNumberBetween0And3 = (int) Math.floor(Math.random() * 3);
        Move[] possibleMoves = {Move.R, Move.P, Move.S};

        Move randomMove = possibleMoves[randomNumberBetween0And3];
        return randomMove;
    }
}
