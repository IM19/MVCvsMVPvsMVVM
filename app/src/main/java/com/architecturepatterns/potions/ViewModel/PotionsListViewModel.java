package com.architecturepatterns.potions.ViewModel;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.architecturepatterns.potions.model.Potion;
import com.architecturepatterns.potions.model.Repository;

import java.util.List;

public class PotionsListViewModel extends ViewModel implements LifecycleObserver {

    private final LiveData<List<Potion>> mPotionListObservable;

    public PotionsListViewModel() {
        super();
        mPotionListObservable = Repository.getPotionsList();
    }

    public LiveData<List<Potion>> getPotionsListObservable() {
        return mPotionListObservable;
    }
}
