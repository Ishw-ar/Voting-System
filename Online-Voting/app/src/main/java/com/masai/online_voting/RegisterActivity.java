package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    private TextView LoginToRegister;
//    private SignInButton signInButton;
//    private GoogleSignInClient mGoogleSignInClient;
//    private String TAG = "RegisterActivity";
//    private FirebaseAuth mAuth;
//    private int RC_SIGN_IN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
    }
}