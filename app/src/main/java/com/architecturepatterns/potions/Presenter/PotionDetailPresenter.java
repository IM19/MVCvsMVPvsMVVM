package com.architecturepatterns.potions.Presenter;

import com.architecturepatterns.potions.view.ui.IPotionDetailView;
import com.architecturepatterns.potions.model.Potion;
import com.architecturepatterns.potions.model.PotionsRepository;

public class PotionDetailPresenter implements IPotionDetailPresenter {

    private final IPotionDetailView mPotionsView;
    private final PotionsRepository mPotionsRepository;

    public PotionDetailPresenter(IPotionDetailView view) {
        mPotionsView = view;
        mPotionsRepository = new PotionsRepository();
    }

    @Override
    public void showPotionDetails(int id) {
        Potion potion = mPotionsRepository.getPotionDetails(id);
        mPotionsView.setPotionName(potion.getName());
        mPotionsView.setPotionIngredients(potion.getIngredients());
        mPotionsView.setPotionEffects(potion.getEffect());
        mPotionsView.setPotionCharacteristics(potion.getCharacteristics());
        mPotionsView.setPotionDifficulty(potion.getDifficultyLevel());
    }
}
