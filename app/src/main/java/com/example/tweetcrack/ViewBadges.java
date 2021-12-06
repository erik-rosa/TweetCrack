package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewBadges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_badges);

        TextView sportsHS = findViewById(R.id.sportsHS);
        TextView usnewsHS = findViewById(R.id.usnewsHS);
        TextView worldnewsHS = findViewById(R.id.worldnewsHS);

        TextView sportsTOT = findViewById(R.id.sportsTOT);
        TextView usnewsTOT = findViewById(R.id.usnewsTOT);
        TextView worldnewsTOT = findViewById(R.id.worldnewsTOT);

        TextView miniHS = findViewById(R.id.miniHS);
        TextView miniTOT = findViewById(R.id.miniTOT);

        sportsHS.setText("Sports High Score: " + GlobalVariables.gameState.getHighScore("sports"));
        usnewsHS.setText("US News High Score: " + GlobalVariables.gameState.getHighScore("usnews"));
        worldnewsHS.setText("World News High Score: " + GlobalVariables.gameState.getHighScore("worldnews"));

        sportsTOT.setText("Sports Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("sports"));
        usnewsTOT.setText("US News Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("usnews"));
        worldnewsTOT.setText("World News Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("worldnews"));

        miniHS.setText("Mini Game High Score: " + GlobalVariables.gameState.getHighScore("mini"));
        miniTOT.setText("Mini Game Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("mini"));
    }
}