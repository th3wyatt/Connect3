package com.example.jon.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int isRed = 0;
    public ImageView currentTurn;
    public int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    public int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8}
            ,{0,3,6},{1,4,7},{2,5,8}
            ,{0,4,8},{2,4,6}
    };

    public ImageView[] squares = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentTurn = (ImageView) findViewById(R.id.currentTurnImage);
        squares = new ImageView[]{
                findViewById(R.id.bottomCenter)
                ,findViewById(R.id.bottomLeft)
                ,findViewById(R.id.bottomRight)
                ,findViewById(R.id.centerCenter)
                ,findViewById(R.id.centerLeft)
                ,findViewById(R.id.centerRight)
                ,findViewById(R.id.topCenter)
                ,findViewById(R.id.topLeft)
                ,findViewById(R.id.topRight)};
    }

    public void dropDown(View view) {

        ImageView clickedImage = (ImageView) view;
        int clickedSquare = Integer.parseInt(clickedImage.getTag().toString());

        if (gameState[clickedSquare] == 2) {
            clickedImage.setTranslationY(-1000f);

            if (isRed == 0) {
                clickedImage.setImageResource(R.drawable.red);
            } else {
                clickedImage.setImageResource(R.drawable.blue);
            }

            clickedImage.setAlpha(1f);
            clickedImage.animate().translationYBy(1000f).setDuration(200);

            gameState[clickedSquare] = isRed;

            if (isRed == 0) {
                isRed = 1;
                currentTurn.setImageResource(R.drawable.blue);

            } else {
                isRed = 0;
                currentTurn.setImageResource(R.drawable.red);

            }

            for (int[] winningPosition: winningPositions){

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){


                    ImageView winningText = (ImageView) findViewById(R.id.winnerImage);

                    ((TextView) findViewById(R.id.winnerText)).setText("Winner:");

                    if (gameState[winningPosition[0]] == 0){
                        winningText.setImageResource(R.drawable.red);
                        winningText.setAlpha(1f);
                    } else{
                        winningText.setImageResource(R.drawable.blue);
                        winningText.setAlpha(1f);

                    }

                    findViewById(R.id.endGameLayout).setVisibility(View.VISIBLE);
                    return;

                }

            }

        }            int counter = 0;
        for (int state: gameState){
            if (state != 2){
                counter++;
            }
        }
        if(counter == 9){
            ImageView winningText = (ImageView) findViewById(R.id.winnerImage);
            winningText.setAlpha(0f);
            ((TextView) findViewById(R.id.winnerText)).setText("DRAW!");
            findViewById(R.id.endGameLayout).setVisibility(View.VISIBLE);

        }

    }

    public void resetBoard(View view){

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        for (ImageView currentView: squares){
            currentView.setAlpha(0f);
        }

        findViewById(R.id.endGameLayout).setVisibility(View.INVISIBLE);



    }
}
