package com.example.gophriend.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gophriend.R;

public class SwipeFragment extends Fragment {
    int count = 1;

    // PROFILE
    String name = "Katie Johnson";
    String age = "21";
    String major = "Psychology";
    String year = "Year 4";

    String[] commonInterest = {
            "Hiking",
            "Eating Food",
            "Camping"
    };

    String bio = "I love hiking and camping. I'm suprisingly funny by the way.";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        int[] img_array = {
                R.drawable.girl1,
                R.drawable.girl2
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

        // PLEASE HELP HERE ;_;
        // I just want to add a TextView under the GridLayout. The text can be set to anything for testing purposes.

        // Common Interest Grid Layout
        // QUESTION - HOW DO I COPY AND PASTE MULTIPLE TEXTVIEW UNDER THE GRIDLAYOUT?
        GridLayout gl = view.findViewById(R.id.gl_ci);
//        TextView tv = new TextView(this.getContext());
//        tv.setText(commonInterest[2]);
//        tv.setBackgroundColor(Color.parseColor("#FFEB3B"));
//        tv.setGravity(Gravity.CENTER);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(5,5,5,5);
//        tv.setLayoutParams(params);


        // This crashes
//        GridLayout.LayoutParams params = new GridLayout.LayoutParams(tv.getLayoutParams());
//        params.rowSpec = GridLayout.spec(0, 1);
//        params.columnSpec = GridLayout.spec(0,2);
//
//
//        tv.setLayoutParams(params);
//
//        gl.addView(tv);


//        // I've tried this... for some reason doesn't work like it's supposed to...
//        for (int i = 0; i < commonInterest.length; i++) {
//            TextView tvv = new TextView(getActivity());
//            tvv.setTextAppearance(this.getContext(), R.style.c_interest);
//            tvv.setText(commonInterest[i]);
//
//            gl.addView(tvv);
//        }

        // Pass and Send Request
        Button pass_button = view.findViewById(R.id.swipe_pass);
        Button req_button = view.findViewById(R.id.swipe_send_req);


        // Go to next person
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SwipeFragmentTwo sf = new SwipeFragmentTwo();
//                getFragmentManager().beginTransaction().replace(R.id.view_pager, sf).commit();
//                test tf = new test();
//                getFragmentManager().beginTransaction().replace(R.id.view_pager, tf).commit();
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
