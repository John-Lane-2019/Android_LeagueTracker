package com.prog3210leaguetrackingapp.www;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayer extends AppCompatActivity {

    Button addPlayer;
    EditText enterName;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        addPlayer = findViewById(R.id.buttonAddPlayer);
        enterName = findViewById(R.id.editTextPlayerName);

        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerModel playerModel;
                try {
                    playerModel = new PlayerModel(-1,enterName.getText().toString(), 0,0,0);
                }
                catch(Exception e){

                    String error = e.getMessage();
                    Toast.makeText(AddPlayer.this, error, Toast.LENGTH_SHORT ).show();
                    playerModel = new PlayerModel( -1,"error", 0, 0,0);


                }

                DatabaseHelper databaseHelper = new DatabaseHelper(AddPlayer.this); //create instance of DB class
                boolean success = databaseHelper.addOne(playerModel); //pass player to addOne method in DB class

                if (success){

                    //showCustomersOnListView(databaseHelper);

                    Toast.makeText(AddPlayer.this, enterName.getText().toString()
                            + " added to player list", Toast.LENGTH_LONG).show();

                    enterName.setText("");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        MenuItem menuItem = menu.findItem(R.id.newGame);
        if (menuItem != null){
            menuItem.setVisible(false);
        }
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewScoreboard:
                Intent viewScoreboardIntent = new Intent
                        (AddPlayer.this, ViewScoreboard.class);
                startActivity(viewScoreboardIntent);
                return true;
            case R.id.selectPlayerX:
            case R.id.selectPlayerO:
                Intent selectPlayerIntent = new Intent
                        (AddPlayer.this, SelectPlayer.class);
                startActivity(selectPlayerIntent);
                return true;
            case R.id.addPlayer:
                Intent addPlayerIntent = new Intent
                        (AddPlayer.this, AddPlayer.class);
                startActivity(addPlayerIntent);
                return true;
            case R.id.returnToGame:
                Intent backToMainIntent = new Intent
                        (AddPlayer.this, MainActivity.class);
                startActivity(backToMainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
