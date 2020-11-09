package com.architecturepatterns.potions.view.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.architecturepatterns.potions.view.adapter.PotionsListAdapter;
import com.architecturepatterns.potions.ViewModel.PotionsListViewModel;
import com.architecturepatterns.potions.PotionsApplication;
import com.architecturepatterns.potions.R;
import com.architecturepatterns.potions.model.Potion;

import java.util.List;

public class PotionsMVVMActivity extends AppCompatActivity implements LifecycleOwner, PotionsListAdapter.OnItemClickListener {

    private PotionsListViewModel mViewModel;
    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potions);
        mContext = PotionsApplication.getContext();
        setupView();
        showProgressBar();

        mViewModel = new ViewModelProvider(this).get(PotionsListViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observeViewModel();
    }

    private void observeViewModel() {
        mViewModel.getPotionsListObservable().observe(this,
                potionsList -> {
                    if (potionsList.size() != 0) {
                        hideProgressBar();
                        loadView(potionsList);
                    } else {
                        showError();
                    }
        });
    }

    private void setupView() {
        mProgressBar = findViewById(R.id.progress_bar);
        mError = findViewById(R.id.data_error);
    }

    public void loadView(List<Potion> potionsList) {
        RecyclerView recyclerView = findViewById(R.id.potions_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new ItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PotionsListAdapter potionsListAdapter = new PotionsListAdapter(potionsList);
        recyclerView.setAdapter(potionsListAdapter);
        potionsListAdapter.setItemClickListener(this);
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

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
