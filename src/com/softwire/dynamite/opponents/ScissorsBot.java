package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

public class ScissorsBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        return Move.S;
    }
}
