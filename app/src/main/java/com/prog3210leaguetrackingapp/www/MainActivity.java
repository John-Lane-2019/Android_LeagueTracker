/*
* PREPARED BY JOHN LANE FOR PROG3210
* MARCH 31 2020
* TIC TAC TOE GAME FOR ANDROID WITH CRUD FUNCTIONS*/

package com.prog3210leaguetrackingapp.www;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
implements OnClickListener{

    public static PlayerModel playerX;
    public static PlayerModel playerO;
    private TextView playerOneName;
    private TextView playerTwoName;
    private TextView currentPlayer;
    private TextView gameOverMessage;
    private String buttonSymbol = "X";
    private Button button;
    private Button buttonArray []= new Button[9];
    private Button newGame;
    private Integer numberOfClicks = 0;
    private Integer gameOverColor = Color.parseColor("#000080");
    private Integer buttonBackgroundColor = Color.parseColor(("#b3b3ff"));
    private View storedButton;
    private  TextView tv_Player1Name;
    private TextView tv_Player2Name;
    DatabaseHelper databaseHelper;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        assignButtonsToArray();

        //cast controls
        gameOverMessage= findViewById(R.id.textViewGameOverMessage);
        playerOneName = findViewById(R.id.tv_Player1Name);
        playerTwoName = findViewById(R.id.tv_Player2Name);
        currentPlayer = findViewById(R.id.textViewCurrentPlayer);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        CheckPlayerXSlots();
        CheckPlayerOSlots();

    }
    //checks if playerX is null or not
    private void CheckPlayerXSlots() {
        turnTheButtonsOff();
         if (playerX == null){

           Toast.makeText(MainActivity.this, "Select Player X", Toast.LENGTH_LONG).show();
        }
         else if(playerX !=null){

             updatePlayerXSlots(playerX);
         }
    }
    //checks if playerO is null or not
    private void CheckPlayerOSlots(){

        if (playerO == null){

            Toast.makeText(MainActivity.this, "Select Player O", Toast.LENGTH_SHORT).show();
        }

        else if(playerO != null) {

            updatePlayerOSlots(playerO);
            turnTheButtonsOn();

        }
    }
    //deactivates buttons so user can't click them before both players selected
    private void turnTheButtonsOff() {
        for (int i = 0; i <buttonArray.length ; i++) {
            buttonArray[i].setEnabled(false);
        }
    }
    //reactivates buttons after both players selected
    private void turnTheButtonsOn() {
        for (int i = 0; i <buttonArray.length ; i++) {
            buttonArray[i].setEnabled(true);
        }
    }
    //updates GUI with playerX info
    public void updatePlayerXSlots(PlayerModel playerX){
        tv_Player1Name = findViewById(R.id.tv_Player1Name);
        tv_Player1Name.setText(playerX.getName());
        currentPlayer.setText(playerX.getName());
    }
    //updates GUI with playerO info
    public void updatePlayerOSlots(PlayerModel playerO){
        tv_Player2Name = findViewById(R.id.tv_Player2Name);
        tv_Player2Name.setText(playerO.getName());
    }

    //assign a button to the button array
    private void assignButtonsToArray() {
        buttonArray[0] = findViewById(R.id.button1);
        buttonArray[1] = findViewById(R.id.button2);
        buttonArray[2] = findViewById(R.id.button3);
        buttonArray[3] = findViewById(R.id.button4);
        buttonArray[4] = findViewById(R.id.button5);
        buttonArray[5] = findViewById(R.id.button6);
        buttonArray[6] = findViewById(R.id.button7);
        buttonArray[7] = findViewById(R.id.button8);
        buttonArray[8] = findViewById(R.id.button9);

        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i].setOnClickListener(this);
        }
    }
    //set an event handler that grabs properties of whichever button is clicked
    @Override
    public void onClick(View view) {

        numberOfClicks++;

        button = (Button)view;


            if (buttonSymbol == "X" && button.getTag() != "O"){
                button.setText("X");
                button.setTag(playerX.getName());
                buttonSymbol = "O";
                currentPlayer.setText(playerO.getName());
            }

            else if (buttonSymbol == "O" && button.getTag() != "X"){

                button.setText("O");
                button.setTag(playerO.getName());
                buttonSymbol = "X";
                currentPlayer.setText(playerX.getName());
            }

            CheckGameOver(button);
    }

    private void CheckGameOver(Button button){
        //check for horizontal victory
        if(buttonArray[0].getTag() == button.getTag() &&
                buttonArray[1].getTag() == button.getTag() &&
                buttonArray[2].getTag() == button.getTag()){
                gameOverMessage.setVisibility(View.VISIBLE);
                gameOverMessage.setText(button.getTag().toString() + " has won!");

                if(button.getTag() == playerX.getName()){
                    databaseHelper.updateWins(playerX);
                    databaseHelper.updateLosses(playerO);
                }
                else if (button.getTag() == playerO.getName()){
                    databaseHelper.updateWins(playerO);
                    databaseHelper.updateLosses(playerX);
                }

            disableButtonArray();
            enableNewGameButton();

        }
        else if(buttonArray[3].getTag() == button.getTag() &&
                buttonArray[4].getTag() == button.getTag() &&
                buttonArray[5].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        else if (buttonArray[6].getTag() == button.getTag() &&
                 buttonArray[7].getTag() == button.getTag() &&
                 buttonArray[8].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        //check for vertical victory
        else if (buttonArray[0].getTag() == button.getTag() &&
                 buttonArray[3].getTag() == button.getTag() &&
                 buttonArray[6].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();

        }
        else if (buttonArray[1].getTag() == button.getTag() &&
                 buttonArray[4].getTag() == button.getTag() &&
                 buttonArray[7].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        else if (buttonArray[2].getTag() == button.getTag() &&
                 buttonArray[5].getTag() == button.getTag() &&
                 buttonArray[8].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        //check for diagonal victory
        else if (buttonArray[0].getTag() == button.getTag() &&
                 buttonArray[4].getTag() == button.getTag() &&
                 buttonArray[8].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        else if (buttonArray[2].getTag() == button.getTag() &&
                 buttonArray[4].getTag() == button.getTag() &&
                 buttonArray[6].getTag() == button.getTag()){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText(button.getTag().toString() + " has won!");

            if(button.getTag() == playerX.getName()){
                databaseHelper.updateWins(playerX);
                databaseHelper.updateLosses(playerO);
            }
            else if (button.getTag() == playerO.getName()){
                databaseHelper.updateWins(playerO);
                databaseHelper.updateLosses(playerX);
            }

            disableButtonArray();
            enableNewGameButton();
        }
        else if (numberOfClicks == 9){
            gameOverMessage.setVisibility(View.VISIBLE);
            gameOverMessage.setText("Tie Game!");

            databaseHelper.updateTies(playerX);
            databaseHelper.updateTies(playerO);
            disableButtonArray();
            enableNewGameButton();
        }
    }

    private void disableButtonArray() {
        for (int j= 0; j< buttonArray.length; j++){

            buttonArray[j].setEnabled(false);
            buttonArray[j].setBackgroundColor(gameOverColor);
        }
    }

    public void enableNewGameButton(){
        newGame = findViewById(R.id.buttonNewGame);
        newGame.setEnabled(true);
        newGame.setVisibility(View.VISIBLE);

    }

    public void startNewGame(View view){

        for (int j = 0; j< buttonArray.length; j++){

            buttonArray[j].setEnabled(true);
            buttonArray[j].setText(null);
            buttonArray[j].setTag(null);
            buttonArray[j].setBackgroundColor(buttonBackgroundColor);

        }
        buttonSymbol = "X";
        numberOfClicks = 0;
        currentPlayer.setText("It's " + playerX.getName() + " turn");
        gameOverMessage.setVisibility(View.INVISIBLE);
        newGame = findViewById(R.id.buttonNewGame);
        newGame.setEnabled(false);
        newGame.setVisibility(View.INVISIBLE);
        storedButton = view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options,menu);
        MenuItem menuItem = menu.findItem(R.id.returnToGame);
        if (menuItem !=null){
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.viewScoreboard:
                Intent viewScoreboardIntent = new Intent
                        (MainActivity.this, ViewScoreboard.class);
                startActivity(viewScoreboardIntent);
                return true;
            case R.id.selectPlayerX:
            case R.id.selectPlayerO:
                Intent selectPlayerIntent = new Intent
                         (MainActivity.this, SelectPlayer.class);
                 startActivity(selectPlayerIntent);
                return true;
            case R.id.addPlayer:
                Intent addPlayerIntent = new Intent(MainActivity.this,AddPlayer.class);
                startActivity(addPlayerIntent);
                return true;
            case R.id.newGame:
                startNewGame(storedButton);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
