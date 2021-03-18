package com.cos.javagg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.cos.javagg.R;

public class SearchName extends AppCompatActivity {

    private ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

        close = findViewById(R.id.close);

        close.setOnClickListener(v -> {
            finish();
        });
    }
}