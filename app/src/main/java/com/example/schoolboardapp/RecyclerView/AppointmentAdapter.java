package com.example.schoolboardapp.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schoolboardapp.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<String> appointmentTimes;

    public AppointmentAdapter(List<String> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, int position) {
        holder.timeTextView.setText(appointmentTimes.get(position));
    }

    @Override
    public int getItemCount() {
        return appointmentTimes.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        public TextView timeTextView;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.appointmentTimeText);
        }
    }
}
