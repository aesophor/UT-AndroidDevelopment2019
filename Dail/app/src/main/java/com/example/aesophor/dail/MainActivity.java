package com.example.aesophor.dail;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String personName = "Unknown";
    private String phoneNumber = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void onClick(View v)
    {
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
}