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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final List<Course> courseList;
    private final Context context;

    public CourseAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.title.setText(course.getCode());
        holder.description.setText(course.getTitle());
        holder.time.setText(course.getTime());
        holder.icon.setImageResource(course.getImageRes());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseDetailActivity.class);
            intent.putExtra("code", course.getCode());
            intent.putExtra("description", course.getDescription());
            intent.putExtra("image", course.getImageRes());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, time;
        ImageView icon;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.courseCode);
            description = itemView.findViewById(R.id.courseTitle);
            time = itemView.findViewById(R.id.courseTime);
            icon = itemView.findViewById(R.id.courseImage);
        }
    }
}
