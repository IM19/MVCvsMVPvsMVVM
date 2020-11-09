package com.architecturepatterns.potions.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    public static final PotionModel POTION_MODEL = PotionModel.getInstance();

    @NonNull
    public static LiveData<List<Potion>> getPotionsList() {
        return POTION_MODEL.getPotionsList();
    }

    @NonNull
    public static LiveData<Potion> getPotionDetails(int potionID) {
        return POTION_MODEL.getPotionDetails(potionID);
    }
}
