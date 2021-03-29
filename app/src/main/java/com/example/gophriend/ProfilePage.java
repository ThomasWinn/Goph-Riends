package com.example.gophriend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

// Sources
// https://www.youtube.com/watch?v=NEzcPRspAO8&t=662s gridview
// youtube.com/watch?v=ppEw_nzCgO4 recycler view
public class ProfilePage extends AppCompatActivity {

    GridView gridView;
    Integer[] image = {
            R.drawable.male1,
            R.drawable.male2,
            R.drawable.male3
    };

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    String[] interestArray = {
            "Chess",
            "Golf",
            "Video Games",
            "Boba",
            "Hotpot"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile_page_layout);
        setContentView(R.layout.activity_profile_page);

//        Toolbar toolbar = (Toolbar)findViewById(R.id.myToolBar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(null);
//
        gridView = findViewById(R.id.gridImage);
        gridView.setAdapter(new ImageAdapterGridView(this));


        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this, interestArray);
        recyclerView.setAdapter(adapter);

        ImageButton ib = findViewById(R.id.back_button);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, MainActivity.class));
            }
        });
    }

    public class ImageAdapterGridView extends BaseAdapter {

        private Context mContext;

        public ImageAdapterGridView(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return image.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(16, 16, 16, 16);
            }
            else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(image[position]);
            return imageView;
        }
    }
}