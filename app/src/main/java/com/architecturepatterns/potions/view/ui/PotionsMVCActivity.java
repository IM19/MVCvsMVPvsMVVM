package com.architecturepatterns.potions.view.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.architecturepatterns.potions.Controller.Controller;
import com.architecturepatterns.potions.Controller.IController;
import com.architecturepatterns.potions.view.adapter.PotionsListAdapter;
import com.architecturepatterns.potions.PotionsApplication;
import com.architecturepatterns.potions.R;
import com.architecturepatterns.potions.model.PotionsRepository;

public class PotionsMVCActivity extends AppCompatActivity implements IPotionsView, PotionsListAdapter.OnItemClickListener {

    private IController mController;
    private PotionsRepository mPotionsRepository;
    private Context mContext;

    private ProgressBar mProgressBar;
    private TextView mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potions);
        mContext = PotionsApplication.getContext();
        mPotionsRepository = new PotionsRepository();
        mController = new Controller(this, mPotionsRepository);
        setupView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mController.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mController.onLaunch();
    }

    private void setupView() {
        mProgressBar = findViewById(R.id.progress_bar);
        mError = findViewById(R.id.data_error);
    }

    @Override
    public void loadView() {
        RecyclerView recyclerView = findViewById(R.id.potions_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new ItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PotionsListAdapter potionsListAdapter = new PotionsListAdapter(mPotionsRepository.getPotionsList());
        recyclerView.setAdapter(potionsListAdapter);
        potionsListAdapter.setItemClickListener(this);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(mContext, PotionDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(PotionDetailActivity.POTION_ID, position);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private class ItemDecoration extends RecyclerView.ItemDecoration {
        private final int mItemSpacing;

        ItemDecoration() {
            mItemSpacing = Math.round(getResources().getDimension(R.dimen.potion_card_spacing));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = mItemSpacing;
        }
    }
}
