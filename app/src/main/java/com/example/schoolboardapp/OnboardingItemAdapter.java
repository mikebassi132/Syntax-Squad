package com.example.schoolboardapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingItemAdapter extends RecyclerView.Adapter<OnboardingItemAdapter.OnboardingViewHolder> {

    private final List<OnboardingItem> onboardingItems;

    public OnboardingItemAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_onboarding, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.bind(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, description;
        private final ImageView image;
        private final LinearLayout topContainer, bottomContainer;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            image = itemView.findViewById(R.id.imageOnboarding);
            topContainer = itemView.findViewById(R.id.topContainer);
            bottomContainer = itemView.findViewById(R.id.bottomContainer);
        }

        void bind(OnboardingItem item) {
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            image.setImageResource(item.getImage());

            if (item.getBackgroundType() == 1) {
                topContainer.setBackgroundColor(Color.parseColor("#7CB232")); // Green
                bottomContainer.setBackgroundColor(Color.WHITE);
            } else {
                topContainer.setBackgroundColor(Color.WHITE);
                bottomContainer.setBackgroundColor(Color.WHITE);
            }
            image.getLayoutParams().width = item.getImageWidth();
            image.getLayoutParams().height = item.getImageHeight();
            image.requestLayout();
        }
    }
}
