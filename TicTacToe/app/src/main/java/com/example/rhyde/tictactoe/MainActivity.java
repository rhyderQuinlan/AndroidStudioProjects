package com.example.rhyde.tictactoe;

//An Android Application designed in Android Studio
//Author: R.Quinlan     Date:12/12/2017
//Filename: MainActivity.java       Language: Java

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    public void beginGame (View view) {
        TextView beginButton = (TextView) findViewById(R.id.startButton);
        beginButton.animate().alpha(0f).setDuration(2000);

        setContentView(R.layout.gameboardlayout);
    }


    int activePlayer = 1;
    int [] gameArray = {0,0,0,0,0,0,0,0,0};
    int [][] winArray = new int [3][3];
    int numberMoves = 0;


    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public void checkWin(int activePlayer, View linearLayoutO, View linearLayoutX) {
        if (activePlayer == 2) {
            linearLayoutX.bringToFront();
            linearLayoutX.animate().alpha(1).setDuration(1000);
        } else {
            linearLayoutO.bringToFront();
            linearLayoutO.animate().alpha(1).setDuration(1000);
        }
    }

    public void fadeIn (View view) {

        final View linearLayoutO = findViewById(R.id.winningMessageO);
        final View linearLayoutX = findViewById(R.id.winningMessageX);
        final View player1text = findViewById(R.id.player1text);
        final View player2text = findViewById(R.id.player2text);

            numberMoves++;
        ImageView counter = (ImageView) view;

        //Selection of the Counter tapped
            int counterTag = Integer.parseInt(counter.getTag().toString());
        //ActivePlayer 1 = yellow
        //ActivePlayer 2 = red
            if (gameArray[counterTag] != 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Taken!",Toast.LENGTH_SHORT);
            toast.show();
            } else {
            if (activePlayer == 1) {
                player1text.animate().alpha(0).setDuration(500);
                player2text.bringToFront();
                player2text.animate().alpha(1).setDuration(500);
                counter.setImageResource(R.drawable.cross);
                counter.animate().alpha(1f).setDuration(600);
                gameArray[counterTag] = 1;
                activePlayer = 2;
            } else if (activePlayer == 2){
                counter.setImageResource(R.drawable.nought);
                counter.animate().alpha(1f).setDuration(600);
                gameArray[counterTag] = 2;
                activePlayer = 1;
                player2text.animate().alpha(0).setDuration(500);
                player1text.bringToFront();
                player1text.animate().alpha(1).setDuration(500);
            }
            //new winMethod(gameArray);
            // Creating winning conditions

           //Printing Array to console. Proud of this!
           int k = 0;
           for (int i =0; i<=2; i++) {
                for (int j = 0; j<=2; j++) {
                    winArray[i][j] = gameArray[k];
                    k++;
                }
            }

            for(int[] row : winArray) {
                printRow(row);
            }

            System.out.println("Game Array:" + Arrays.toString(gameArray));
           int i = 0;
           int j = 0;

            if (numberMoves >= 5) {
                while (i <= 2) {
                    //horizontal line win
                    if (winArray[i][j] == winArray[i][j + 1]
                            && winArray[i][j] == winArray[i][j + 2]
                            && winArray[i][j] != 0
                            && winArray[i][j + 1] != 0
                            && winArray[i][j + 2] != 0) {
                        checkWin (activePlayer, linearLayoutO, linearLayoutX);
                    }
                    //vertical line win
                    if (winArray[j][i] == winArray[j + 1][i]
                            && winArray[j][i] == winArray[j + 2][i]
                            && winArray[j][i] != 0
                            && winArray[j + 1][i] != 0
                            && winArray[j + 2][i] != 0) {
                        //frameLayout.animate().alpha(1).setDuration(1000);
                        checkWin (activePlayer, linearLayoutO, linearLayoutX);
                    }
                    //diaganol line right win
                    if (winArray[0][0] == winArray[1][1]
                            && winArray[0][0] == winArray[2][2]) {
                        i = 3;
                        //frameLayout.animate().alpha(1).setDuration(1000);
                        checkWin (activePlayer, linearLayoutO,linearLayoutX);
                    }
                    //diaganol line left win
                    if (winArray[2][0] == winArray[1][1]
                            && winArray[2][0] == winArray[0][2]) {
                        i = 3;
                        //frameLayout.animate().alpha(1).setDuration(1000);
                        checkWin (activePlayer, linearLayoutO, linearLayoutX);
                    }
                    i++;
                }

            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}

/*class winMethod {
    public winMethod (int [] gameArray){


    }

} */
