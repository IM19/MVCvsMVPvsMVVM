package com.architecturepatterns.potions.Presenter;

import androidx.annotation.NonNull;

import com.architecturepatterns.potions.view.adapter.IPotionsListItem;
import com.architecturepatterns.potions.view.ui.IPotionsListView;
import com.architecturepatterns.potions.model.IPotionModel;
import com.architecturepatterns.potions.model.Potion;
import com.architecturepatterns.potions.model.PotionsRepository;

import java.util.List;

public class PotionsListPresenter implements IPotionsListPresenter, IPotionModel.Listener {

    private final IPotionsListView mPotionsView;
    private final PotionsRepository mPotionRepository;
    List<Potion> mPotionList;

    public PotionsListPresenter(IPotionsListView view) {
        mPotionsView = view;
        mPotionRepository = new PotionsRepository();
    }

    @Override
    public void onLaunch() {
        mPotionRepository.addListener(this);
        mPotionRepository.loadPotionsList();
    }

    @Override
    public int getPotionsCount() {
        return mPotionList.size();
    }

    public void onBindPotionsListEntryAtPosition (int position, @NonNull IPotionsListItem potionEntry) {
        Potion p = mPotionList.get(position);
        potionEntry.setPotionsId(p.getId());
        potionEntry.setPotionsTitle(p.getName());
    }

    @Override
    public void unregister() {
        mPotionRepository.removeListener();
    }

    @Override
    public void onDataLoading() {
        mPotionsView.showProgressBar();
    }

    @Override
    public void onDataLoaded() {
        mPotionList = mPotionRepository.getPotionsList();
        mPotionsView.hideProgressBar();
        mPotionsView.loadView();
    }

    @Override
    public void onDataLoadFailed() {
        mPotionsView.showError();
    }
}
