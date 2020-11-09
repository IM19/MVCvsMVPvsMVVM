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

import com.architecturepatterns.potions.Presenter.IPotionsListPresenter;
import com.architecturepatterns.potions.Presenter.PotionsListPresenter;
import com.architecturepatterns.potions.view.adapter.PotionsListAdapter;
import com.architecturepatterns.potions.PotionsApplication;
import com.architecturepatterns.potions.R;

public class PotionsMVPActivity extends AppCompatActivity implements IPotionsListView, PotionsListAdapter.OnItemClickListener {

    private IPotionsListPresenter mPresenter;
    private Context mContext;

    private ProgressBar mProgressBar;
    private TextView mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potions);
        mContext = PotionsApplication.getContext();
        setupView();
        mPresenter = new PotionsListPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onLaunch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unregister();
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
        PotionsListAdapter potionsListAdapter = new PotionsListAdapter(mPresenter);
        potionsListAdapter.setItemClickListener(this);
        recyclerView.setAdapter(potionsListAdapter);
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
        mContext.startActivity(intent);
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
