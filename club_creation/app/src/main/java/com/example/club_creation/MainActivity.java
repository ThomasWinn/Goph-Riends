package com.example.club_creation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerHour, spinnerMinutes, spinnerLUL;
    TextView club_date, interests;

    Button pickImage, submit;

    boolean[] selectedInterests;
    boolean[] selectedDay;
    ArrayList<Integer> dayList = new ArrayList<>();
    String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                        "Saturday", "Sunday"};
    ArrayList<Integer> interestsList = new ArrayList<>();
    String[] interestsArray = {"Soccer", "Video Games", "Chess", "Basketball", "Swimming", "Music", "Movies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        spinnerHour = findViewById(R.id.spinner1); //For hour
        spinnerMinutes = findViewById(R.id.spinner2); //For min
        spinnerLUL = findViewById(R.id.spinner3); //For AM PM
        populateSpinnerHour();
        populateSpinnerMinute();
        populateSpinnerLUL();
        spinnerHour.setOnItemSelectedListener(this);
        spinnerMinutes.setOnItemSelectedListener(this);
        spinnerLUL.setOnItemSelectedListener(this);


        club_date = findViewById(R.id.club_day);
        interests = findViewById(R.id.Interests);

        pickImage = findViewById(R.id.pick_image);

        submit = findViewById(R.id.button2);

        //Initialize selected array
        selectedDay = new boolean[dayArray.length];
        selectedInterests = new boolean[interestsArray.length];

        //This is to select multiple dates
        club_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //Set title
                builder.setTitle("Select Day");

                //Set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if(b) {
                            //When checkbox selected
                            //Add position in day list
                            dayList.add(i);
                            //Sort day list
                            Collections.sort(dayList);
                        }
                        else {
                            //When checkbox unselected
                            //Remove position from day list
                            dayList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //Use for loop
                        for (int j = 0; j < dayList.size(); j++) {
                            //Concat array value
                            stringBuilder.append(dayArray[dayList.get(j)]);
                            //Check condition
                            if (j != dayList.size()-1) {
                                //When j value not equal to day list size -1
                                //Add comma
                                stringBuilder.append(", ");
                            }
                        }
                        //Set text on text view
                        club_date.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Dismiss dialog
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Use for loop
                        for (int j=0; j < selectedDay.length; j++) {
                            //Remove all selection
                            selectedDay[j] = false;
                            //Clear day list
                            dayList.clear();
                            //Clear text view value
                            club_date.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        //This is for selecting multiple interests
        interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);

                builder2.setTitle("Interests Drop Down");

                builder2.setCancelable(false);

                builder2.setMultiChoiceItems(interestsArray, selectedInterests, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if(b) {
                            interestsList.add(i);
                            Collections.sort(interestsList);
                        }
                        else {
                            interestsList.remove(i);
                        }
                    }
                });

                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < interestsList.size(); j++) {
                            stringBuilder.append(interestsArray[interestsList.get(j)]);
                            //Check condition
                            if (j != interestsList.size()-1) {
                                //When j value not equal to interest list size -1
                                //Add comma
                                stringBuilder.append(", ");
                            }
                        }
                        //Set text on text view
                        interests.setText(stringBuilder.toString());
                    }
                });

                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Dismiss dialog
                        dialog.dismiss();
                    }
                });

                builder2.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Use for loop
                        for (int j=0; j < selectedInterests.length; j++) {
                            //Remove all selection
                            selectedInterests[j] = false;
                            //Clear day list
                            interestsList.clear();
                            //Clear text view value
                            interests.setText("");
                        }
                    }
                });
                builder2.show();
            }
        });

        //submit clicker to get to a different activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        //This is for adding image

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    //These are the things to transfer over to different activity
    public void openActivity2() {

        EditText editText1 = (EditText) findViewById(R.id.Club_name);
        String text = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.Club_description);
        String text2 = editText2.getText().toString();

        String text3 = interests.getText().toString();

        String text4 = club_date.getText().toString();

        String hour = spinnerHour.getSelectedItem().toString();
        String minutes = spinnerMinutes.getSelectedItem().toString();
        String lul = spinnerLUL.getSelectedItem().toString();

        //Assign bitmap to pass picture to another activity
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.images);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("Club name", text);
        intent.putExtra("Club description", text2);
        intent.putExtra("Interests Lists", text3);
        intent.putExtra("Club Date", text4);
        intent.putExtra("Hour", hour);
        intent.putExtra("Minutes", minutes);
        intent.putExtra("LUL", lul);
        intent.putExtra("picture", byteArray);
        startActivity(intent);
    }


    // This is for adding pictures
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            ImageView imageView = findViewById(R.id.imageView);
            List<Bitmap> bitmaps = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    bitmaps.add(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (final Bitmap b : bitmaps) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(b);
                            }
                        });
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    private void populateSpinnerLUL() {
        ArrayAdapter<CharSequence> lulAdapter = ArrayAdapter.createFromResource(this, R.array.lul, android.R.layout.simple_spinner_item);
        lulAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLUL.setAdapter(lulAdapter);
    }

    private void populateSpinnerHour() {
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.Hour, android.R.layout.simple_spinner_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(hourAdapter);
    }

    private void populateSpinnerMinute() {
        ArrayAdapter<CharSequence> minAdapter = ArrayAdapter.createFromResource(this, R.array.Minutes, android.R.layout.simple_spinner_item);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinutes.setAdapter(minAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner1) {
            String selectedHour = parent.getSelectedItem().toString();
            Toast.makeText(this, selectedHour, Toast.LENGTH_SHORT).show();
        }
        else if (parent.getId() == R.id.spinner2) {
            String selectedMin = parent.getSelectedItem().toString();
            Toast.makeText(this, selectedMin, Toast.LENGTH_SHORT).show();
        }
        else if (parent.getId() == R.id.spinner3) {
            String selectedLul = parent.getSelectedItem().toString();
            Toast.makeText(this, selectedLul, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}