package com.example.gophriend.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gophriend.MainActivity;
import com.example.gophriend.R;
import com.example.gophriend.club_display;

public class Footballevent extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.football_event);
        ImageButton backout = (ImageButton) findViewById(R.id.imageButton53);
        backout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Footballevent.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
