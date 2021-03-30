package com.example.gophriend.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.gophriend.Club_creation;
import com.example.gophriend.R;
import com.example.gophriend.event_creation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class event_discover extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_discover_page, container, false);
        FloatingActionButton button = view.findViewById(R.id.add);

        /**ImageView imgView1 = null;
        imgView1 = (ImageView) imgView1.findViewById(R.id.event1);
        imgView1.setImageResource(R.drawable.hackathon);

        ImageView imgView2 = null;
        imgView2 = (ImageView) imgView2.findViewById(R.id.event2);
        imgView2.setImageResource(R.drawable.football);


        ImageView imgView3 = null;
        imgView3 = (ImageView) imgView3.findViewById(R.id.event3);
        imgView3.setImageResource(R.drawable.rave);

        ImageView imgView4 = null;
        imgView4 = (ImageView) imgView4.findViewById(R.id.event4);
        imgView4.setImageResource(R.drawable.yoga);**/

        ImageButton ib = (ImageButton) view.findViewById(R.id.imageButton51);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Footballevent.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                //startActivity(new Intent(this, Club_creation.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), event_creation.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
            //startActivity(new Intent(this, Club_creation.class));
        }
    });
        return view;
    }
}
