package com.architecturepatterns.potions.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.architecturepatterns.potions.Presenter.IPotionsListPresenter;
import com.architecturepatterns.potions.R;

public class PotionsListAdapter extends RecyclerView.Adapter<PotionsListAdapter.ViewHolder> {
    private IPotionsListPresenter mPresenter;
    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public PotionsListAdapter(IPotionsListPresenter presenter) {
        mPresenter = presenter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IPotionsListItem {
        public TextView potionEntryID;
        public TextView potionEntryTitle;

        public ViewHolder(@NonNull ViewGroup cardLayout) {
            super(cardLayout);
            potionEntryID = cardLayout.findViewById(R.id.potion_entry_number);
            potionEntryTitle = cardLayout.findViewById(R.id.potion_entry_title);
            cardLayout.setOnClickListener(view -> {
                    mItemClickListener.onClick(getAdapterPosition());
            });
        }

        @Override
        public void setPotionsId(String id) {
            potionEntryID.setText(id);
        }

        @Override
        public void setPotionsTitle(String title) {
            potionEntryTitle.setText(title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup cardLayout;
        cardLayout =
                (ViewGroup)
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.potion_entry, parent, false);
        return new ViewHolder(cardLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mPresenter.onBindPotionsListEntryAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getPotionsCount();
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
