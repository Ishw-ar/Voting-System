package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphTextView;

public class HomeActivity extends AppCompatActivity {
    ImageView Auth,Tick;
    private ProgressDialog LoadingBar;
    private DatabaseReference mref;
    String Phone;
    public String isVote="0";
    NeumorphTextView VoteNowTv,VotedTv;
    private NeumorphCardView cardView1,cardView2;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i=getIntent();
        Phone = i.getStringExtra("phone");
        mref= FirebaseDatabase.getInstance().getReference();

        Auth=(ImageView)findViewById(R.id.authenticate);
        Tick=(ImageView)findViewById(R.id.votedicon);
        VoteNowTv=findViewById(R.id.votenowtv);
        VotedTv=findViewById(R.id.votedtv);
        cardView1=findViewById(R.id.edittext_card_home);
        cardView2=findViewById(R.id.voted_card_home);
        mref.child("Users").child(Phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isVote=dataSnapshot.child("Vote").getValue().toString();
                if(isVote.equals("1"))
                {
                    Tick.setVisibility(View.VISIBLE);
                    VotedTv.setVisibility(View.VISIBLE);
                    VoteNowTv.setVisibility(View.INVISIBLE);
                    Auth.setVisibility(View.INVISIBLE);
                    cardView1.setVisibility(View.INVISIBLE);
                    cardView2.setVisibility(View.VISIBLE);
                    new AestheticDialog.Builder(HomeActivity.this, DialogStyle.FLAT, DialogType.ERROR)
                            .setTitle("Please try in next election")
                            .setMessage("Sorry you have already used your vote!!")
                            .setDarkMode(false)
                            .setAnimation(DialogAnimation.SHRINK)
                            .setGravity(Gravity.TOP)
                            .show();
                }
                else
                {
                    Tick.setVisibility(View.INVISIBLE);
                    VotedTv.setVisibility(View.INVISIBLE);
                    VoteNowTv.setVisibility(View.VISIBLE);
                    Auth.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.INVISIBLE);
                    new AestheticDialog.Builder(HomeActivity.this, DialogStyle.FLAT, DialogType.INFO)
                            .setTitle("One vote can make change")
                            .setMessage("Your vote is precious, use carefully!!")
                            .setDarkMode(false)
                            .setAnimation(DialogAnimation.SHRINK)
                            .setGravity(Gravity.BOTTOM)
                            .show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        LoadingBar=new ProgressDialog(this);



        final Executor executor = Executors.newSingleThreadExecutor();





        final BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Authentication")
                .setSubtitle("")
                .setDescription("place you finger to vote")
                .setNegativeButton("Cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).build();

        final HomeActivity activity=this;

        Auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Intent i=new Intent(HomeActivity.this, VerifyId.class);
                                i.putExtra("phone",Phone);
                                startActivity(i);

                                /**LoadingBar.setTitle("Please Wait");
                                 LoadingBar.setMessage("Please wait while your vote is submitting in our database..");
                                 LoadingBar.setCanceledOnTouchOutside(false);
                                 LoadingBar.show();


                                 mref.child("Users").child(Phone).child("Vote").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                LoadingBar.dismiss();

                                Tick.setVisibility(View.VISIBLE);
                                VotedTv.setVisibility(View.VISIBLE);
                                VoteNowTv.setVisibility(View.INVISIBLE);
                                Auth.setVisibility(View.INVISIBLE);
                                }
                                });**/

                            }
                        });
                    }
                });
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.logout2:

                Intent intent=new Intent(HomeActivity.this,Welcome.class);
                startActivity(intent);
                return true;

            case R.id.updatepassword2:
                Intent intent2=new Intent(HomeActivity.this,UserUpdatePassword.class);
                intent2.putExtra("phone",Phone);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}