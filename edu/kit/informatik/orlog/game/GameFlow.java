package edu.kit.informatik.orlog.game;

/**
 * The different game states {@link OrlogGame} can go through.
 *
 * @author uxgle
 * @version 1.0
 */
public enum GameFlow {
    /**
     * The game starts.
     */
    START,
    /**
     * The current player's turn is changed.
     */
    CHANGE_TURN,
    /**
     * The dice phase.
     */
    DICE,
    /**
     * End of the dice phase.
     */
    DICE_FINISH,
    /**
     * The god favor phase.
     */
    GOD_FAVOR,
    /**
     * End of the god favor phase.
     */
    GOD_FAVOR_FINISH,
    /**
     * The execution phase.
     */
    EVALUATE,
    /**
     * The game is finished.
     */
    FINISH;
}
