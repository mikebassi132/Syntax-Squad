package com.example.schoolboardapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.HomeCardViewHolder> {

    private final List<HomeCardItem> items;
    private final Context context;

    public HomeCardAdapter(List<HomeCardItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card_item, parent, false);
        return new HomeCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardViewHolder holder, int position) {
        HomeCardItem item = items.get(position);

        holder.cardImage.setImageResource(item.getImageRes());
        holder.cardTitle.setText(item.getTitle());
        holder.cardDescription.setText(item.getDescription());

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(item.getLabelColor());
        bg.setCornerRadius(20f);
        holder.cardTextContainer.setBackground(bg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = null;
            String title = item.getTitle().toLowerCase();

            switch (title) {
                case "services hub":
                    intent = new Intent(context, ServicesHubActivity.class);
                    break;
                case "your courses":
                    intent = new Intent(context, CoursesActivity.class); // ✅ Link it here if not yet
                    break;
                case "upcoming events":
                    intent = new Intent(context, EventsActivity.class);
                    break;
                case "book an appointment":
                    intent = new Intent(context, AppointmentsActivity.class);
                    break;
                case "your profile":
                    intent = new Intent(context, ProfileActivity.class);
                    break;
                case "campus map":
                    intent = new Intent(context, MapActivity.class);
            }

            if (intent != null) {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class HomeCardViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardTitle, cardDescription;
        LinearLayout cardTextContainer;

        public HomeCardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardDescription = itemView.findViewById(R.id.cardDescription);
            cardTextContainer = itemView.findViewById(R.id.cardTextContainer);
        }
    }
}
