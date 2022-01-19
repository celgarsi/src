package edu.kit.informatik.orlog.entity;

import java.util.ArrayList;

public interface PlayerInterface {

    Long getLifePoints();

    void setLifePoints(Long lifePoints);

    Integer getGodPowerPoints();

    void setGodPowerPoints(Integer godPowerPoints);

    void setItems(ArrayList<ItemInterface> items);

    ArrayList<ItemInterface> getItems();

    GodFavorInterface getGodFavor();

    void setGodFavor(GodFavorInterface godFavor);

    String getName();

    void setName(String name) throws Exception;

    int getMeleeDamage();

    void setMeleeDamage(int meleeDamage);

    void addMeleeDamage(int meleeDamage);

    int getMeleeProtection();

    void setMeleeProtection(int meleeProtection);

    void addMeleeProtection(int meleeProtection);

    int getDistanceDamage();

    void setDistanceDamage(int distanceDamage);

    void addDistanceDamage(int distanceDamage);

    int getDistanceProtection();

    void setDistanceProtection(int distanceProtection);

    void addDistanceProtection(int distanceProtection);
}
