package com.example.gophriend.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gophriend.Club_creation;
import com.example.gophriend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class club_discover extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_discover_page, container, false);
        FloatingActionButton button = view.findViewById(R.id.add);


        ImageView imgView1 = null;
        imgView1 = (ImageView) imgView1.findViewById(R.id.club1);
        imgView1.setImageResource(R.drawable.chess);

        ImageView imgView2 = null;
        imgView2 = (ImageView) imgView2.findViewById(R.id.club2);
        imgView2.setImageResource(R.drawable.skii);


        ImageView imgView3 = null;
        imgView3 = (ImageView) imgView3.findViewById(R.id.club3);
        imgView3.setImageResource(R.drawable.amongus);

        ImageView imgView4 = null;
        imgView4 = (ImageView) imgView4.findViewById(R.id.club4);
        imgView4.setImageResource(R.drawable.dance);

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
