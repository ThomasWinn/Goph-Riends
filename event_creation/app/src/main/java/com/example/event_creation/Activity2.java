package com.example.event_creation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Event name");
        String description = intent.getStringExtra("Event description");
        String interests_lists = intent.getStringExtra("Interests Lists");
        String date = intent.getStringExtra("Event Date");
        String Hour = intent.getStringExtra("Hour");
        String Minutes = intent.getStringExtra("Minutes");
        String LMAO = intent.getStringExtra("LUL");

        TextView textEname = (TextView) findViewById(R.id.Event_name);
        TextView textEdes = (TextView) findViewById(R.id.Event_description);
        TextView textInterests = (TextView) findViewById(R.id.Interests);
        TextView textDay = (TextView) findViewById(R.id.event_day);
        TextView textHour = (TextView) findViewById(R.id.Hour);
        TextView textMinutes = (TextView) findViewById(R.id.Minutes);
        TextView textLMAO = (TextView) findViewById(R.id.LMAO);

        textEname.setText(name);
        textEdes.setText(description);
        textInterests.setText(interests_lists);
        textDay.setText(date);
        textHour.setText(Hour);
        textMinutes.setText(Minutes);
        textLMAO.setText(LMAO);

        //Picture stuffs
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        image.setImageBitmap(bmp);
    }

}