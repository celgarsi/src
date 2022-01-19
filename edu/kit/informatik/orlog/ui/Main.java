package edu.kit.informatik.orlog.ui;

import edu.kit.informatik.orlog.game.OrlogGame;

import java.util.Scanner;

/**
 * This is the entry point class for the program containing the main method.
 * Contains the entry point and input/output constants.
 *
 * @author uxgle
 * @version 1.0
 */
public final class Main {
    /**
     * The separator, a blank space, between a command and the parameters.
     */
    public static final String COMMAND_SEPARATOR = " ";
    /**
     * Index of the first parameter.
     */
    public static final int FIRST_PARAMETER_INDEX = 1;
    /**
     * The separator between lines of output.
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();
    /**
     * The separator between different parameters for the command line input.
     */
    public static final String SEPARATOR = ";";
    /**
     * An empty string used for some outputs.
     */
    public static final String EMPTY_STRING = "";
    /**
     * The start of an output string for a failed operation.
     */
    public static final String ERROR = "Error, %s";
    /**
     * Exception message in case of utility class instantiation.
     */
    public static final String UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated.";
    /**
     * The output string for a successful operation.
     */
    public static final String OK = "OK";
    /**
     * The error message for an invalid item input.
     */
    public static final String INVALID_ITEM = "not a valid item!";
    /**
     * The String identifier for an item that generates god power.
     */
    public static final char GOD_POWER_ID = 'G';
    /**
     * Index of the identifier for an item that generates god power.
     */
    public static final int GOD_POWER_ID_POSITION = 0;
    /**
     * The error message for an excessive amount of god power generator items.
     */
    public static final String INVALID_NUMBER_GOD_POWER_GENERATORS = "too many god power generator items";
    /**
     * The error message for an invalid god favor input.
     */
    public static final String INVALID_GOD_FAVOR = "not a valid god favor!";
    /**
     * The String pattern for a god favor level.
     */
    public static final String GOD_FAVOR_LEVEL = "(\\d+)";// "^([1-3]){1}$";
    /**
     * The String pattern for the level of a god power.
     */
    public static final String GOD_POWER_LEVEL_PATTERN = "([1-3])";
    /**
     * The string used for the output in case of a victory.
     */
    public static final String WINS = "%s wins";
    /**
     * The string used for the output in case of a draw.
     */
    public static final String DRAW = "draw";

    /**
     * The private constructor avoids object generation.
     */
    private Main() {
        throw new IllegalStateException(UTILITY_CLASS_INSTANTIATION);
    }

    /**
     * The main method of the program. Checks the given input and produces corresponding output.
     *
     * @param args the two player names, life points and god power points
     * @throws Exception throws an error if the passed arguments are invalid
     */
    public static void main(String[] args) throws Exception {
        OrlogGame game = new OrlogGame(args[0]);
        Scanner scanner = null;

        try {
            game.startGame(args[0]);

            do {
                scanner = new Scanner(System.in);
                Command.executeMatching(scanner.nextLine(), game);
            } while (!game.isFinished());

        } catch (Exception e) {
            System.err.println(String.format(ERROR, e.getMessage()));
        } finally {
            scanner.close();
        }

    }
}
