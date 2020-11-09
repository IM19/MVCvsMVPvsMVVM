package com.architecturepatterns.potions.model;

import androidx.annotation.NonNull;

import java.util.List;

public class PotionsRepository implements IRepository<Potion, Integer, IPotionModel.Listener> {

    public static final PotionModel POTION_MODEL = PotionModel.getInstance();

    @NonNull
    public List<Potion> getPotionsList() {
        return POTION_MODEL.getPotionsList();
    }

    @Override
    public Potion getPotionDetails(Integer potionID) {
        return POTION_MODEL.getPotion(potionID);
    }

    @Override
    public void addListener(IPotionModel.Listener listener) {
        POTION_MODEL.addListener(listener);
    }

    @Override
    public void removeListener() {
        POTION_MODEL.removeListener();
    }

    @Override
    public void loadPotionsList() {
        POTION_MODEL.loadPotionsList();
    }
}
