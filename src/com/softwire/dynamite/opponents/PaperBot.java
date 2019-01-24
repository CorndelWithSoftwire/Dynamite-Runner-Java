package com.softwire.dynamite.opponents;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;

public class PaperBot implements Bot {
    @Override
    public Move makeMove(Gamestate gamestate) {
        return Move.P;
    }
}
