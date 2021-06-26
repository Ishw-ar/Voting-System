package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import soup.neumorphism.NeumorphButton;

public class VerifyId extends AppCompatActivity {
    String Phone;
    EditText Id;
    NeumorphButton IdButton;
    private DatabaseReference mref;

    String myId,myAge,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_id);
        Intent i=getIntent();
        Phone=i.getStringExtra("phone");

        Id=(EditText) findViewById(R.id.idproof);
        IdButton=findViewById(R.id.verifyagebutton);
        mref= FirebaseDatabase.getInstance().getReference();
        IdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(Id.getText().toString()))
                {
                    new AestheticDialog.Builder(VerifyId.this, DialogStyle.TOASTER, DialogType.ERROR)
                            .setTitle("Error")
                            .setMessage("Please enter your id")
                            .setDarkMode(false)
                            .setAnimation(DialogAnimation.DIAGONAL)
                            .setGravity(Gravity.TOP)
                            .show();
                }
                else
                {
                    mref.child("Users").child(Phone).child("ID").setValue(Id.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    Intent i=new Intent(VerifyId.this,CameraActivity.class);
                                    i.putExtra("phone",Phone);
                                    startActivity(i);
                                }
                            });
                }
            }
        });
    }
}