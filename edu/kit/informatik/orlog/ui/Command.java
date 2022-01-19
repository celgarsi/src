package edu.kit.informatik.orlog.ui;

import edu.kit.informatik.orlog.entity.GodFavorType;
import edu.kit.informatik.orlog.entity.ItemType;
import edu.kit.informatik.orlog.game.OrlogGame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The different commands to be executed by user interaction.
 *
 * @author uxgle
 * @version 1.0
 */
public enum Command {
    /**
     * The print command returns the current lives and god-power points of both players.
     */
    PRINT("print") {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            return game.getGameStatus();
        }
    },

    /**
     * The roll command simulates the result of the dice phase assigning 6 items to a player.
     */
    ROLL("roll" + Main.COMMAND_SEPARATOR + ItemType.getAllowedTypesPattern() + Main.SEPARATOR +
            ItemType.getAllowedTypesPattern() + Main.SEPARATOR + ItemType.getAllowedTypesPattern() + Main.SEPARATOR +
            ItemType.getAllowedTypesPattern() + Main.SEPARATOR + ItemType.getAllowedTypesPattern() + Main.SEPARATOR +
            ItemType.getAllowedTypesPattern()) {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            final String itemIdString = input.group(Main.FIRST_PARAMETER_INDEX);
            String itemsIdString[] = {input.group(Main.FIRST_PARAMETER_INDEX), input.group(Main.FIRST_PARAMETER_INDEX + 1),
                    input.group(Main.FIRST_PARAMETER_INDEX + 2), input.group(Main.FIRST_PARAMETER_INDEX + 3),
                    input.group(Main.FIRST_PARAMETER_INDEX + 4), input.group(Main.FIRST_PARAMETER_INDEX + 5)};
            try {
                game.rollsToPlayer(itemsIdString);
            } catch (Exception e) {
                return String.format(Main.ERROR, e.getMessage());
            }

            return Main.OK;
        }
    },

    /**
     * The turn command changes the active player in 3 different situations:
     * In the dice phase it authorizes player2 to roll after player1 has rolled and
     * ends the dice phase after player2 has rolled.
     * In the gods' favor phase, the turn command of game end 1 is used to authorize player2
     * to also select a gods' favor.
     * Finally, turn may not be used again until after the execution phase to give control to player1.
     */
    TURN("turn") {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            try {
                game.changeTurn();
            } catch (Exception e) {
                return String.format(Main.ERROR, e.getMessage());
            }

            return game.getCurrentPlayer().getName();
        }
    },

    /**
     * The godfavor command is used to select a godfavor with a given level for a player.
     */
    GOD_FAVOR("godfavor" + Main.COMMAND_SEPARATOR + GodFavorType.getGodFavorsId() + Main.SEPARATOR + Main.GOD_POWER_LEVEL_PATTERN) {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            final String godFavorIdString = input.group(Main.FIRST_PARAMETER_INDEX);
            final int godFavorLevel = Integer.parseInt(input.group(Main.FIRST_PARAMETER_INDEX + 1));

            try {
                game.godFavorToPlayer(godFavorIdString, godFavorLevel);
            } catch (Exception e) {
                return String.format(Main.ERROR, e.getMessage());
            }

            return Main.OK;
        }
    },

    /**
     * The evaluate command starts the execution phase and calculates the remaining lives
     * and god power of both players.
     * It returns the name and the remaining lives and god power of all players or the
     * corresponding output if the game is finished.
     */
    EVALUATE("evaluate") {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            try {
                game.evaluate();
            } catch (Exception e) {
                return String.format(Main.ERROR, e.getMessage());
            }

            if (game.isFinished()) {
                switch (game.getResult()) {
                    case DRAW:
                        return Main.DRAW;
                    case PLAYER1_WINNER:
                        return String.format(Main.WINS, game.getPlayer1().getName());
                    case PLAYER2_WINNER:
                        return String.format(Main.WINS, game.getPlayer2().getName());
                }
                ;
            }

            return game.getGameStatus();
        }
    },

    /**
     * The quit command to exit the program.
     */
    QUIT("quit") {
        @Override
        public String execute(final Matcher input, final OrlogGame game) {
            game.endGame();

            return Main.EMPTY_STRING;
        }
    };

    /**
     * String constant containing an error message if no command could be found in this enum.
     */
    private static final String INVALID_COMMAND = String.format(Main.ERROR, "not a valid command!");

    /**
     * The pattern of this command.
     */
    private final Pattern pattern;

    /**
     * Constructs a new command with the given String.
     *
     * @param pattern The pattern of this command
     */
    Command(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Calls a command if it is found after checking the input against the enum
     * with all available commands.
     *
     * @param input The user input
     * @param game  The instance of the game Orlog to run the command on
     * @return the command that is executed
     * @if no matching command is found returns an error message
     */
    public static void executeMatching(String input, OrlogGame game) {
        boolean invalidCommand = true;

        for (Command command : Command.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                invalidCommand = false;
                System.out.println(command.execute(matcher, game));
                break;
            }
        }

        if (invalidCommand) {
            System.err.println(INVALID_COMMAND);
        }
    }

    /**
     * Executes a command.
     *
     * @param input The line of input of the command
     * @param game  The instance of the Orlog game where the command is executed on
     * @return the result of the command execution that may contain error messages
     */
    abstract String execute(Matcher input, OrlogGame game);

}
