package com.masai.online_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.masai.online_voting.Model.Users;

import java.util.List;

public class AdminDashboard extends AppCompatActivity {
    private Button button;
    private String isVote="0";
    private DatabaseReference mref;
    String Phone;
    private HomeActivity homeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mref=database.getReference().child("Users");
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                      List<Users>  user=dataSnapshot.getValue();
//                        isVote=user.getName();
                        Toast.makeText(AdminDashboard.this,"Phone Number is"+isVote,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}