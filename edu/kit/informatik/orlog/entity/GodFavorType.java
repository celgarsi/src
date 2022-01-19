package edu.kit.informatik.orlog.entity;

import java.util.regex.Pattern;

/**
 * This enum encapsulates the three available god power favors in the orlog game.
 *
 * @author uxgle
 * @version 1.0
 */
public enum GodFavorType {
    /**
     * The Thor's Thunder god favor.
     */
    THOR_THUNDER("TS") {
        @Override
        public GodFavorInterface buildGodFavor(int level) {
            return new ThorThunderFavor(level);
        }
    },
    /**
     * The Thrymr's Theft god favor.
     */
    THRYMR_THEFT("TT") {
        @Override
        public GodFavorInterface buildGodFavor(int level) {
            return new ThrymrsTheftFavor(level);
        }
    },
    /**
     * The Idun's Regeneration god favor.
     */
    IDUN_REGENERATION("IR") {
        @Override
        public GodFavorInterface buildGodFavor(int level) {
            return new IdunRegenerationFavor(level);
        }
    };

    private final Pattern godFavorId;

    /**
     * The constructor instantiates a new {@link GodFavorType} with the given pattern.
     *
     * @param pattern The pattern of the god favor type
     */
    GodFavorType(String pattern) {
        this.godFavorId = Pattern.compile(pattern);
    }

    /**
     * Returns the regular expression for one god favor type.
     *
     * @return The regular expression for one god favor type
     */
    public static String getGodFavorTypeId() {
        return "[" + THOR_THUNDER.getGodFavorIdString() + "|" + THRYMR_THEFT.getGodFavorIdString() + "|"
                + IDUN_REGENERATION.getGodFavorIdString() + "]";
    }

    /**
     * Returns the grouped pattern for multiple god favor types.
     *
     * @return the regular expression for an arbitrary number of god favors
     */
    public static Pattern getGodFavorsId() {
        return Pattern.compile("(" + getGodFavorTypeId() + "*" + ")");
    }

    /**
     * Compiles the given String to a {@link GodFavorType}
     *
     * @param godFavorTypeId the String containing the god favor type id
     * @return the god favor type with the same id if available, null otherwise
     */
    public static GodFavorType compileFromString(final String godFavorTypeId) {
        for (final GodFavorType godFavor : GodFavorType.values()) {
            if (godFavor.getGodFavorIdString().equals(godFavorTypeId)) {
                return godFavor;
            }
        }
        return null;
    }

    /**
     * Gets the god favor id as a String.
     *
     * @return The String with the god favor type id.
     */
    public String getGodFavorIdString() {
        return godFavorId.toString();
    }

    //TODO comprobar esto

    /**
     * Builds a god favor with the given level.
     *
     * @param level The given level
     * @return A god favor with the given level
     */
    public abstract GodFavorInterface buildGodFavor(int level);
}
