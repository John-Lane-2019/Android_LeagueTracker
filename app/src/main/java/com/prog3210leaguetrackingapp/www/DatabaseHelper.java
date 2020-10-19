package com.prog3210leaguetrackingapp.www;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
//sets up the SQLite database
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String PLAYER_TABLE = "PLAYER_TABLE";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_WINS = "PLAYER_WINS";
    public static final String COLUMN_PLAYER_LOSSES = "PLAYER_LOSSES";
    public static final String COLUMN_PLAYER_TIES = "PLAYER_TIES";
    public static final String COLUMN_ID = "ID";
    public PlayerModel passedPlayerModel;


    public DatabaseHelper(@Nullable Context context) {
        super(context, "player.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //db gets created here
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + PLAYER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + COLUMN_PLAYER_NAME + " TEXT, " + COLUMN_PLAYER_WINS + " INTEGER, " + COLUMN_PLAYER_LOSSES + " INTEGER, " + COLUMN_PLAYER_TIES + "  INTEGER)"; // needed two white spaces, not one. Geez
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
        onCreate(db);
    }
    // add a player to the database
    public boolean addOne(PlayerModel playerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PLAYER_NAME, playerModel.getName());
        cv.put(COLUMN_PLAYER_WINS,playerModel.getWins());
        cv.put(COLUMN_PLAYER_LOSSES,playerModel.getLosses());
        cv.put(COLUMN_PLAYER_TIES, playerModel.getTies());


        long insert = db.insert(PLAYER_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }

    }
    //pulls all the players off the database so they can be output to the GUI
    public List<PlayerModel> getEveryone(){
        List<PlayerModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_WINS + " DESC ";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null); //cursor in SQLite is result set

        if (cursor.moveToFirst()){
            //if true loop through results (cursor) and put into return list
            do{
                int playerId = cursor.getInt(0);
                String playerName = cursor.getString(1);
                int playerWins = cursor.getInt(2);
                int playerLoses = cursor.getInt(3);
                int playerTies = cursor.getInt(4);

                PlayerModel playerModel;
                playerModel = new PlayerModel(playerId,playerName,playerWins,playerLoses,playerTies);
                returnList.add(playerModel);
            }
            while(cursor.moveToNext());
        }
        else{
            //do nothing
        }
        //close cursor and db when done
        cursor.close();
        db.close();
        return returnList;
    }
    // updates the wins for each player
    public void updateWins(PlayerModel playerModel){

        int playerID = playerModel.getId();

        String query = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_WINS + "=" + COLUMN_PLAYER_WINS + " +1" + " WHERE " + COLUMN_ID + "=" + playerID;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
    // updates the losses for each player
    public void updateLosses(PlayerModel playerModel) {
        int playerID = playerModel.getId();

        String query = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_LOSSES + "=" + COLUMN_PLAYER_LOSSES + " +1" + " WHERE " + COLUMN_ID + "=" + playerID;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
    // updates ties for each player
    public void updateTies(PlayerModel playerModel){

        int playerID = playerModel.getId();

        String query = "UPDATE " + PLAYER_TABLE + " SET " + COLUMN_PLAYER_TIES + "=" + COLUMN_PLAYER_TIES + " +1" + " WHERE " + COLUMN_ID + "=" + playerID;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

    }
    // deletes players from the database
    public boolean deletePlayer(PlayerModel playerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + PLAYER_TABLE + " WHERE " + COLUMN_ID + " = " + playerModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){ //you are looking at the wrong sample

            return true;
        }
        else{
            return false;
        }
    }
}
