package com.prog3210leaguetrackingapp.www;
// this class acts as a go between for the SelectPlayer activity and MainActivity
public class PlayerManager {

    private PlayerModel playerX;
    private   PlayerModel playerO;

    public void assignPlayerX(PlayerModel clickedPlayer){
        playerX = clickedPlayer;
        MainActivity.playerX = playerX;
    }

    public void assignPlayerO(PlayerModel clickedPlayer) {
        playerO = clickedPlayer;
        MainActivity.playerO = playerO;
    }
}
