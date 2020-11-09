package com.architecturepatterns.potions.view.ui;

public interface IPotionDetailView {

    void setPotionName(String name);

    void setPotionIngredients(String ingredients);

    void setPotionCharacteristics(String characteristics);

    void setPotionEffects(String effects);

    void setPotionDifficulty(String difficulty);
}
