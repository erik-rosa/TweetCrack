package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategorySelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);
    }

    public void onSportsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","NBAplayers/NBA-players");
        startActivity(my_intent);
    }
    public void onWorldNewsPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),GameScreen.class);
        my_intent.putExtra("category","palafo/World-News-List");
        startActivity(my_intent);
    }

}