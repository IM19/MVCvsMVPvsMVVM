package com.architecturepatterns.potions.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.architecturepatterns.potions.R;
import com.architecturepatterns.potions.model.Potion;
import com.architecturepatterns.potions.model.PotionsRepository;

public class PotionDetailActivity extends AppCompatActivity {
    public static final String POTION_ID = "0";

    private TextView mPotionName;
    private TextView mPotionIngredients;
    private TextView mPotionEffect;
    private TextView mPotionCharacteristics;
    private TextView mPotionDifficultyLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PotionsRepository mPotionRepository = new PotionsRepository();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Potion potion = mPotionRepository.getPotionDetails(bundle.getInt(POTION_ID));
        initView();
        updateView(potion);
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
