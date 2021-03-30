
package com.example.gophriend;

import android.content.Intent;
import android.os.Bundle;

import com.example.gophriend.ui.Message.Inbox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.gophriend.ui.main.SectionsPagerAdapter;

// Source for GridLayout on swipe_ui https://www.youtube.com/watch?v=PFEb9FfopFo
public class MainActivity extends AppCompatActivity {

    int pass_value = -999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String value = intent.getStringExtra("value");
        if (value == null){
            value = "-999";
        }
        int val = Integer.parseInt(value); // -999
        pass_value = val;

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), pass_value); // communicate with tabs
        ViewPager viewPager = findViewById(R.id.view_pager); // finds the view pager
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs); // get tabs in activity main
        tabs.setupWithViewPager(viewPager); // add tab titles

        ImageButton ib = (ImageButton) findViewById(R.id.prof_butt);
        ImageButton inboxbutt = (ImageButton) findViewById(R.id.inboxButton);
        ImageButton nb = (ImageButton) findViewById(R.id.notification_butt);

        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Notification.class));
            }
        });

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfilePage.class));
            }
        });
        inboxbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Inbox.class));
            }
        });



//        ib.setOnClickListener(new View.OnClickListener {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ProfilePage.class));
//            }
//        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}