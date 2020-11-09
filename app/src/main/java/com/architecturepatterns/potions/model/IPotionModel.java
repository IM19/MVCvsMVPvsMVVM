package com.architecturepatterns.potions.model;

import java.util.List;

public interface IPotionModel {

    List<Potion> getPotionsList();

    void loadPotionsList();

    Potion getPotion(int id);

    interface Listener {

        void onDataLoading();

        void onDataLoaded();

        void onDataLoadFailed();
    }

    void addListener(Listener listener);

    void removeListener();

}
