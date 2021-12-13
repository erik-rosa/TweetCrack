package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewBadges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_badges);

        TextView sportsHS = findViewById(R.id.NFLHS);
        TextView usnewsHS = findViewById(R.id.USCelebHS);
        TextView worldnewsHS = findViewById(R.id.NBAHS);
        TextView FastFoodHS = findViewById(R.id.FastFoodHS);
        TextView ContentCreatorHS = findViewById(R.id.ContentCreatorHS);

        TextView sportsTOT = findViewById(R.id.NFLTOT);
        TextView usnewsTOT = findViewById(R.id.USCelebTOT);
        TextView worldnewsTOT = findViewById(R.id.NBATOT);
        TextView FastFoodTOT = findViewById(R.id.FastFoodTOT);
        TextView ContentCreatorTOT = findViewById(R.id.ContentCreatorTOT);

        TextView miniHS = findViewById(R.id.miniHS);
        TextView miniTOT = findViewById(R.id.miniTOT);

        sportsHS.setText("NFL Players Questions to Complete: " + GlobalVariables.gameState.getHighScore("NFL Players"));
        usnewsHS.setText("US Celebs Questions to Complete: " + GlobalVariables.gameState.getHighScore("US Celebs"));
        worldnewsHS.setText("NBA Players Questions to Complete: " + GlobalVariables.gameState.getHighScore("NBA Players"));
        FastFoodHS.setText("Fast Food Companies Questions to Complete: " + GlobalVariables.gameState.getHighScore("Fast Food Companies"));
        ContentCreatorHS.setText("Content Creators Questions to Complete  : " + GlobalVariables.gameState.getHighScore("Content Creators"));

        sportsTOT.setText("NFL Players Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("NFL Players"));
        usnewsTOT.setText("US Celebs Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("US Celebs"));
        worldnewsTOT.setText("NBA Players Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("NBA Players"));
        FastFoodTOT.setText("Fast Food Companies Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("Fast Food Companies"));
        ContentCreatorTOT.setText("Content Creators Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("Content Creators"));

        miniHS.setText("Mini Game High Score: " + GlobalVariables.gameState.getHighScore("mini"));
        miniTOT.setText("Mini Game Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("mini"));
    }
}