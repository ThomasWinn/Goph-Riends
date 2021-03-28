
package com.example.gophriend.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gophriend.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index, String title) {
        Fragment fragment = null;

        if ("People".equalsIgnoreCase(title)) {
            Log.i("debug", title + " hits Home Fragment");
            fragment = new SwipeFragment();
        } else if ("Clubs".equalsIgnoreCase(title)) {
            Log.i("debug", title + " hits Third Fragment");
            fragment = new SwipeFragment();
        } else if ("Events".equalsIgnoreCase(title)) {
            Log.i("debug", title + " hits Swipe Fragment");
            fragment = new SwipeFragment();
        } else {
            Log.i("debug", title + " hits Default Fragment");
            fragment = new PlaceholderFragment();
        }

//        PlaceholderFragment
        Log.i("debug", String.format("The fragement of page %s is %d", title, fragment.hashCode()));
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_swipe_one, container, false);
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}

