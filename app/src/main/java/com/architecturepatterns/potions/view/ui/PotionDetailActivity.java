package com.architecturepatterns.potions.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.architecturepatterns.potions.Presenter.IPotionDetailPresenter;
import com.architecturepatterns.potions.Presenter.PotionDetailPresenter;
import com.architecturepatterns.potions.R;

public class PotionDetailActivity extends AppCompatActivity implements IPotionDetailView {
    public static final String POTION_ID = "0";

    private IPotionDetailPresenter mPresenter;

    private TextView mPotionName;
    private TextView mPotionIngredients;
    private TextView mPotionEffect;
    private TextView mPotionCharacteristics;
    private TextView mPotionDifficultyLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        initView();
        mPresenter = new PotionDetailPresenter(this);
        mPresenter.showPotionDetails(bundle.getInt(POTION_ID));
    }

    private void initView() {
        setContentView(R.layout.potion_description);
        mPotionName = findViewById(R.id.potion_name);
        mPotionIngredients = findViewById(R.id.potion_ingredients);
        mPotionEffect = findViewById(R.id.potion_effect);
        mPotionCharacteristics = findViewById(R.id.potion_characteristics);
        mPotionDifficultyLevel = findViewById(R.id.potion_difficulty);
    }

    @Override
    public void setPotionName(String name) {
        mPotionName.setText(name);
    }

    @Override
    public void setPotionIngredients(String ingredients) {
        mPotionIngredients.setText(ingredients);
    }

    @Override
    public void setPotionCharacteristics(String characteristics) {
        mPotionCharacteristics.setText(characteristics);
    }

    @Override
    public void setPotionEffects(String effects) {
        mPotionEffect.setText(effects);
    }

    @Override
    public void setPotionDifficulty(String difficulty) {
        mPotionDifficultyLevel.setText(difficulty);
    }
}
