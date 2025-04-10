package com.example.schoolboardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentOptionAdapter extends RecyclerView.Adapter<AppointmentOptionAdapter.ViewHolder> {

    private Context context;
    private List<AppointmentOption> appointmentOptions;
    private OnItemClickListener onItemClickListener;


    public AppointmentOptionAdapter(Context context, List<AppointmentOption> appointmentOptions, OnItemClickListener listener) {
        this.context = context;
        this.appointmentOptions = appointmentOptions;
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppointmentOption appointmentOption = appointmentOptions.get(position);
        holder.professorNameTextView.setText(appointmentOption.getProfessorName());
        holder.availableTimeTextView.setText(appointmentOption.getAvailableTime());

        // ✅ Set icon based on name
        String name = appointmentOption.getProfessorName().toLowerCase();
        if (name.contains("professor")) {
            holder.iconImageView.setImageResource(R.drawable.ic_professor);
        } else if (name.contains("counselor")) {
            holder.iconImageView.setImageResource(R.drawable.ic_counselor);
        } else if (name.contains("advisor")) {
            holder.iconImageView.setImageResource(R.drawable.ic_advisor);
        } else {
            holder.iconImageView.setImageResource(R.drawable.ic_launcher_foreground); // fallback
        }

        // Set the click listener
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentOptions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView professorNameTextView;
        TextView availableTimeTextView;
        ImageView iconImageView; // ✅ Add ImageView reference

        public ViewHolder(View itemView) {
            super(itemView);
            professorNameTextView = itemView.findViewById(R.id.professorName);
            availableTimeTextView = itemView.findViewById(R.id.availableTime);
            iconImageView = itemView.findViewById(R.id.iconImage); // ✅ Link it here
        }
    }

    // Define the OnItemClickListener interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // ✅ Allow setting/changing the listener later
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
