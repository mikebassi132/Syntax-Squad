package com.example.schoolboardapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final List<Course> courseList;

    public CourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseCode, courseInstructor;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseCode = itemView.findViewById(R.id.textCourseCode);
            courseInstructor = itemView.findViewById(R.id.textInstructor);
        }
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.courseCode.setText(course.getCode());
        holder.courseInstructor.setText(course.getInstructor());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}

