package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.masai.online_voting.Model.Users;
import com.masai.online_voting.Prevalent.Prevalent;

import soup.neumorphism.NeumorphButton;

public class AdminLogin extends AppCompatActivity {
    private EditText Email_admin, Password_admin;
    private NeumorphButton Login_admin;
    private DatabaseReference mRef_admin;
    private ProgressDialog LoadingBar_admin;
    String myEmail, myPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);
        Email_admin = (EditText) findViewById(R.id.loginEmail_admin);
        Password_admin = (EditText) findViewById(R.id.loginpassword_admin);
        Login_admin = findViewById(R.id.layout_btnLoginAccount_admin);

        LoadingBar_admin = new ProgressDialog(this);
        mRef_admin = FirebaseDatabase.getInstance().getReference();
        myPassword = Password_admin.getText().toString();
        myEmail = Email_admin.getText().toString();
        Login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(Email_admin.getText().toString())) {
                    Toast.makeText(AdminLogin.this, "Please enter your Email..", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(Password_admin.getText().toString())) {
                    Toast.makeText(AdminLogin.this, "Please enter your password...", Toast.LENGTH_SHORT).show();
                } else {
                    LoginAdmin();
                }
            }
        });
    }

    private void LoginAdmin() {


        LoadingBar_admin.setTitle("Login Account");
        LoadingBar_admin.setMessage("Please wait while we are checking our credentials..");
        LoadingBar_admin.setCanceledOnTouchOutside(false);
        LoadingBar_admin.show();

        AllowAccessToAdmin(myEmail, myPassword);
    }

    private void AllowAccessToAdmin(final String myphone, final String mypassword) {

        if ((Email_admin.getText().toString()).equals("kundan211097@gmail.com")) {

            if ((Password_admin.getText().toString()).equals("admin")) {

                LoadingBar_admin.dismiss();
                Toast.makeText(AdminLogin.this, "Logged in Successfully..", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AdminLogin.this, AdminDashboard.class);
                startActivity(i);
            } else {
                LoadingBar_admin.dismiss();
                Toast.makeText(AdminLogin.this, "please enter correct password..", Toast.LENGTH_SHORT).show();
            }
        }

    }


}