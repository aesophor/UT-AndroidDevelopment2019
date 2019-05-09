package com.example.aesophor.dail;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer music;
    private final static int MAX_VOLUME = 100;

    private String personName = "Unknown";
    private String phoneNumber = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_LONG).show();

        playVoice(this, R.raw.music);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_LONG).show();
        setVolume(MAX_VOLUME - 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_LONG).show();
        setVolume(MAX_VOLUME / 2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_LONG).show();
    }


    private void dial() {
        // Use format with "tel:" and phoneNumber created is stored in u.
        Uri u = Uri.parse("tel:" + phoneNumber);

        // Create the intent and set the data for the intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, u);

        try {
            // Launch the Phone app's dialer with a phone number to dial a call.
            startActivity(i);
        } catch (SecurityException s) {
            // Display the toast with exception message.
            Toast.makeText(this, s.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View v) {
        // Make dial according to the image button clicked.
        switch (v.getId()) {
            case R.id.button1:
                personName = "Mark Zuckerberg";
                phoneNumber = "9011000001";
                break;
            case R.id.button2:
                personName = "Sundar Pichai";
                phoneNumber = "9011000002";
                break;
            case R.id.button3:
                personName = "Tim Cook";
                phoneNumber = "9011000003";
                break;
            default:
                break;
        }

        dial();
    }

    public void playVoice(final Context context, int rawVoice) {
        music = MediaPlayer.create(context, rawVoice);
        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (music != null) {
                    music.release();
                }
            }
        });
        music.start();
    }

    public void setVolume(int newVolume) {
        final float volume = (float) (1 - (Math.log(MAX_VOLUME - newVolume) / Math.log(MAX_VOLUME)));
        music.setVolume(volume, volume);
    }
}