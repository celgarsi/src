package edu.kit.informatik.orlog.entity;

/**
 * This class represents the god favor Idun's Regeneration.
 *
 * @author uxgle
 * @version 1.0
 */
public class IdunRegenerationFavor implements GodFavorInterface {

    private int lifePointsGenerator;
    private int godFavorCost;
    private int level;

    /**
     * The constructor instantiates a new Idun's Regeneration favor with the given level.
     *
     * @param level The given level
     */
    public IdunRegenerationFavor(int level) {
        setLevel(level);
    }

    @Override
    public void process(PlayerInterface owner, PlayerInterface target) {
        owner.setLifePoints(target.getLifePoints() + lifePointsGenerator);
        owner.setGodPowerPoints(owner.getGodPowerPoints() - godFavorCost);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;

        if (level < 1) {
            lifePointsGenerator = 0;
            godFavorCost = 0;
        } else {
            lifePointsGenerator = 2 + (2 * (level - 1));
            godFavorCost = 4 + (3 * (level - 1));
        }
    }

    @Override
    public int getLevel() {
        return level;
    }

}
