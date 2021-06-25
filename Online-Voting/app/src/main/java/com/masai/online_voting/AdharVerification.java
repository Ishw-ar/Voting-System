package com.masai.online_voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import soup.neumorphism.NeumorphButton;

public class AdharVerification extends AppCompatActivity {
    private EditText mEtAadharNumber;


    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhar_verification);
        Intent i=getIntent();


        mEtAadharNumber=findViewById(R.id.etAdharNumber);

        view=findViewById(R.id.progressButtonAdhar);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validateAadharNumber(mEtAadharNumber.getText().toString())){
                    final ProgressButton progressButton=new ProgressButton(AdharVerification.this,view);
                    progressButton.buttonActivated();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressButton.buttonFinished();
                            Handler handler1=new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i=new Intent(AdharVerification.this,Welcome.class);

                                    startActivity(i);
                                }
                            },2000);
                        }
                    },3000);
                }else{
                    Toast.makeText(AdharVerification.this, "Please enter a valid adhar number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public static boolean validateAadharNumber(String aadharNumber){
        Pattern aadharPattern = Pattern.compile("\\d{12}");
        boolean isValidAadhar = aadharPattern.matcher(aadharNumber).matches();
        if(isValidAadhar){
            isValidAadhar = VerhoeffAlgorithm.validateVerhoeff(aadharNumber);
        }
        return isValidAadhar;
    }
}
class VerhoeffAlgorithm{
    static int[][] d  = new int[][]
            {
                    {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {1, 2, 3, 4, 0, 6, 7, 8, 9, 5},
                    {2, 3, 4, 0, 1, 7, 8, 9, 5, 6},
                    {3, 4, 0, 1, 2, 8, 9, 5, 6, 7},
                    {4, 0, 1, 2, 3, 9, 5, 6, 7, 8},
                    {5, 9, 8, 7, 6, 0, 4, 3, 2, 1},
                    {6, 5, 9, 8, 7, 1, 0, 4, 3, 2},
                    {7, 6, 5, 9, 8, 2, 1, 0, 4, 3},
                    {8, 7, 6, 5, 9, 3, 2, 1, 0, 4},
                    {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
            };
    static int[][] p = new int[][]
            {
                    {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {1, 5, 7, 6, 2, 8, 3, 0, 9, 4},
                    {5, 8, 0, 3, 7, 9, 6, 1, 4, 2},
                    {8, 9, 1, 6, 0, 4, 3, 5, 2, 7},
                    {9, 4, 5, 3, 1, 2, 6, 8, 7, 0},
                    {4, 2, 8, 6, 5, 7, 3, 9, 0, 1},
                    {2, 7, 9, 3, 8, 0, 6, 4, 1, 5},
                    {7, 0, 4, 6, 9, 1, 3, 2, 5, 8}
            };
    static int[] inv = {0, 4, 3, 2, 1, 5, 6, 7, 8, 9};

    public static boolean validateVerhoeff(String num){
        int c = 0;
        int[] myArray = StringToReversedIntArray(num);
        for (int i = 0; i < myArray.length; i++){
            c = d[c][p[(i % 8)][myArray[i]]];
        }

        return (c == 0);
    }
    private static int[] StringToReversedIntArray(String num){
        int[] myArray = new int[num.length()];
        for(int i = 0; i < num.length(); i++){
            myArray[i] = Integer.parseInt(num.substring(i, i + 1));
        }
        myArray = Reverse(myArray);
        return myArray;
    }
    private static int[] Reverse(int[] myArray){
        int[] reversed = new int[myArray.length];
        for(int i = 0; i < myArray.length ; i++){
            reversed[i] = myArray[myArray.length - (i + 1)];
        }
        return reversed;
    }
}

class ProgressButton{
    private CardView cardView;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private TextView textView;
    Animation fade_in;
    ProgressButton(Context ct,View view){
cardView=view.findViewById(R.id.cardViewAdhar);
layout=view.findViewById(R.id.constrainLayoutAdhar);
progressBar=view.findViewById(R.id.progressBar2);
textView=view.findViewById(R.id.tvAdhar);
    }
    void  buttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Please Wait....");

    }
    @SuppressLint("ResourceAsColor")
    void buttonFinished(){
        layout.setBackgroundColor(R.color.purple_200);
        progressBar.setVisibility(View.GONE);
        textView.setText("Done");
    }
}