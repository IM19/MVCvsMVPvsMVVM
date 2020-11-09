package com.architecturepatterns.potions.model;

public class Potion {

    private final String mId;
    private String mName;
    private String mIngredients;
    private String mEffect;
    private String mCharacteristics;
    private String mDifficultyLevel;

    public Potion(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public void setIngredients(String ingredients) {
        mIngredients = ingredients;
    }

    public String getEffect() {
        return mEffect;
    }

    public void setEffect(String effect) {
        mEffect = effect;
    }

    public String getCharacteristics() {
        return mCharacteristics;
    }

    public void setCharacteristics(String characteristics) {
        mCharacteristics = characteristics;
    }

    public String getDifficultyLevel() {
        return mDifficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        mDifficultyLevel = difficultyLevel;
    }
}
