package edu.kit.informatik.orlog.entity;

import java.util.regex.Pattern;

/**
 * This enum lists every available item types in the orlog game.
 *
 * @author uxgle
 * @version 1.0
 */
public enum ItemType {
    /**
     * The axe.
     */
    AXE("MA") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 1, 0, 0, 0, 0);
        }
    },
    /**
     * The helmet.
     */
    HELMET("MD") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 1, 0, 0, 0);
        }
    },
    /**
     * The god's power-generating variant of the helmet.
     */
    GOD_POWER_HELMET("GMD") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 1, 0, 0, 1);
        }
    },
    /**
     * The bow.
     */
    BOW("RA") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 0, 1, 0, 0);
        }
    },
    /**
     * The god's power-generating variant of the bow.
     */
    GOD_POWER_BOW("GRA") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 0, 1, 0, 1);
        }
    },
    /**
     * The shield.
     */
    SHIELD("RD") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 0, 0, 1, 0);
        }
    },
    /**
     * The god's power-generating variant of the shield.
     */
    GOD_POWER_SHIELD("GRD") {
        @Override
        public ItemInterface buildItem() {
            return new StatsItem(getItemTypeId(), 0, 0, 0, 1, 1);
        }
    },
    /**
     * The theft of god power.
     */
    THEFT("ST") {
        @Override
        public ItemInterface buildItem() {
            return new TheftItem(getItemTypeId(), 0);
        }
    },
    /**
     * The god's power-generating variant of the theft of god power.
     */
    GOD_POWER_THEFT("GST") {
        @Override
        public ItemInterface buildItem() {
            return new TheftItem(getItemTypeId(), 1);
        }
    };

    final private String itemTypeId;

    /**
     * The constructor instantiates a new {@link ItemType} with the given id.
     *
     * @param itemId The given id
     */
    ItemType(String itemId) {
        this.itemTypeId = itemId;
    }

    /**
     * Gets the item type id.
     *
     * @return The item type id
     */
    public String getItemTypeId() {
        return itemTypeId;
    }

    /**
     * Compiles the given item type id String to a {@link ItemType}
     *
     * @param itemTypeId the String containing the item type id
     * @return the item type with the same id if available, null otherwise
     */
    public static ItemType compileFromString(final String itemTypeId) {
        for (final ItemType item : ItemType.values()) {
            if (item.getItemTypeId().equals(itemTypeId)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns the regular expression for one item type.
     *
     * @return The regular expression for one god favor type
     */
    public static String getAllowedTypesStringPattern() {
        return "[" + AXE.getItemTypeId() + "|" + HELMET.getItemTypeId() + "|" + GOD_POWER_HELMET.getItemTypeId() + "|"
                + BOW.getItemTypeId() + "|" + GOD_POWER_BOW.getItemTypeId() + "|" + SHIELD.getItemTypeId() + "|"
                + GOD_POWER_SHIELD.getItemTypeId() + "|" + THEFT.getItemTypeId() + "|" + GOD_POWER_THEFT.getItemTypeId()
                + "]";
    }

    /**
     * Returns the grouped pattern for multiple item types.
     *
     * @return The regular expression for an arbitrary number of items
     */
    public static Pattern getAllowedTypesPattern() {
        return Pattern.compile("(" + getAllowedTypesStringPattern() + "*" + ")");
    }

    // TODO

    /**
     * Builds an item.
     *
     * @return An item
     */
    public abstract ItemInterface buildItem();
}
