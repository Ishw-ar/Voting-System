package com.masai.online_voting;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.util.HashMap;
import java.util.Map;

import soup.neumorphism.NeumorphButton;


public class RegisterActivity extends AppCompatActivity {
    private TextView LoginToRegister;
//    private SignInButton signInButton;
//    private GoogleSignInClient mGoogleSignInClient;
//    private String TAG = "RegisterActivity";
//    private FirebaseAuth mAuth;
//    private int RC_SIGN_IN = 1;


    private EditText Name,Password,Phone;
    private NeumorphButton Register;
    private DatabaseReference mref;

    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name=(EditText)findViewById(R.id.nameentry);
        Password=(EditText)findViewById(R.id.passwordentry);
        Phone=(EditText)findViewById(R.id.phoneentry);
        Register=findViewById(R.id.layout_btnCreateAccount);
        mref= FirebaseDatabase.getInstance().getReference();
        LoadingBar=new ProgressDialog(this);

        LoginToRegister = (TextView) findViewById(R.id.tvlogintoregister);
//        signInButton = findViewById(R.id.google_sign);
//        mAuth = FirebaseAuth.getInstance();

        LoginToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
       // mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });
//    }
//
//    private void signIn() {
//
//        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent,RC_SIGN_IN);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RC_SIGN_IN){
//            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount acc=completedTask.getResult(ApiException.class);
//            Toast.makeText(RegisterActivity.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(acc);
//        }
//        catch (ApiException e){
//            Toast.makeText(RegisterActivity.this,"Signed In Failed",Toast.LENGTH_SHORT).show();
//            FirebaseGoogleAuth(null);
//        }
//    }

//    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
//        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
//        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull  Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(RegisterActivity.this,"Successful",Toast.LENGTH_SHORT).show();
//                    FirebaseUser user=mAuth.getCurrentUser();
//                    updateUI(null);
//                }
//                else {
//                    Toast.makeText(RegisterActivity.this,"Failed",Toast.LENGTH_SHORT).show();
//                    updateUI(null);
//                }
//
//            }
//        });
//
//    }

//    private void updateUI(FirebaseUser firebaseUser) {
//        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if(account!=null){
//
//        }
//
//    }

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(Name.getText().toString()))
                {
                    new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setMessage("Please Enter your name first")
                            .setDarkMode(true)
                            .setTitle("Error!")
                            .setAnimation(DialogAnimation.WINDMILL)
                            .setGravity(Gravity.TOP)
                            .show();
                }
                else if(TextUtils.isEmpty(Phone.getText().toString()))
                {
                    new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setMessage("Please Enter your phone number first")
                            .setDarkMode(true)
                            .setTitle("Error!")
                            .setAnimation(DialogAnimation.SLIDE_LEFT)
                            .setGravity(Gravity.TOP)
                            .show();
                }
                else if(TextUtils.isEmpty(Password.getText().toString()))
                {
                    new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setMessage("Please Enter your password first")
                            .setDarkMode(true)
                            .setTitle("Error!")
                            .setAnimation(DialogAnimation.SLIDE_LEFT)
                            .setGravity(Gravity.TOP)
                            .show();
                }
                else if(Phone.getText().toString().length()<10)
                {
                    new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setMessage("Please Enter correct phone number first")
                            .setDarkMode(true)
                            .setTitle("Error!")
                            .setAnimation(DialogAnimation.DIAGONAL)
                            .setGravity(Gravity.TOP)
                            .show();
                }

                else
                {
                    mref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(!(dataSnapshot.child("Users").child("+91"+Phone.getText().toString()).exists()))
                            {
                                CreateAccount(Phone,Name,Password);
                            }
                            else
                            {
                                new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                        .setMessage("Account  with this number already exists....")
                                        .setDarkMode(true)
                                        .setTitle("Error!")
                                        .setAnimation(DialogAnimation.SLIDE_LEFT)
                                        .setGravity(Gravity.TOP)
                                        .show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
    }
    private void CreateAccount(EditText phone, EditText name, EditText password) {
        Map<String,Object> details=new HashMap<String,Object>();
        details.put("Phone","+91"+phone.getText().toString());
        details.put("Name",name.getText().toString());
        details.put("Password",password.getText().toString());
        details.put("Vote","0");
        details.put("Party","");
        details.put("ID","");
        mref.child("Users").child("+91"+phone.getText().toString()).updateChildren(details)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            LoadingBar.dismiss();
                            new AestheticDialog.Builder(RegisterActivity.this, DialogStyle.FLASH, DialogType.ERROR)
                                    .setMessage("Account created SucessFully... ")
                                    .setDarkMode(true)
                                    .setTitle("Welcome")
                                    .setAnimation(DialogAnimation.SPIN)
                                    .setGravity(Gravity.TOP)
                                    .show();
                            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                        else
                        {
                            LoadingBar.dismiss();
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}