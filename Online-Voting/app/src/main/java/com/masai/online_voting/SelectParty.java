package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import soup.neumorphism.NeumorphFloatingActionButton;

public class SelectParty extends AppCompatActivity {
    NeumorphFloatingActionButton Party1,Party2,Party3,Party4,Party5;
    private DatabaseReference mref;
    private ProgressDialog LoadingBar;
    String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_party);
        Party1=findViewById(R.id.FirstParty);
        Party2=findViewById(R.id.SecondParty);
        Party3=findViewById(R.id.ThirdParty);
        Party4=findViewById(R.id.FourthParty);
        Party5=findViewById(R.id.FifthParty);
        Intent i=getIntent();
        Phone=i.getStringExtra("phone");
        mref= FirebaseDatabase.getInstance().getReference();
        LoadingBar=new ProgressDialog(this);
        Party1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to RJD");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();
                        mref.child("Users").child(Phone).child("Vote").setValue("1");

                        mref.child("Users").child(Phone).child("Party").setValue("Party1").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 1");

                                startActivity(i);

                                LoadingBar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();




            }
        });
        Party2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to INC");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();
                        mref.child("Users").child(Phone).child("Vote").setValue("1");

                        mref.child("Users").child(Phone).child("Party").setValue("Party2").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 2");

                                startActivity(i);

                                LoadingBar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        Party3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to BJP");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();
                        mref.child("Users").child(Phone).child("Vote").setValue("1");

                        mref.child("Users").child(Phone).child("Party").setValue("Party3").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 3");

                                startActivity(i);

                                LoadingBar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        Party4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you want to give your vote to JDU");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();
                        mref.child("Users").child(Phone).child("Vote").setValue("1");

                        mref.child("Users").child(Phone).child("Party").setValue("Party3").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 3");

                                startActivity(i);

                                LoadingBar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        Party5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SelectParty.this);
                builder.setTitle("Confirm Your Party");
                builder.setMessage("Do you really want to press NOTA!!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LoadingBar.setTitle("Voting Inprogress");
                        LoadingBar.setMessage("Please wait..");
                        LoadingBar.setCanceledOnTouchOutside(false);
                        LoadingBar.show();
                        mref.child("Users").child(Phone).child("Vote").setValue("1");

                        mref.child("Users").child(Phone).child("Party").setValue("Party3").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Intent i=new Intent(SelectParty.this,FinalActivity.class);
                                i.putExtra("phone",Phone);
                                i.putExtra("partyname","Party 3");

                                startActivity(i);

                                LoadingBar.dismiss();
                                Toast.makeText(SelectParty.this, "Your Vote is Submitted to our database..", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}