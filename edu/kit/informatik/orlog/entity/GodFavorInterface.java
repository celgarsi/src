package edu.kit.informatik.orlog.entity;

/**
 * This interface encapsulates the functionality of a {@link GodFavorType}.
 *
 * @author uxgle
 * @version 1.0
 */
public interface GodFavorInterface {

    /**
     * Processes the effect of the owner's god favor against the target.
     *
     * @param owner  The owner of the god favor
     * @param target The target of the god favor used by the owner
     */
    void process(PlayerInterface owner, PlayerInterface target);

    /**
     * Gets the priority of use of the god favor.
     *
     * @return The priority of the god favor
     */
    int getPriority();

    /**
     * Gets the level of the god favor.
     *
     * @return The level of the god favor
     */
    int getLevel();

    /**
     * Sets the level of the god favor from the given level.
     *
     * @param level The given level
     */
    void setLevel(int level);
}
