package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewBadges extends AppCompatActivity {

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_badges);
        int correct;
        int incorrect;
        double Ratio;

        TextView NFL = findViewById(R.id.NFL);
        TextView NFLHS = findViewById(R.id.NFLHS);
        TextView NFLTOT = findViewById(R.id.NFLTOT);

        TextView usCeleb = findViewById(R.id.USCeleb);
        TextView usCelebHS = findViewById(R.id.USCelebHS);
        TextView usCelebTOT = findViewById(R.id.USCelebTOT);

        TextView NBA = findViewById(R.id.NBA);
        TextView NBAHS = findViewById(R.id.NBAHS);
        TextView NBATOT = findViewById(R.id.NBATOT);

        TextView FastFood = findViewById(R.id.FastFood);
        TextView FastFoodHS = findViewById(R.id.FastFoodHS);
        TextView FastFoodTOT = findViewById(R.id.FastFoodTOT);

        TextView ContentCreator = findViewById(R.id.ContentCreator);
        TextView ContentCreatorHS = findViewById(R.id.ContentCreatorHS);
        TextView ContentCreatorTOT = findViewById(R.id.ContentCreatorTOT);

        TextView miniHS = findViewById(R.id.miniHS);
        TextView miniTOT = findViewById(R.id.miniTOT);

        NFL.setText(("NFL Players: " + GlobalVariables.gameState.getTotalGamesPlayed("NFL Players") + " completed game(s)"));
        NFLHS.setText("Best: " + GlobalVariables.gameState.getHighScore("NFL Players"));
        correct = GlobalVariables.gameState.getCorrectQuestions("NFL Players");
        incorrect = GlobalVariables.gameState.getIncorrectQuestions("NFL Players");
        Ratio  =0;
        if(incorrect != 0){
            Ratio = roundAvoid(((double) correct)/incorrect, 3);
        }
        NFLTOT.setText( correct+ " Correct, " + incorrect + " Incorrect, " + Ratio  );

        NBA.setText(("NBA Players: " + GlobalVariables.gameState.getTotalGamesPlayed("NBA Players") + " completed game(s)"));
        NBAHS.setText("Best: " + GlobalVariables.gameState.getHighScore("NBA Players"));
        correct = GlobalVariables.gameState.getCorrectQuestions("NBA Players");
        incorrect = GlobalVariables.gameState.getIncorrectQuestions("NBA Players");
        Ratio  =0;
        if(incorrect != 0){
            Ratio = roundAvoid(((double) correct)/incorrect, 3);
        }
        NBATOT.setText( correct+ " Correct, " + incorrect + " Incorrect, " + Ratio  );

        usCeleb.setText(("US Celebrities: " + GlobalVariables.gameState.getTotalGamesPlayed("US Celebs") + " completed game(s)"));
        usCelebHS.setText("Best: " + GlobalVariables.gameState.getHighScore("US Celebs"));
        correct = GlobalVariables.gameState.getCorrectQuestions("US Celebs");
        incorrect = GlobalVariables.gameState.getIncorrectQuestions("US Celebs");
        Ratio  =0;
        if(incorrect != 0){
            Ratio = roundAvoid(((double) correct)/incorrect, 3);
        }
        usCelebTOT.setText( correct+ " Correct, " + incorrect + " Incorrect, " + Ratio  );

        FastFood.setText(("Fast Food Companies: " + GlobalVariables.gameState.getTotalGamesPlayed("Fast Food Companies") + " completed game(s)"));
        FastFoodHS.setText("Best: " + GlobalVariables.gameState.getHighScore("Fast Food Companies"));
        correct = GlobalVariables.gameState.getCorrectQuestions("Fast Food Companies");
        incorrect = GlobalVariables.gameState.getIncorrectQuestions("Fast Food Companies");
        Ratio  =0;
        if(incorrect != 0){
            Ratio = roundAvoid(((double) correct)/incorrect, 3);
        }
        FastFoodTOT.setText( correct+ " Correct, " + incorrect + " Incorrect, " + Ratio  );

        ContentCreator.setText(("Content Creators: " + GlobalVariables.gameState.getTotalGamesPlayed("Content Creators") + " completed game(s)"));
        ContentCreatorHS.setText("Best: " + GlobalVariables.gameState.getHighScore("Content Creators"));
        correct = GlobalVariables.gameState.getCorrectQuestions("Content Creators");
        incorrect = GlobalVariables.gameState.getIncorrectQuestions("Content Creators");
        Ratio  =0;
        if(incorrect != 0){
            Ratio = roundAvoid(((double) correct)/incorrect, 3);
        }
        ContentCreatorTOT.setText( correct+ " Correct, " + incorrect + " Incorrect, " + Ratio  );


//        usnewsHS.setText("US Celebs Questions to Complete: " + GlobalVariables.gameState.getHighScore("US Celebs"));
//        worldnewsHS.setText("NBA Players Questions to Complete: " + GlobalVariables.gameState.getHighScore("NBA Players"));
//        FastFoodHS.setText("Fast Food Companies Questions to Complete: " + GlobalVariables.gameState.getHighScore("Fast Food Companies"));
//        ContentCreatorHS.setText("Content Creators Questions to Complete  : " + GlobalVariables.gameState.getHighScore("Content Creators"));
//
//
//        usnewsTOT.setText("US Celebs Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("US Celebs"));
//        worldnewsTOT.setText("NBA Players Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("NBA Players"));
//        FastFoodTOT.setText("Fast Food Companies Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("Fast Food Companies"));
//        ContentCreatorTOT.setText("Content Creators Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("Content Creators"));

        miniHS.setText("Mini Game High Score: " + GlobalVariables.gameState.getHighScore("mini"));
        miniTOT.setText("Mini Game Total Games: " + GlobalVariables.gameState.getTotalGamesPlayed("mini"));
    }
}