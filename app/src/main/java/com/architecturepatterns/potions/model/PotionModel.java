package com.architecturepatterns.potions.model;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.architecturepatterns.potions.PotionsApplication;
import com.architecturepatterns.potions.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class PotionModel implements IPotionModel {

    public static final String POTIONS_SPLIT_REGEX = ";";
    private static final int POTION_DATA_COLUMNS = 5;
    private static final int POTION_NAME = 0;
    private static final int POTION_INGREDIENTS = 1;
    private static final int POTION_CHARACTERISTICS = 2;
    private static final int POTION_EFFECTS = 3;
    private static final int POTION_DIFFICULTY = 4;

    private static PotionModel sInstance;
    private final List<Potion> mPotionsList;
    private Listener mListener;

    public static synchronized PotionModel getInstance() {
        if (sInstance == null) {
            sInstance = new PotionModel();
        }
        return sInstance;
    }

    public PotionModel() {
        mPotionsList = new ArrayList<>();
    }

    @Override
    public void addListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public void removeListener() {
        mListener = null;
    }

    @Override
    public List<Potion> getPotionsList() {
        return mPotionsList;
    }

    @Nullable
    @Override
    public Potion getPotion(int id) {
        if (mPotionsList.size() != 0) {
            return mPotionsList.get(id);
        }
        return null;
    }

    @Override
    public void loadPotionsList() {
        if (mPotionsList.size() == 0) {
            LoadPotionsTask loadPotionsTask = new LoadPotionsTask();
            loadPotionsTask.execute();
        }
    }

    private class LoadPotionsTask extends AsyncTask<Void, Void, List<Potion>> {
        @Override
        protected void onPreExecute() {
            mListener.onDataLoading();
        }

        @Override
        protected List<Potion> doInBackground(Void... voids) {
            int potionCount = 0;
            InputStream is = PotionsApplication.getContext().getResources().openRawResource(R.raw.potions_data);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            try {
                String line;
                while (((line = reader.readLine()) != null)) {
                    if (potionCount == 0) {
                        potionCount++;
                        continue;
                    }
                    String[] potionEntry = line.split(POTIONS_SPLIT_REGEX, POTION_DATA_COLUMNS);
                    Potion potion = new Potion(Integer.toString(potionCount));

                    potion.setName(potionEntry[POTION_NAME]);
                    potion.setIngredients(potionEntry[POTION_INGREDIENTS]);
                    potion.setCharacteristics(potionEntry[POTION_CHARACTERISTICS]);
                    potion.setEffect(potionEntry[POTION_EFFECTS]);
                    potion.setDifficultyLevel(potionEntry[POTION_DIFFICULTY]);

                    mPotionsList.add(potion);
                    potionCount++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error while closing input stream: " + e);
                }
            }
            return mPotionsList;
        }

        @Override
        protected void onPostExecute(List<Potion> potionList) {
            if (potionList != null) {
                mListener.onDataLoaded();
            } else {
                mListener.onDataLoadFailed();
            }
        }
    }
}
