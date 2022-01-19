package edu.kit.informatik.orlog.entity;

/**
 * This class represents the god favor Thor's Thunder.
 *
 * @author uxgle
 * @version 1.0
 */
public class ThorThunderFavor implements GodFavorInterface {

    private int damage;
    private int godFavorCost;
    private int level;

    /**
     * The constructor instantiates a new Thor's Thunder favor with the given level.
     *
     * @param level The given level
     */
    public ThorThunderFavor(int level) {
        this.damage = 2 + (3 * (level - 1));
        this.godFavorCost = 4 + (4 * (level - 1));
    }

    @Override
    public void process(PlayerInterface owner, PlayerInterface target) {
        target.setLifePoints(target.getLifePoints() - damage);
        owner.setGodPowerPoints(owner.getGodPowerPoints() - godFavorCost);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;

        if (level < 1) {
            this.damage = 0;
            this.godFavorCost = 0;
        } else {
            this.damage = 2 + (3 * (level - 1));
            this.godFavorCost = 4 + (4 * (level - 1));
        }
    }

    @Override
    public int getLevel() {
        return level;
    }
}
