package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    //Entries will be 0 for player 1 and 1 for player 2
    int[] boxEntries = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int player =0; // player 0 is player 1 and 1 is player 2
    int count =0; // keeps track of how many boxes are filled

    int[][] winningCombinations = { {0,1,2}, {3,4,5}, {6,7,8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    GridLayout myGrid = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBoxClicked(View view) {

        ImageView imageView = (ImageView) view;
        int tag =  Integer.parseInt(view.getTag().toString());

        System.out.println("The tag is  " + tag + "--" +boxEntries[tag] );
        //if the box has not been checked already
        if (boxEntries[tag] == 2) {
            if (player == 0) {
                boxEntries[tag] = player;
                imageView.setImageResource(R.drawable.red);
                player =1;
            } else {
                boxEntries[tag] = player;
                imageView.setImageResource(R.drawable.yellow);
                player =0;
            }
            count ++;

            //check if someone won.
            for (int[] combination : winningCombinations) {
                    if(boxEntries[combination[0]] == boxEntries[combination[1]] && boxEntries[combination[1]] == boxEntries[combination[2]] && boxEntries[combination[0]] != 2) {
                        String message = "Player " + (boxEntries[combination[0]]+1) + " won!!!";
                        endGame(message);
                        return;
                    }
            }

            if (count == 9) {
                endGame("Game Drawn!!!");
            }
        }
    }

    public void endGame (String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        TextView endGametextView = findViewById(R.id.endGameText);
        LinearLayout endGameLayout = findViewById(R.id.endGameLayout);
        endGameLayout.setVisibility(View.VISIBLE);
        endGametextView.setText(message);
    }


    public void resetGame(View view) {

        Arrays.fill(boxEntries, 2);
        player=0;
        count=0;
        LinearLayout endGameLayout = (LinearLayout) findViewById(R.id.endGameLayout);
        endGameLayout.setVisibility(View.INVISIBLE);
        System.out.println("2222");


        GridLayout myGrid = (GridLayout) findViewById(R.id.love);
        System.out.println("1111");
        for (int i=0; i< myGrid.getChildCount(); i++) {
            ImageView imageView = (ImageView) myGrid.getChildAt(i);
            imageView.setImageResource(0);//settiing nothing.
        }


    }
}
