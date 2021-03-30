package com.example.gophriend.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gophriend.Club_creation;
import com.example.gophriend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;


public class club_discover_anime extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_discover_anime, container, false);
        FloatingActionButton button = view.findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Club_creation.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                //startActivity(new Intent(this, Club_creation.class));
            }
        });
        return view;
    }

}
