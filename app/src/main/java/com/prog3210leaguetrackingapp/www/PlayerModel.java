package com.prog3210leaguetrackingapp.www;

import androidx.annotation.NonNull;

public class PlayerModel {

    private int id;
    private String name;
    private int wins;
    private int losses;
    private int ties;

  /* public PlayerModel( int playerID, String name, int wins, int losses, int ties){
        this.id = playerID;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }*/

    //default constructor
    public PlayerModel(){}

    public PlayerModel( int playerId, String playerName, int playerWins, int playerLosses, int playerTies) {
        this.id = playerId;
        this.name = playerName;
        this.wins = playerWins;
        this.losses = playerLosses;
        this.ties = playerTies;
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("     ");
        sb.append(id);
        sb.append(" ");
        sb.append(name );
        sb.append("  ");
        sb.append("Wins:    ");
        sb.append(wins);
        sb.append("     ");
        sb.append("Losses:  ");
        sb.append(losses);
        sb.append("     ");
        sb.append("Ties:     ");
        sb.append(ties);
        return sb.toString();
    }

    public int getId() {

       return id;
    }

    //getters and setters
    public void setId(int id) {

       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

       this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
}
