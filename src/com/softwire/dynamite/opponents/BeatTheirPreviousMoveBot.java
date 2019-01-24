package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;

public class BeatTheirPreviousMoveBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        if (gamestate.getRounds().size() == 0) {
            return getRandomMove();
        }

        Round lastRound = gamestate.getRounds().get(gamestate.getRounds().size() - 1);
        return getMoveThatBeats(lastRound.getP2());
    }

    private Move getMoveThatBeats(Move theirLastMove) {
        switch (theirLastMove) {
            case R:
                return Move.P;
            case P:
                return Move.S;
            case S:
                return Move.R;
            case D:
                return Move.W;
            case W:
                return Move.R;
            default:
                throw new RuntimeException("Invalid last move from P2");
        }
    }

    public Move getRandomMove() {
        int randomNumberBetween0And3 = (int) Math.floor(Math.random() * 3);
        Move[] possibleMoves = {Move.R, Move.P, Move.S};

        Move randomMove = possibleMoves[randomNumberBetween0And3];
        return randomMove;
    }
}
