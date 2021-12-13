package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class youWin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);
    }

    public void onCategoryPressed(View view) {
        finish();
//        Intent my_intent = new Intent(getBaseContext(),CategorySelect.class);
//        startActivity(my_intent);
    }
}