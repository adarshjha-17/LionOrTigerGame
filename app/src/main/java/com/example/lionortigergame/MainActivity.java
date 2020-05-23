package com.example.lionortigergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{

        one, two, unplayed
    }

    Player currentPlayer = Player.one;
    Player[] playerChoice = new Player[9];
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetGame();
            }
        });

        for(int i=0;i<playerChoice.length;i++){

            playerChoice[i] = Player.unplayed;
        }
    }

    public void imageTapped(View view){

        ImageView imageView = (ImageView) view;
        int clickedTag = Integer.parseInt(imageView.getTag().toString());

        if (playerChoice[clickedTag] == Player.unplayed && gameOver == false) {


            imageView.setTranslationX(-2000f);


            playerChoice[clickedTag] = currentPlayer;
            if (currentPlayer == Player.one) {

                currentPlayer = Player.two;
                imageView.setImageResource(R.drawable.tiger);
            } else if (currentPlayer == Player.two) {

                currentPlayer = Player.one;
                imageView.setImageResource(R.drawable.lion);
            }

            imageView.animate().translationXBy(2000f).alpha(1f).rotation(3600f).setDuration(500);

            for (int winningPosition[] : winningPositions) {


                if (playerChoice[winningPosition[0]] == playerChoice[winningPosition[1]] &&
                        playerChoice[winningPosition[1]] == playerChoice[winningPosition[2]] && playerChoice[winningPosition[0]] != Player.unplayed) {

                    String winnerOfGame = "";
                    gameOver = true;
                    btnReset.setVisibility(View.VISIBLE);

                    if (currentPlayer == Player.one){

                        winnerOfGame = "Lion";
                    }
                    else if(currentPlayer == Player.two){

                        winnerOfGame = "Tiger";
                    }

                    Toast.makeText(this, winnerOfGame + "is the winner of game", Toast.LENGTH_SHORT).show();

                }
                else{

                    boolean gameState = true;

                    for(Player counterState : playerChoice){

                        if(counterState == Player.unplayed){

                            gameState = false;
                        }

                    }
                    if(gameState){

                        Toast.makeText(this,  "It's a draw", Toast.LENGTH_SHORT).show();
                        btnReset.setVisibility(View.VISIBLE);

                    }
                }

            }
        }
    }

    private void resetGame(){

        btnReset.setVisibility(View.GONE);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.animate().alpha(0.4f);
            imageView.setImageResource(0);
        }

        for(int i=0;i<playerChoice.length;i++){

            playerChoice[i] = Player.unplayed;
        }

        currentPlayer = Player.one;
        gameOver = false;
    }
}
