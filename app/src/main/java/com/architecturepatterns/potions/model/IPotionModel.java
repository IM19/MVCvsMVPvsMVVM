package com.architecturepatterns.potions.model;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IPotionModel {

    LiveData<List<Potion>> getPotionsList();

    void loadPotionsList();

    LiveData<Potion> getPotionDetails(int potionID);
}
