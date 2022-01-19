package edu.kit.informatik.orlog.game;

import edu.kit.informatik.orlog.entity.*;
import edu.kit.informatik.orlog.ui.Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a variation of the dice game Orlog.
 *
 * @author uxgle
 * @version 1.0
 */
public class OrlogGame {

    //TODO Init data patter
    private final String INIT_DATA_PATTERN = "^((.+);){2}\\d+;\\d+$";
    private final String INIT_DATA_PATTERN_GROUPS = "(\\w+);(\\w+);(\\d+);(\\d+)";
    private final int PLAYER_ONE_TURN = 0;
    private final int PLAYER_TWO_TURN = 1;

    //TODO Scanner?
    Scanner sc = new Scanner(System.in);
    private Player player1;
    private Player player2;
    private GameFlow currentGameState;
    private int turn;
    private ResultType result;

    /**
     * The constructor instantiates a new {@link OrlogGame}.
     *
     * @param gameOptions The String containing the players names and their starting life- and god power points
     * @throws Exception occurs if the gameOptions String does not match the expected format
     */
    public OrlogGame(String gameOptions) throws Exception {
        startGame(gameOptions);
    }

    public void startGame(String gameOptions) throws Exception {
        currentGameState = GameFlow.START;
        turn = 0;
        result = null;

        if (!Pattern.matches(INIT_DATA_PATTERN_GROUPS, gameOptions)) {
            throw new Exception("The data is invalid");
        }
        initPlayers(gameOptions);
    }

    private void initPlayers(String gameOptions) throws Exception {
        Pattern pattern = Pattern.compile(INIT_DATA_PATTERN);
        Matcher matcher = pattern.matcher(gameOptions);
        matcher.matches();

        Long initLifePoints = Long.parseLong(matcher.group(3));
        Integer initEnergyPoints = Integer.parseInt(matcher.group(4));

        player1 = new Player(matcher.group(1), initLifePoints, initEnergyPoints);
        player2 = new Player(matcher.group(2), initLifePoints, initEnergyPoints);
    }

    public String getGameStatus() {
        String gameStatus = player1.getStats() + Main.LINE_SEPARATOR + player2.getStats();

        return gameStatus;
    }

    public void rollsToPlayer(String[] itemsIdString) throws Exception {
        checkGameFlow(GameFlow.DICE);

        currentGameState = GameFlow.DICE;

        ArrayList<ItemInterface> itemsList = new ArrayList<ItemInterface>();
        int numberOfPowerGenerators = 0;
        for (String itemString : itemsIdString) {
            if (itemString.charAt(Main.GOD_POWER_ID_POSITION) == Main.GOD_POWER_ID) {
                numberOfPowerGenerators++;
                if (numberOfPowerGenerators >= 5) {
                    throw new Exception(Main.ERROR + Main.INVALID_NUMBER_GOD_POWER_GENERATORS);
                }
            }
            itemsList.add(ItemType.compileFromString(itemString).buildItem());
        }
        getCurrentPlayer().setItems(itemsList);

    }

    public void changeTurn() throws Exception {
        checkGameFlow(GameFlow.CHANGE_TURN);

        if (currentGameState == GameFlow.DICE && turn == PLAYER_TWO_TURN) {
            currentGameState = GameFlow.DICE_FINISH;
        }

        if (currentGameState == GameFlow.GOD_FAVOR && turn == PLAYER_TWO_TURN) {
            currentGameState = GameFlow.GOD_FAVOR_FINISH;
        }

        turn = PLAYER_ONE_TURN == turn ? PLAYER_TWO_TURN : PLAYER_ONE_TURN;
    }

    public void godFavorToPlayer(String godFavorId, int level) throws Exception {
        checkGameFlow(GameFlow.GOD_FAVOR);
        currentGameState = GameFlow.GOD_FAVOR;

        GodFavorInterface godFavor = GodFavorType.compileFromString(godFavorId).buildGodFavor(level);
        getCurrentPlayer().setGodFavor(godFavor);
    }

    public void evaluate() throws Exception {
        checkGameFlow(GameFlow.EVALUATE);
        currentGameState = GameFlow.EVALUATE;

        evaluateItems();
        checkPlayersLife();
        evaluateGodFavor();

    }

    public Player getCurrentPlayer() {
        if (turn == PLAYER_ONE_TURN) {
            return player1;
        }
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private void evaluateItems() {
        evaluateItemsForPlayer(player1, player2);
        evaluateItemsForPlayer(player2, player1);
    }

    private void evaluateItemsForPlayer(PlayerInterface owner, PlayerInterface target) {
        ArrayList<ItemInterface> items = owner.getItems();

        for (ItemInterface item : items) {
            item.process(owner, target);
        }
    }

    private void checkPlayersLife() {

        if (isPlayerOutOfCombat(player1) && isPlayerOutOfCombat(player2)) {
            result = ResultType.DRAW;

            currentGameState = GameFlow.FINISH;
        } else if (isPlayerOutOfCombat(player2)) {
            result = ResultType.PLAYER1_WINNER;

            currentGameState = GameFlow.FINISH;
        } else if (isPlayerOutOfCombat(player1)) {
            result = ResultType.PLAYER2_WINNER;

            currentGameState = GameFlow.FINISH;
        }
    }

    public boolean isPlayerOutOfCombat(PlayerInterface player) {
        return player.getLifePoints() <= 0;
    }

    private void evaluateGodFavor() {
        if (player2.getGodFavor().getPriority() > player1.getGodFavor().getPriority()) {
            evaluateGodFavorForPlayer(player2, player1);
        } else {
            evaluateGodFavorForPlayer(player1, player2);
        }
    }

    private void evaluateGodFavorForPlayer(PlayerInterface owner, PlayerInterface target) {
        owner.getGodFavor().process(owner, target);
    }

    public void endGame() {
        currentGameState = GameFlow.FINISH;
    }

    public boolean isFinished() {
        return currentGameState.equals(GameFlow.FINISH);
    }

    public ResultType getResult() {
        return result;
    }

    private void checkGameFlow(GameFlow gameState) throws Exception {
        switch (gameState) {
            case CHANGE_TURN:
                if (this.currentGameState.equals(GameFlow.START) ||
                        this.currentGameState.equals(GameFlow.DICE_FINISH) ||
                        this.currentGameState.equals(GameFlow.GOD_FAVOR_FINISH) ||
                        this.currentGameState.equals(GameFlow.FINISH)) {
                    throw new Exception("Cannot change turn at the current game state");
                }
                break;
            case DICE:
                if (!this.currentGameState.equals(GameFlow.START) && !this.currentGameState.equals(GameFlow.DICE)) {
                    throw new Exception("Cannot roll at the current game state");
                }
                break;

            case GOD_FAVOR:
                if (this.currentGameState.equals(GameFlow.GOD_FAVOR_FINISH) ||
                        (!this.currentGameState.equals(GameFlow.DICE_FINISH) &&
                                !this.currentGameState.equals(GameFlow.GOD_FAVOR))) {
                    throw new Exception("Cannot roll at the current game state");
                }
                break;
            case EVALUATE:
                if (!this.currentGameState.equals(GameFlow.DICE_FINISH) &&
                        !this.currentGameState.equals(GameFlow.GOD_FAVOR) &&
                        !this.currentGameState.equals(GameFlow.GOD_FAVOR_FINISH)) {
                    throw new Exception("Incorrect do this at the current game state");
                }
                break;
            default:
                break;
        }
    }
}
