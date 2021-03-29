package com.example.gophriend.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        /**ImageView imageclub1 = null;
        imageclub1 = (ImageView)  imageclub1.findViewById(R.id.club1);
        imageclub1.setImageResource(R.drawable.chess);

        //TextView clubt1 =(TextView) findViewById(R.id.clubText1);

        ImageView  imageclub2  = null;
        imageclub2 = (ImageView) imageclub2.findViewById(R.id.club2);
        imageclub2.setImageResource(R.drawable.skii);


        ImageView imageclub3 = null;
        imageclub3 = (ImageView) imageclub3.findViewById(R.id.club3);
        imageclub3.setImageResource(R.drawable.amongus);

        ImageView imageclub4 = null;
        imageclub4 = (ImageView) imageclub4.findViewById(R.id.club4);
        imageclub4.setImageResource(R.drawable.dance);**/

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
