package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import soup.neumorphism.NeumorphTextView;

public class FinalActivity extends AppCompatActivity {

    NeumorphTextView V2;
    String PartyName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i=getIntent();
        PartyName=i.getStringExtra("partyname");


        V2=findViewById(R.id.v2);

        V2.setText("Your vote is submitted to "+PartyName);
        new AestheticDialog.Builder(FinalActivity.this, DialogStyle.FLASH, DialogType.SUCCESS)
                .setTitle("Success")
                .setMessage("Your vote has been recorded.")
                .setDarkMode(false)
                .setAnimation(DialogAnimation.SPIN)
                .setGravity(Gravity.BOTTOM)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.logout:

                Intent intent=new Intent(FinalActivity.this,Welcome.class);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}