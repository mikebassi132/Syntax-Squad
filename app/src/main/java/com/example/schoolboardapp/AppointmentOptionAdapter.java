package com.example.schoolboardapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentOptionAdapter extends RecyclerView.Adapter<AppointmentOptionAdapter.ViewHolder> {

    private List<AppointmentOption> options;
    private Context context;

    public AppointmentOptionAdapter(Context context, List<AppointmentOption> options) {
        this.context = context;
        this.options = options;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentOption option = options.get(position);
        holder.titleText.setText(option.getTitle());
        holder.descText.setText(option.getDescription());
        holder.iconImage.setImageResource(option.getImageResId());

        // Optional: handle click
        holder.itemView.setOnClickListener(v -> {
            // Add navigation or dialog if needed
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, descText;
        ImageView iconImage;

        public ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            descText = itemView.findViewById(R.id.descText);
            iconImage = itemView.findViewById(R.id.iconImage);
        }
    }
}
