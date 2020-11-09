package com.architecturepatterns.potions.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.architecturepatterns.potions.ViewModel.PotionDetailViewModel;
import com.architecturepatterns.potions.R;
import com.architecturepatterns.potions.model.Potion;

public class PotionDetailActivity extends AppCompatActivity {

    public static final String POTION_ID = "0";

    private TextView mPotionName;
    private TextView mPotionIngredients;
    private TextView mPotionEffect;
    private TextView mPotionCharacteristics;
    private TextView mPotionDifficultyLevel;

    private PotionDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PotionDetailViewModel.Factory factory =
                new PotionDetailViewModel.Factory(bundle.getInt(POTION_ID));
        mViewModel = new ViewModelProvider(this, factory).get(PotionDetailViewModel.class);
        initView();
        observeViewModel();
    }

    private void observeViewModel() {
        mViewModel.getPotionDetailObservable().observe(this,
                potion -> {
                if (potion != null) {
                    updateView(potion);
                 }
            });
    }

    private void initView() {
        setContentView(R.layout.potion_description);
        mPotionName = findViewById(R.id.potion_name);
        mPotionIngredients = findViewById(R.id.potion_ingredients);
        mPotionEffect = findViewById(R.id.potion_effect);
        mPotionCharacteristics = findViewById(R.id.potion_characteristics);
        mPotionDifficultyLevel = findViewById(R.id.potion_difficulty);
    }

    private void updateView(Potion potion) {
        mPotionName.setText(potion.getName());
        mPotionIngredients.setText(potion.getIngredients());
        mPotionEffect.setText(potion.getEffect());
        mPotionCharacteristics.setText(potion.getCharacteristics());
        mPotionDifficultyLevel.setText(potion.getDifficultyLevel());
    }
}
