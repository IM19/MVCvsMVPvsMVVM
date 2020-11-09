package com.architecturepatterns.potions.Controller;

import com.architecturepatterns.potions.view.ui.IPotionsView;
import com.architecturepatterns.potions.model.IPotionModel;
import com.architecturepatterns.potions.model.PotionsRepository;

public class Controller implements IController, IPotionModel.Listener {

    private final IPotionsView mPotionsView;
    private final PotionsRepository mPotionsRepository;

    public Controller(IPotionsView view, PotionsRepository repository) {
        mPotionsView = view;
        mPotionsRepository = repository;
    }

    @Override
    public void onLaunch() {
        mPotionsRepository.addListener(this);
        mPotionsRepository.loadPotionsList();
    }

    @Override
    public void onDataLoading() {
        mPotionsView.showProgressBar();
    }

    @Override
    public void onDataLoaded() {
        mPotionsView.hideProgressBar();
        mPotionsView.loadView();
    }

    @Override
    public void onDataLoadFailed() {
        mPotionsView.showError();
    }

    @Override
    public void unregister() {
        mPotionsRepository.removeListener();
    }
}
