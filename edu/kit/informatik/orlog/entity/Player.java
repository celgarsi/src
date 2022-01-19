package edu.kit.informatik.orlog.entity;

import edu.kit.informatik.orlog.ui.Main;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * This class represents a player of the game Orlog.
 *
 * @author uxgle
 * @version 1.0
 */
public class Player implements PlayerInterface {
    private String name;
    private GodFavorInterface godFavor;
    private ArrayList<ItemInterface> items;
    private Long lifePoints;
    private Integer godPowerPoints;   //God power points

    private int meleeDamage;
    private int meleeProtection;
    private int distanceDamage;
    private int distanceProtection;

    /**
     * The constructor instantiates a new player with the given name, life points and god power points.
     *
     * @param name           The name of the player
     * @param lifePoints     The amount of life points of the player
     * @param godPowerPoints The amount of god power points of the player
     * @throws Exception occurs if it is an invalid name or an invalid amount of life- or god power points
     */
    public Player(String name, Long lifePoints, Integer godPowerPoints) throws Exception {
        checkLifePoints(lifePoints);
        checkGodPowerPoints(godPowerPoints);
        setName(name);
        setLifePoints(lifePoints);
        setGodPowerPoints(godPowerPoints);

        meleeDamage = 0;
        meleeProtection = 0;
        distanceDamage = 0;
        distanceProtection = 0;
    }

    /**
     * Checks whether the player has a given item.
     *
     * @param item The given item.
     * @return {true} if the player has the given item, {false} if not
     */
    public boolean hasItem(ItemType item) {
        return items.contains(item);
    }

    /**
     * Gets the stats of a player, i.e: name, life points and god power points
     *
     * @return a String
     */
    public String getStats() {
        return name + Main.SEPARATOR + lifePoints + Main.SEPARATOR + godPowerPoints;
    }

    /**
     * Gets the life points of a player.
     *
     * @return the life points of a player
     */
    public Long getLifePoints() {
        return lifePoints;
    }

    /**
     * Sets the life points of a player from a given amount.
     *
     * @param lifePoints the given amount of life points
     */
    public void setLifePoints(Long lifePoints) {
        this.lifePoints = Math.max(lifePoints, 0);
    }

    /**
     * Gets the god power points of a player.
     *
     * @return the god power points of a player
     */
    public Integer getGodPowerPoints() {
        return godPowerPoints;
    }

    /**
     * Sets the god power points of a player from a given amount.
     *
     * @param godPowerPoints the given amount of god power points
     */
    public void setGodPowerPoints(Integer godPowerPoints) {
        this.godPowerPoints = Math.max(godPowerPoints, 0);
    }

    /**
     * Gets the items of a player.
     *
     * @return an ArrayList with all the items of a player
     */
    public ArrayList<ItemInterface> getItems() {
        return items;
    }

    /**
     * Sets the items of a player from a given ArrayList.
     *
     * @param items an ArrayList with items
     */
    public void setItems(ArrayList<ItemInterface> items) {
        this.items = items;
        setStatsFromItems();
    }

    /**
     * Sets the item stats contained in the ArrayList items to the player attributes.
     */
    private void setStatsFromItems() {
        for (ItemInterface item : items) {

            godPowerPoints += item.getGodPowerGenerator();

            if (item instanceof StatsItemInterface) {
                StatsItemInterface statsItem = (StatsItemInterface) item;
                addMeleeDamage(statsItem.getMeleeDamage());
                addMeleeProtection(statsItem.getMeleeProtection());
                addDistanceDamage(statsItem.getDistanceDamage());
                addDistanceProtection(statsItem.getDistanceProtection());
            }
        }
    }

    /**
     * Gets the god favor of a player.
     *
     * @return the god favor of a player.
     */
    public GodFavorInterface getGodFavor() {
        return godFavor;
    }

    /**
     * Sets the god favor of a player from a given god favor.
     *
     * @param godFavor The given god favor
     */
    public void setGodFavor(GodFavorInterface godFavor) {
        this.godFavor = godFavor;
    }

    /**
     * Gets the name of a player.
     *
     * @return The name of the player
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name of a player from a given String.
     *
     * @param name The given String
     * @throws Exception occurs if the given String is invalid
     */
    public void setName(String name) throws Exception {
        checkName(name);
        this.name = name;
    }

    /**
     * Checks whether the given name is valid by not being empty or containing blank spaces or ";".
     *
     * @param name The given name.
     * @throws Exception occurs if the given name is invalid
     */
    public void checkName(String name) throws Exception {
        if (Pattern.matches(" ;", name)) {
            throw new Exception("The player name cannot contains ';' or white spaces");
        } else if (name.length() == 0) {
            throw new Exception("The player name cannot be empty");
        }
    }

    /**
     * Checks whether the given life points are valid by being equal or greater than 5.
     *
     * @param lifePoints The given life points.
     * @throws Exception occurs if the given life points are invalid
     */
    public void checkLifePoints(Long lifePoints) throws Exception {
        //TODO crear constante con error
        if (lifePoints < 5) {
            throw new Exception("The player must have at least 5 lifepoints");
        }
    }

    /**
     * Checks whether the given god power points are valid by being at least 0.
     *
     * @param godPowerPoints The given god power points
     * @throws Exception occurs if the given god power points are invalid
     */
    public void checkGodPowerPoints(Integer godPowerPoints) throws Exception {
        //TODO crear constante con error
        if (godPowerPoints < 0) {
            throw new Exception("The player must have at least 0 God-Power-Points");
        }
    }

    /**
     * Gets the melee damage of a player.
     *
     * @return The melee damage of the player
     */
    public int getMeleeDamage() {
        return meleeDamage;
    }

    /**
     * Sets the given melee damage to a player.
     *
     * @param meleeDamage The given melee damage
     */
    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }

    /**
     * Adds the given melee damage to a player.
     *
     * @param meleeDamage The given melee damage
     */
    public void addMeleeDamage(int meleeDamage) {
        this.meleeDamage += meleeDamage;
    }

    /**
     * Gets the melee protection of a player.
     *
     * @return The melee protection of the player
     */
    public int getMeleeProtection() {
        return meleeProtection;
    }

    /**
     * Sets the given melee protection to a player.
     *
     * @param meleeProtection The given melee protection
     */
    public void setMeleeProtection(int meleeProtection) {
        this.meleeProtection = Math.max(meleeProtection, 0);
    }

    /**
     * Adds the given melee protection to a player.
     *
     * @param meleeProtection The given melee protection
     */
    public void addMeleeProtection(int meleeProtection) {
        this.meleeProtection += meleeProtection;
    }

    /**
     * Gets the distance damage of a player.
     *
     * @return The distance damage of the player
     */
    public int getDistanceDamage() {
        return distanceDamage;
    }

    /**
     * Sets the given distance damage to a player.
     *
     * @param distanceDamage The given distance damage
     */
    public void setDistanceDamage(int distanceDamage) {
        this.distanceDamage = distanceDamage;
    }

    /**
     * Adds the given distance damage to a player.
     *
     * @param distanceDamage The given distance damage
     */
    public void addDistanceDamage(int distanceDamage) {
        this.distanceDamage += distanceDamage;
    }

    /**
     * Gets the distance protection of a player.
     *
     * @return The distance protection of the player
     */
    public int getDistanceProtection() {
        return distanceProtection;
    }

    /**
     * Sets the given distance protection to a player.
     *
     * @param distanceProtection The given distance protection
     */
    public void setDistanceProtection(int distanceProtection) {
        this.distanceProtection = Math.max(distanceProtection, 0);
    }

    /**
     * Adds the given distance protection to a player.
     *
     * @param distanceProtection The given distance protection
     */
    public void addDistanceProtection(int distanceProtection) {
        this.distanceProtection += distanceProtection;
    }
}