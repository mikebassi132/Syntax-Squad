package com.example.schoolboardapp;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private ImageView mapImageView;
    private Matrix matrix = new Matrix();
    private float scaleFactor = 1.0f;
    private float lastTouchX, lastTouchY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);  // Make sure this points to your correct layout

        mapImageView = findViewById(R.id.mapImageView);
        mapImageView.setImageResource(R.drawable.campus_map);  // Replace with your image

        // Set the initial matrix transformation to the ImageView
        mapImageView.setImageMatrix(matrix);

        // Set an onTouchListener to detect zoom and pan
        mapImageView.setOnTouchListener((v, event) -> {
            float scaleX, scaleY;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    lastTouchX = event.getX();
                    lastTouchY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float deltaX = event.getX() - lastTouchX;
                    float deltaY = event.getY() - lastTouchY;

                    matrix.postTranslate(deltaX, deltaY);
                    mapImageView.setImageMatrix(matrix);

                    lastTouchX = event.getX();
                    lastTouchY = event.getY();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    scaleFactor = calculateScaleFactor(event);
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        });
    }

    // Function to calculate zoom scale factor
    private float calculateScaleFactor(MotionEvent event) {
        float x0 = event.getX(0);
        float y0 = event.getY(0);
        float x1 = event.getX(1);
        float y1 = event.getY(1);

        float deltaX = x1 - x0;
        float deltaY = y1 - y0;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    // Optionally, you can handle zoom with pinch gestures
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float newScaleFactor = scaleFactor * (event.getY() - event.getY(0)) / 100;
        if (newScaleFactor > 0.5 && newScaleFactor < 3) {
            scaleFactor = newScaleFactor;
            matrix.setScale(scaleFactor, scaleFactor);
            mapImageView.setImageMatrix(matrix);
        }
        return super.onTouchEvent(event);
    }
}
