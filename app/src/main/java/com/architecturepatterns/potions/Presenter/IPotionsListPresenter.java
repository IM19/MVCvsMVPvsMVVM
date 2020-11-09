package com.architecturepatterns.potions.Presenter;

import com.architecturepatterns.potions.view.adapter.IPotionsListItem;

public interface IPotionsListPresenter {

    void onLaunch();

    int getPotionsCount();

    void onBindPotionsListEntryAtPosition(int position, IPotionsListItem potionEntry);

    void unregister();

}
