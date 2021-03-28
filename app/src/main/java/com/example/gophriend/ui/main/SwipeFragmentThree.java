package com.example.gophriend.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gophriend.R;

public class SwipeFragmentThree extends Fragment {
    int count = 1;

    // PROFILE
    String name = "Kevin Freeman";
    String age = "23";
    String major = "Accounting";
    String year = "Year 5";

    String[] commonInterest = {
            "League of Legends",
            "Basketball",
            "Working Out",
            "Justin Bieber",
            "Luna",
            "Ping Pong"
    };

    String bio = "Do what you want in life. Follow your dreams.";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        int[] img_array = {
                R.drawable.guy1,
                R.drawable.guy2
        };
        View view = inflater.inflate(R.layout.fragment_swipe_one, container, false);


        // IMAGE BUTTON LOGIC
        ImageButton ib = (ImageButton) view.findViewById(R.id.swipe_pic);

        ib.setImageResource(img_array[0]);
        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count >= img_array.length) {
                    count = 0;
                }
                ib.setImageResource(img_array[count]);
                count = count + 1;
            }
        });

        // User info
        TextView uname = (TextView) view.findViewById(R.id.user_name);
        TextView uage = (TextView) view.findViewById(R.id.user_age);

        TextView umajor = (TextView) view.findViewById(R.id.swipe_major);
        TextView uyear = (TextView) view.findViewById(R.id.swipe_year);

        TextView ubio = (TextView) view.findViewById(R.id.swipe_bio);

        uname.setText(name);
        uage.setText(age);

        umajor.setText(major);
        uyear.setText(year);

        ubio.setText(bio);


        // Common Interest Grid Layout
        // QUESTION - HOW DO I COPY AND PASTE MULTIPLE TEXTVIEW AND INFLATE IT UNDER THE GRIDLAYOUT?

        // Pass and Send Request
        Button pass_button = view.findViewById(R.id.swipe_pass);
        Button req_button = view.findViewById(R.id.swipe_send_req);


        // Go to next person
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Go to next person and send a friend req notification
        req_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

}
