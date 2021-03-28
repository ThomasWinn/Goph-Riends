package com.example.gophriend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class event_creation extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnPick, submit;

    TextView mDisplayDate, interests;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    Spinner spinnerHour, spinnerMinutes, spinnerLUL; //LUL here is AM or PM cuz i dont know what to name it

    boolean[] selectedInterests;

    ArrayList<Integer> interestsList = new ArrayList<>();
    String[] interestsArray = {"Soccer", "Video Games", "Chess", "Basketball", "Swimming", "Music", "Movies", };

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_creation);

        //Assign variables
        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        spinnerHour = findViewById(R.id.spinner);
        spinnerMinutes = findViewById(R.id.spinner2);
        spinnerLUL = findViewById(R.id.spinner3);

        interests = findViewById(R.id.Interests);

        btnPick = findViewById(R.id.button);

        submit = findViewById(R.id.button2);

        populateSpinnerHour();
        populateSpinnerMinute();
        populateSpinnerLUL();
        spinnerHour.setOnItemSelectedListener(this);
        spinnerMinutes.setOnItemSelectedListener(this);
        spinnerLUL.setOnItemSelectedListener(this);

        //Initialize selected interests array
        selectedInterests = new boolean[interestsArray.length];

        //Date select clicker
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        event_creation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        //This is for select multiple interests
        interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Initialize alert dialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(event_creation.this);
                //Set title
                builder2.setTitle("Interests Drop Down");

                //Set dialog non cancelable
                builder2.setCancelable(false);

                builder2.setMultiChoiceItems(interestsArray, selectedInterests, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if(b) {
                            //When checkbox selected
                            //Add position in day list
                            interestsList.add(i);
                            //Sort day list
                            Collections.sort(interestsList);
                        }
                        else {
                            //When checkbox unselected
                            //Remove position from day list
                            interestsList.remove(i);
                        }
                    }
                });

                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //Use for loop
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

        // This is for adding image
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(event_creation.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(event_creation.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }

    //These are the things to transfer over to different activity
    public void openActivity2() {

        EditText editText1 = (EditText) findViewById(R.id.event_name);
        String text = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.event_description);
        String text2 = editText2.getText().toString();

        String text3 = interests.getText().toString();

        String text4 = mDisplayDate.getText().toString();

        String hour = spinnerHour.getSelectedItem().toString();
        String minutes = spinnerMinutes.getSelectedItem().toString();
        String lul = spinnerLUL.getSelectedItem().toString();

        //Assign bitmap to pass picture to another activity
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.girl1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Intent intent = new Intent(this, event_display.class);
        intent.putExtra("Event name", text);
        intent.putExtra("Event description", text2);
        intent.putExtra("Interests Lists", text3);
        intent.putExtra("Event Date", text4);
        intent.putExtra("Hour", hour);
        intent.putExtra("Minutes", minutes);
        intent.putExtra("LUL", lul);
        intent.putExtra("picture", byteArray);
        startActivity(intent);
    }

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

    private void populateSpinnerMinute() {
        ArrayAdapter<CharSequence> minAdapter = ArrayAdapter.createFromResource(this, R.array.Minutes, android.R.layout.simple_spinner_item);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMinutes.setAdapter(minAdapter);
    }

    private void populateSpinnerHour() {
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.Hour, android.R.layout.simple_spinner_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHour.setAdapter(hourAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner) {
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