package com.masai.online_voting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import soup.neumorphism.NeumorphCardView;

public class SplashScreen extends AppCompatActivity {

         public NeumorphCardView button;
         public Animation frombottom,froTop;
         public ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        button=(NeumorphCardView) findViewById(R.id.btnStarted);
        image=(ImageView) findViewById(R.id.voteimage);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.animbottom);
        button.setAnimation(frombottom);
        froTop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        image.setAnimation(froTop);
    }
}