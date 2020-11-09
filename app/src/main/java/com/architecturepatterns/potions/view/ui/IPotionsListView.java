package com.architecturepatterns.potions.view.ui;

public interface IPotionsListView {

    void loadView();

    void showProgressBar();

    void hideProgressBar();

    void showError();
}
