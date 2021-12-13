package com.example.tweetcrack;

import static com.example.tweetcrack.Hidden.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CategorySelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);

        if (GlobalVariables.loggedIn && !USER.isEmpty()) {
            String[] details = USER.split("&");
            String username = details[3].substring(details[3].indexOf("=") + 1);
            Button friendsCategoryButton = (Button) findViewById(R.id.button8);
            friendsCategoryButton.setVisibility(View.VISIBLE);
        }
    }

    public void onSportsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","NBA/NBA-Players");
        my_intent.putExtra("categoryName","NBA Players");
        startActivity(my_intent);
    }
    public void onNFLPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","NFLPlayers/NFLPlayers");
        my_intent.putExtra("categoryName","NFL Players");
        startActivity(my_intent);
    }

    //usweekly/US-Celebs
    public void onCelebsNewsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","usweekly/US-Celebs");
        my_intent.putExtra("categoryName","US Celebs");
        startActivity(my_intent);
    }
    //BJohnsonxAR
    public void onFastFoodPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","1432536823058743297");
        my_intent.putExtra("categoryName","Fast Food Companies");
        startActivity(my_intent);
    }

    //Cocodog98
    public void onContentCreatorsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","1469170443697328131");
        my_intent.putExtra("categoryName","Content Creators");
        startActivity(my_intent);
    }


    public void onFriendsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","friends");
        startActivity(my_intent);
    }
}