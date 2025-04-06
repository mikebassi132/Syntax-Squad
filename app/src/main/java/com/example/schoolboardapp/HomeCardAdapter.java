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
        return new HomeCardViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.home_card_item, parent, false));
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

        // ✅ Handle clicks based on title
        holder.itemView.setOnClickListener(v -> {
            String title = item.getTitle();

            if (title.equalsIgnoreCase("Services Hub")) {
                context.startActivity(new Intent(context, ServicesHubActivity.class));
            } else if (title.equalsIgnoreCase("Your Courses")) {
                // Add your own CourseActivity here
            } else if (title.equalsIgnoreCase("Upcoming Events")) {
                context.startActivity(new Intent(context, EventsActivity.class));

            } else if (title.equalsIgnoreCase("Book an Appointment")) {
                context.startActivity(new Intent(context, AppointmentsActivity.class));
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
