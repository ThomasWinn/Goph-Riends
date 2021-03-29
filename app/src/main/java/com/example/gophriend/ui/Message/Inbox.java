package com.example.gophriend.ui.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gophriend.MainActivity;
import com.example.gophriend.ProfilePage;
import com.example.gophriend.R;

import java.util.ArrayList;

public class Inbox extends AppCompatActivity {
    ArrayList<String> users = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        users.add(" Demo(Friend's name)");
        ListView userListView = (ListView) findViewById(R.id.userListView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        userListView.setAdapter(arrayAdapter);
        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MessageMain.class);
                intent.putExtra("username", users.get(i));
                startActivity(intent);
            }
        });
        ImageButton ib = findViewById(R.id.back_button);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inbox.this, MainActivity.class));
            }
        });
    }
}