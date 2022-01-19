package edu.kit.informatik.orlog.entity;

/**
 * This interface encapsulates the functionality of {@link ItemType}.
 *
 * @author uxgle
 * @version 1.0
 */
public interface ItemInterface {
    /**
     * Gets the item id.
     *
     * @return The item id
     */
    String getItemId();

    /**
     * Gets the god power generation points of an item.
     *
     * @return the god power generation points of the item
     */
    int getGodPowerGenerator();

    /**
     * Processes the effect of the owner's items against the target.
     *
     * @param owner  The owner of the item
     * @param target The target of the item used by the owner
     */
    void process(PlayerInterface owner, PlayerInterface target);
}
