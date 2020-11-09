package com.architecturepatterns.potions.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.architecturepatterns.potions.model.Potion;
import com.architecturepatterns.potions.model.Repository;

public class PotionDetailViewModel extends ViewModel {

    private final LiveData<Potion> mPotionDetailObservable;

    public PotionDetailViewModel(int potionID) {
        super();
        mPotionDetailObservable = Repository.getPotionDetails(potionID);
    }

    public LiveData<Potion> getPotionDetailObservable() {
        return mPotionDetailObservable;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final int projectID;

        public Factory(int projectID) {
            this.projectID = projectID;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PotionDetailViewModel(projectID);
        }
    }
}
