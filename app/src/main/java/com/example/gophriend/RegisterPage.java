
package com.example.gophriend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gophriend.data.Message;
import com.example.gophriend.data.myDbAdapter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

//Source code: https://github.com/mitchtabian/DatePickerDialog
//Source code: https://www.youtube.com/watch?v=on_OrrX7Nw4

public class RegisterPage extends AppCompatActivity {
    //(String name, String pass, String displayname, String email, String dob, String major, String year, String interests)
    EditText Name, Pass, Displayname, Email;
    TextView DOB, Major, Year, Interests;
    myDbAdapter helper;
    private Button signUpButton;
    private Button yearButton;

    private static final String TAG = "RegisterPage";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //code for majors
    TextView Majors;
    boolean[] majors;
    ArrayList<Integer> majorslist = new ArrayList<>();
    String[] majorsArray = {"Computer Science", "Physics"};

    //code for Interests
    //TextView Interests;
    boolean[] interests;
    ArrayList<Integer> interestslist = new ArrayList<>();
    String[] interestsArray =  {"Chess",
            "Golf",
            "Video Games",
            "Boba",
            "Hotpot",
            "Basketball",
            "3D printing",
            "Amateur radio",
            "Scrapbook",
            "Amateur radio",
            "Acting",
            "Baton twirling",
            "Board games",
            "Book restoration",
            "Cabaret",
            "Calligraphy",
            "Candle making",
            "Computer programming",
            "Coffee roasting",
            "Cooking",
            "Colouring",
            "Cosplaying",
            "Couponing",
            "Creative writing",
            "Crocheting",
            "Cryptography",
            "Dance",
            "Digital arts",
            "Drama",
            "Drawing",
            "Do it yourself",
            "Electronics",
            "Embroidery",
            "Fashion",
            "Flower arranging",
            "Foreign language learning",
            "Gaming",
            "Tabletop games",
            "Role-playing games",
            "Gambling",
            "Genealogy",
            "Glassblowing",
            "Gunsmithing",
            "Homebrewing",
            "Ice skating",
            "Jewelry making",
            "Jigsaw puzzles",
            "Juggling",
            "Knapping",
            "Knitting",
            "Kabaddi",
            "Knife making",
            "Lacemaking",
            "Lapidary",
            "Leather crafting",
            "Lego building",
            "Lockpicking",
            "Machining",
            "Macrame",
            "Metalworking",
            "Magic",
            "Model building",
            "Listening to music",
            "Origami",
            "Painting",
            "Playing musical instruments",
            "Pet",
            "Poi",
            "Pottery",
            "Puzzles",
            "Quilting",
            "Reading",
            "Scrapbooking",
            "Sculpting",
            "Sewing",
            "Singing",
            "Sketching",
            "Soapmaking",
            "Sports",
            "Stand-up comedy",
            "Sudoku",
            "Table tennis",
            "Taxidermy",
            "Video gaming",
            "Watching movies",
            "Web surfing",
            "Whittling",
            "Wood carving",
            "Woodworking",
            "World Building",
            "Writing",
            "Yoga",
            "Yo-yoing",
            "Air sports",
            "Archery",
            "Astronomy",
            "Backpacking",
            "Base jumping",
            "Baseball",
            "Basketball",
            "Beekeeping",
            "Bird watching",
            "Blacksmithing",
            "Board sports",
            "Bodybuilding",
            "Brazilian jiu-jitsu",
            "Community",
            "Cycling",
            "Dowsing",
            "Driving",
            "Fishing",
            "Flag football",
            "Flying",
            "Flying disc",
            "Foraging",
            "Gardening",
            "Geocaching",
            "Ghost hunting",
            "Graffiti",
            "Handball",
            "Hiking",
            "Hooping",
            "Horseback riding",
            "Hunting",
            "Inline skating",
            "Jogging",
            "Kayaking",
            "Kite flying",
            "Kitesurfing",
            "Larping",
            "Letterboxing",
            "Metal detecting",
            "Motor sports",
            "Mountain biking",
            "Mountaineering",
            "Mushroom hunting",
            "Mycology",
            "Netball",
            "Nordic skating",
            "Orienteering",
            "Paintball",
            "Parkour",
            "Photography",
            "Polo",
            "Rafting",
            "Rappelling",
            "Rock climbing",
            "Roller skating",
            "Rugby",
            "Running",
            "Sailing",
            "Sand art",
            "Scouting",
            "Scuba diving",
            "Sculling",
            "Rowing",
            "Shooting",
            "Shopping",
            "Skateboarding",
            "Skiing",
            "Skim Boarding",
            "Skydiving",
            "Slacklining",
            "Snowboarding",
            "Stone skipping",
            "Surfing",
            "Swimming",
            "Taekwondo",
            "Tai chi",
            "Urban exploration",
            "Vacation",
            "Vehicle restoration",
            "Water sports"};


    //code for Year
    TextView Years;
    boolean[] years;
    ArrayList<Integer> yearlist = new ArrayList<>();
    CharSequence[] yearArray = {"Incoming", "Freshman", "Sophomore", "Junior", "Senior", "Graduate Student"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);


        Displayname= (EditText) findViewById(R.id.editDisplayName);
        Email= (EditText) findViewById(R.id.editDisplayName2);
        DOB = (TextView) findViewById(R.id.DOB);
        Major= (TextView) findViewById(R.id.Input_Major);
        Year= (TextView) findViewById(R.id.select_year);
        Interests= (TextView) findViewById(R.id.Select_interests);

        helper = new myDbAdapter(this);

        signUpButton = (Button) findViewById(R.id.Register);

        //Code for Interests
        Interests = (TextView) findViewById(R.id.Select_interests);
        //Selected Interests array
        interests = new boolean[interestsArray.length];

        Interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //initilize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RegisterPage.this);
                builder.setTitle("Select your Interests");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(interestsArray, interests, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check conditions
                        if (isChecked){
                            interestslist.add(which);
                            Collections.sort(interestslist);

                        }else{
                            interestslist.remove(which);

                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < interestslist.size(); j++){
                            stringBuilder.append(interestsArray[interestslist.get(j)]);
                            if(j != interestslist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        Interests.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < interests.length; j++){
                            interests[j] = false;
                            interestslist.clear();
                            Interests.setText("");
                        }
                    }
                });
                builder.show();
            }
        });


        //Code for majors
        Majors = (TextView) findViewById(R.id.Input_Major);
        //Selected major array
        majors = new boolean[majorsArray.length];

        Majors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initilize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RegisterPage.this);
                builder.setTitle("Select your major");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(majorsArray, majors, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //check conditions
                        if (isChecked){
                            majorslist.add(which);
                            Collections.sort(majorslist);

                        }else{
                            majorslist.remove(which);

                        }
                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j = 0; j < majorslist.size(); j++){
                            stringBuilder.append(majorsArray[majorslist.get(j)]);
                            if(j != majorslist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        Majors.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j = 0; j < majors.length; j++){
                            majors[j] = false;
                            majorslist.clear();
                            Majors.setText("");
                        }
                    }
                });
                builder.show();
            }
        });
        //Source: https://www.youtube.com/watch?v=rTXafzJP3Lk

        Years = (TextView) findViewById(R.id.select_year);

        Years.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegisterPage.this);
                alertDialogBuilder.setTitle("Select your year");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setSingleChoiceItems(yearArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Years.setText(yearArray[which]);
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });


        //Code for Date of Birth
        mDisplayDate = (TextView) findViewById(R.id.DOB);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterPage.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }
    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();


        String t3 = Displayname.getText().toString();
        String t4 = Email.getText().toString();
        String t5 = DOB.getText().toString();
        String t6 = Major.getText().toString();
        String t7 = Year.getText().toString();
        String t8 = Interests.getText().toString();


        if(t1.isEmpty() || t2.isEmpty()|| t3.isEmpty()|| t4.isEmpty()|| t5.isEmpty()|| t6.isEmpty()|| t7.isEmpty()|| t8.isEmpty())
        {
            Message.message(getApplicationContext(),"All fields must be complete");
        }
        else if(!t4.endsWith("@umn.edu"))
        {
            Message.message(getApplicationContext(),"Only sign up with UMN email");
        }
        else if(t2.length() < 5)
        {
            Message.message(getApplicationContext(),"Password must be greater than 5 characters");
        }
        else
        {
            long id = helper.insertData(t1,t2,t3,t4,t5,t6,t7,t8);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Unable To sign up, please try again");
                Name.setText("");
                Pass.setText("");
                Displayname.setText("");
                Email.setText("");
                DOB.setText("");
                Major.setText("");
                Year.setText("");
                Interests.setText("");
            } else
            {
                //Message.message(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                Pass.setText("");
                Displayname.setText("");
                Email.setText("");
                DOB.setText("");
                Major.setText("");
                Year.setText("");
                Interests.setText("");
                //Button activity, Cite:https://www.youtube.com/watch?v=bgIUdb-7Rqo
                signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSignUpActivity();
                    }
                });
            }
        }
    }

    public void openSignUpActivity() {
        Intent singUpIntent = new Intent(this, MainActivity.class);
        startActivity(singUpIntent);
    }
}