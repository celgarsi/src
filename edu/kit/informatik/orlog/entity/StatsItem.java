package edu.kit.informatik.orlog.entity;

/**
 * This class encapsulates the stats of an {@link ItemType}.
 *
 * @author uxgle
 * @version 1.0
 */
public class StatsItem implements StatsItemInterface {

    private final String itemId;
    private final int meleeDamage;
    private final int meleeProtection;
    private final int distanceDamage;
    private final int distanceProtection;
    private final int godPowerGenerator;

    /**
     * The constructor instantiates a new StatsItem with a given id, melee damage, melee protection, distance damage,
     * distance protection and god power points generation.
     *
     * @param itemId             The given id
     * @param meleeDamage        The given melee damage
     * @param meleeProtection    The given melee protection
     * @param distanceDamage     The given distance damage
     * @param distanceProtection The given distance protection
     * @param godPowerGenerator  The given god power points generation
     */
    public StatsItem(String itemId, int meleeDamage, int meleeProtection, int distanceDamage, int distanceProtection, int godPowerGenerator) {
        this.itemId = itemId;
        this.meleeDamage = meleeDamage;
        this.meleeProtection = meleeProtection;
        this.distanceDamage = distanceDamage;
        this.distanceProtection = distanceProtection;
        this.godPowerGenerator = godPowerGenerator;
    }

    /**
     * Gets the amount of melee damage of an StatsItem.
     *
     * @return The melee damage of the StatsItem
     */
    public int getMeleeDamage() {
        return meleeDamage;
    }

    /**
     * Gets the amount of melee protection of an StatsItem.
     *
     * @return The melee protection of the StatsItem
     */
    public int getMeleeProtection() {
        return meleeProtection;
    }

    /**
     * Gets the amount of distance damage of an StatsItem.
     *
     * @return The melee distance of the StatsItem
     */
    public int getDistanceDamage() {
        return distanceDamage;
    }

    /**
     * Gets the amount of distance protection of an StatsItem.
     *
     * @return The distance protection of the StatsItem
     */
    public int getDistanceProtection() {
        return distanceProtection;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public int getGodPowerGenerator() {
        return godPowerGenerator;
    }

    @Override
    public void process(PlayerInterface owner, PlayerInterface target) {
        if (meleeDamage > 0) {
            int differenceDamage = Math.max(meleeDamage - target.getMeleeProtection(), 0);
            int differenceProtection = target.getMeleeProtection() - meleeDamage;

            long targetLifePoints = target.getLifePoints() - differenceDamage;

            target.setMeleeProtection(differenceProtection);
            target.setLifePoints(targetLifePoints);
        }

        if (distanceDamage > 0) {
            int differenceDamage = Math.max(distanceDamage - target.getDistanceProtection(), 0);
            int differenceProtection = target.getDistanceProtection() - distanceDamage;

            long targetLifePoints = target.getLifePoints() - differenceDamage;

            target.setDistanceProtection(differenceProtection);
            target.setLifePoints(targetLifePoints);
        }
    }

}
