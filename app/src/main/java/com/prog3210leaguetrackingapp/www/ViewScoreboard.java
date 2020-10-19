package com.prog3210leaguetrackingapp.www;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewScoreboard extends AppCompatActivity {

    ListView listViewPlayers;
    DatabaseHelper databaseHelper;
    ArrayAdapter arrayAdapter;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scoreboard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listViewPlayers = findViewById(R.id.listViewPlayers);
        databaseHelper = new DatabaseHelper(ViewScoreboard.this);
        showPlayersOnListView(databaseHelper);

        listViewPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerModel clickedPlayer = (PlayerModel) parent.getItemAtPosition(position);
                databaseHelper.deletePlayer(clickedPlayer);
                showPlayersOnListView(databaseHelper);
                Toast.makeText(ViewScoreboard.this, "Player Deleted",
                        Toast.LENGTH_LONG).show();

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
                        (ViewScoreboard.this, ViewScoreboard.class);
                startActivity(viewScoreboardIntent);
                return true;
            case R.id.selectPlayerX:
            case R.id.selectPlayerO:
                Intent selectPlayerIntent = new Intent
                        (ViewScoreboard.this, SelectPlayer.class);
                startActivity(selectPlayerIntent);
                return true;
            case R.id.addPlayer:
                Intent addPlayerIntent = new Intent
                        (ViewScoreboard.this, AddPlayer.class);
                startActivity(addPlayerIntent);
                return true;
            case R.id.returnToGame:
                Intent backToMainIntent = new Intent
                        (ViewScoreboard.this, MainActivity.class);
                startActivity(backToMainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showPlayersOnListView(DatabaseHelper databaseHelper) {
         arrayAdapter = new ArrayAdapter<>(
                 ViewScoreboard.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryone()
         );
        listViewPlayers.setAdapter(arrayAdapter);
    }
}
