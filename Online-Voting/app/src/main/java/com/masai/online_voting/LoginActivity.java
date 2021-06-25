package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.masai.online_voting.Model.Users;
import com.masai.online_voting.Prevalent.Prevalent;

import soup.neumorphism.NeumorphButton;

public class LoginActivity extends AppCompatActivity {
    public TextView CreateAccount;
    private EditText Phone,Password;
    private NeumorphButton Login;
    private DatabaseReference mref;
    private ProgressDialog LoadingBar;
    String myphone,mypassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CreateAccount=(TextView) findViewById(R.id.tvNewUser);
        Phone=(EditText)findViewById(R.id.loginphone);
        Password=(EditText)findViewById(R.id.loginpassword);
        Login=findViewById(R.id.layout_btnLoginAccount);

        LoadingBar=new ProgressDialog(this);
        mref= FirebaseDatabase.getInstance().getReference();
        mypassword=Password.getText().toString();
        myphone="+91"+Phone.getText().toString();

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(Phone.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Please enter your phone number..", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
                }
                else if(Phone.getText().toString().length() <10)
                {
                    Toast.makeText(LoginActivity.this, "Please enter correct phone number..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoginUser();
                }
            }
        });

    }
    private void LoginUser() {



        LoadingBar.setTitle("Login Account");
        LoadingBar.setMessage("Please wait while we are checking our credentials..");
        LoadingBar.setCanceledOnTouchOutside(false);
        LoadingBar.show();

        AllowAccessToUser(myphone,mypassword);
    }
    private void AllowAccessToUser(final String myphone, final String mypassword) {

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Users").child("+91"+Phone.getText().toString()).exists())
                {
                    final Users userdata=dataSnapshot.child("Users").child("+91"+Phone.getText().toString()).getValue(Users.class);
                    if(userdata.getPhone().equals("+91"+Phone.getText().toString()))
                    {

                        if(userdata.getPassword().equals(Password.getText().toString()))
                        {

                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Logged in Successfully..", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                            Prevalent.currentOnlineUser=userdata;
                            i.putExtra("phone","+91"+Phone.getText().toString());
                            startActivity(i);
                        }
                        else
                        {
                            LoadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "please enter correct password..", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    LoadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "please create your account first with this number ..", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}