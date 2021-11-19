package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onViewBadgesPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),ViewBadges.class);
        startActivity(my_intent);
    }
    public void onMiniGamePressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),MiniGame.class);
        startActivity(my_intent);
    }

    public void onCategoryPressed(View view) {
        Intent my_intent = new Intent(getBaseContext(),CategorySelect.class);
        startActivity(my_intent);
    }
}