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

public class SelectPlayer extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayAdapter arrayAdapter;
    PlayerManager playerManager;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        listView = findViewById(R.id.listView);
        playerManager = new PlayerManager();
        databaseHelper = new DatabaseHelper(SelectPlayer.this);
        showPlayersOnListView(databaseHelper);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerModel clickedPlayer = (PlayerModel) parent.getItemAtPosition(position);
                if(MainActivity.playerX == null){
                    playerManager.assignPlayerX(clickedPlayer);
                }
                else{
                    playerManager.assignPlayerO(clickedPlayer);
                }
                Intent mainActivity = new Intent(SelectPlayer.this, MainActivity.class);
                startActivity(mainActivity);
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
                        (SelectPlayer.this, ViewScoreboard.class);
                startActivity(viewScoreboardIntent);
                return true;
            case R.id.selectPlayerX:
            case R.id.selectPlayerO:
                Intent selectPlayerIntent = new Intent
                        (SelectPlayer.this, SelectPlayer.class);
                startActivity(selectPlayerIntent);
                return true;
            case R.id.addPlayer:
                Intent addPlayerIntent = new Intent
                        (SelectPlayer.this, AddPlayer.class);
                startActivity(addPlayerIntent);
                return true;
            case R.id.returnToGame:
                Intent backToMainIntent = new Intent
                        (SelectPlayer.this, MainActivity.class);
                startActivity(backToMainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPlayersOnListView(DatabaseHelper databaseHelper) {
        arrayAdapter = new ArrayAdapter<>(
                SelectPlayer.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryone()
        );
        listView.setAdapter(arrayAdapter);
    }
}
