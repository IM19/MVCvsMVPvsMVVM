package com.architecturepatterns.potions.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.architecturepatterns.potions.R;
import com.architecturepatterns.potions.model.Potion;

import java.util.List;

public class PotionsListAdapter extends RecyclerView.Adapter<PotionsListAdapter.ViewHolder> {

    List<Potion> mPotionsCollection;
    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public PotionsListAdapter(List<Potion> potionsList) {
        mPotionsCollection = potionsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
    }

    @NonNull
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
        Potion p = mPotionsCollection.get(position);

        TextView id = holder.potionEntryID;
        id.setText(p.getId());
        TextView title = holder.potionEntryTitle;
        title.setText(p.getName());
    }

    @Override
    public int getItemCount() {
        return mPotionsCollection.size();
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
