package com.example.jackf.lovematch;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b_compute;
    ImageView iv_needle;
    EditText et_YourName, et_OtherPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_compute = (Button) findViewById(R.id.b_comput);
        iv_needle = (ImageView) findViewById(R.id.iv_Needle);
        et_OtherPersonName = (EditText) findViewById(R.id.et_OtherPersonName);
        et_YourName = (EditText) findViewById(R.id.et_YourName);

        b_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourName = et_YourName.getText().toString().toLowerCase();
                String otherpersonName = et_OtherPersonName.getText().toString().toLowerCase();

                int totalLetters = yourName.length() + otherpersonName.length();
                int totalMatches = 0;

                for(int i = 0; i<yourName.length();i++){
                    for(int j = 0; j<otherpersonName.length();j++){
                        if(yourName.charAt(i) == otherpersonName.charAt(j)){
                            totalMatches++;
                        }

                    }
                }
                for(int i = 0; i<otherpersonName.length();i++){
                    for(int j = 0; j<yourName.length();j++){
                        if(otherpersonName.charAt(i) == yourName.charAt(j)){
                            totalMatches++;
                        }

                    }
                }

                float compatScore = (float) totalMatches/totalLetters;
                //loveScore is between -50 and +50
                int loveScore = ((int) (compatScore*100)) - 50;

                RotateAnimation ra = new RotateAnimation(0,360+loveScore, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                ra.setFillAfter(true);
                ra.setDuration(2000);
                ra.setInterpolator(new AccelerateDecelerateInterpolator());
                iv_needle.startAnimation(ra);

                Toast.makeText(MainActivity.this, "Love score of " + loveScore, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
