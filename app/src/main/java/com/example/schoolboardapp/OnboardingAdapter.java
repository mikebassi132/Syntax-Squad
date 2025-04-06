package com.example.schoolboardapp;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private final List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_onboarding,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnboardingItem item = onboardingItems.get(position);
        Context context = holder.itemView.getContext();

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.image.setImageResource(item.getImage());

        // ✅ Apply image size in dp
        ViewGroup.LayoutParams params = holder.image.getLayoutParams();
        params.width = dpToPx(item.getImageWidth(), context);
        params.height = dpToPx(item.getImageHeight(), context);
        holder.image.setLayoutParams(params);

        // ✅ Apply green background to top half only if backgroundType is 1
        if (item.getBackgroundType() == 1) {
            holder.topLayout.setBackgroundColor(Color.parseColor("#7CB232")); // green
        } else {
            holder.topLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {

        LinearLayout topLayout;
        ImageView image;
        TextView title, description;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            topLayout = itemView.findViewById(R.id.topLayout); // container above image
            image = itemView.findViewById(R.id.imageOnboarding);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
        }
    }

    private int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );
    }
}
