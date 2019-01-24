package com.softwire.dynamite.runner;

import com.softwire.dynamite.bot.Bot;
import com.softwire.dynamite.game.Gamestate;
import com.softwire.dynamite.game.Move;
import com.softwire.dynamite.game.Round;
import com.softwire.dynamite.opponents.*;

import java.util.ArrayList;

public class DynamiteRunner {
    public interface Factory<T> {
        T create();
    }

//    public static void main(String[] args) {
//        Results results = playGames(() -> new RockBot());
//    }

    public static Results playGames(Factory<Bot> playerBotFactory) {

        Bot[] opponentBots = {
                new RockBot(),
                new PaperBot(),
                new ScissorsBot(),
                new RandomRPSBot(),
                new DynamiteFirstBot(),
                new DynamiteOnDrawBot(),
                new BeatTheirPreviousMoveBot()
        };

        Results results = new Results();
        for (Bot opponentBot: opponentBots) {
            Bot playerBot = playerBotFactory.create();

            Result result = runGame(playerBot, opponentBot);
            results.addResults(result);
        }

        System.out.println(results.toString());

        return results;
    }

    private static Result runGame(Bot playerBot, Bot opponentBot) {
        Result result = new Result();
        result.setOpponentName(opponentBot.getClass().getSimpleName());

        // Setup game states
        // We need two because p1 and p2 will be opposite for the two players
        Gamestate playerGameState = setupGameState();
        Gamestate opponentGameState = setupGameState();

        int playerScore = 0;
        int opponentScore = 0;
        int currentRoundWorth = 1;

        while (true) {
            Move playerMove;
            try {
                playerMove = playerBot.makeMove(playerGameState);
            }
            catch (Exception ex) {
                result.setResult("LOSE");
                result.setMessage("Your bot threw an exception: " + ex.getMessage());
                break;
            }
            Move opponentMove;
            try {
                opponentMove = opponentBot.makeMove(opponentGameState);
            }
            catch (Exception ex) {
                result.setResult("WIN");
                result.setMessage("Opponent bot threw an exception: " + ex.getMessage());
                break;
            }

            addRound(playerGameState, opponentGameState, playerMove, opponentMove);

            if (xWinsAgainstY(playerMove, opponentMove)) {
                playerScore += currentRoundWorth;
                currentRoundWorth = 1;
            }
            else if (xWinsAgainstY(opponentMove, playerMove)) {
                opponentScore += currentRoundWorth;
                currentRoundWorth = 1;
            }
            else {
                currentRoundWorth++;
            }

            if (playerScore >= 1000) {
                result.setResult("WIN");
                break;
            }
            if (opponentScore >= 1000) {
                result.setResult("LOSE");
                break;
            }
            if (playerGameState.getRounds().size() >= 2500) {
                result.setResult("DRAW");
                break;
            }
        }

        result.setYourScore(playerScore);
        result.setOpponentScore(opponentScore);
        result.setRounds(playerGameState.getRounds());

        System.out.println(result.toString());

        return result;
    }

    private static boolean xWinsAgainstY(Move x, Move y) {
        if (x == Move.R && (y == Move.S || y == Move.W)) return true;
        if (x == Move.P && (y == Move.R || y == Move.W)) return true;
        if (x == Move.S && (y == Move.P || y == Move.W)) return true;
        if (x == Move.D && (y == Move.R || y == Move.P || y == Move.S)) return true;
        if (x == Move.W && (y == Move.D)) return true;
        return false;
    }

    private static Gamestate setupGameState() {
        Gamestate playerGamestate = new Gamestate();
        playerGamestate.setRounds(new ArrayList<>());
        return playerGamestate;
    }

    private static void addRound(Gamestate playerGamestate, Gamestate opponentGamestate, Move playerMove, Move opponentMove) {
        Round playerRound = new Round();
        playerRound.setP1(playerMove);
        playerRound.setP2(opponentMove);
        playerGamestate.getRounds().add(playerRound);

        Round opponentRound = new Round();
        opponentRound.setP1(opponentMove);
        opponentRound.setP2(playerMove);
        opponentGamestate.getRounds().add(opponentRound);
    }

}
