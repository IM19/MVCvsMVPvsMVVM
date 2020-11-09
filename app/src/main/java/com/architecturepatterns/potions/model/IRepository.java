package com.architecturepatterns.potions.model;

import java.util.List;

public interface IRepository<T, K, L> {

    List<T> getPotionsList();

    T getPotionDetails(K potionID);

    void addListener(L listener);

    void removeListener();

    void loadPotionsList();
}
