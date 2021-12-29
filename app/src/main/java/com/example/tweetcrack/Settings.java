package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import winterwell.jtwitter.Twitter;

import static com.example.tweetcrack.Hidden.USER;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView account = findViewById(R.id.account);
        Button signOut = findViewById(R.id.signOutButton);
        if(GlobalVariables.loggedIn == true){
            signOut.setVisibility(View.VISIBLE);
            String[] details = USER.split("&");
            String username = details[3].substring(details[3].indexOf("=") + 1);
            account.setText("Twitter Account: " + username);
        }
    }

    public void getInContactButton(View view){
        Uri uri = Uri.parse("http://thepeytonmarinelli.info/"); // missing 'http://' will cause crashed
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent2);
    }
    public void signOutButton(View view){
        GlobalVariables.loggedIn = false;
        TextView account = findViewById(R.id.account);
        Button signOut = findViewById(R.id.signOutButton);
        signOut.setVisibility(View.INVISIBLE);
        account.setText("Twitter Account: Not logged in");
    }
    public void clearStatsButton(View view){
        GlobalVariables.gameState.clearStats();
        finish();
    }

    public void updateSwitch(View view){
        Switch soundOnOff = findViewById(R.id.switch1);
        GlobalVariables.soundOn = soundOnOff.isChecked();
    }
    @Override
    public void onResume(){
        super.onResume();
        Switch soundOnOff = findViewById(R.id.switch1);
        soundOnOff.setChecked(GlobalVariables.soundOn);
    }
}