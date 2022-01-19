package edu.kit.informatik.orlog.entity;

/**
 * This interface encapsulates the functionality of {@link StatsItem}.
 *
 * @author uxgle
 * @version 1.0
 */
public interface StatsItemInterface extends ItemInterface {
    /**
     * Gets the melee damage of an item.
     *
     * @return The melee damage of the item
     */
	int getMeleeDamage();

    /**
     * Gets the melee protection of an item.
     *
     * @return The melee protection of the item
     */
    int getMeleeProtection();

    /**
     * Gets the distance damage of an item.
     *
     * @return The distance damage of the item
     */
    int getDistanceDamage();

    /**
     * Gets the melee protection of an item.
     *
     * @return The distance protection of the item
     */
    int getDistanceProtection();
}
