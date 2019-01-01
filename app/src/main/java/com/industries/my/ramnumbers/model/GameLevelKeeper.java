package com.industries.my.ramnumbers.model;

public class GameLevelKeeper {

    private final static long MINIMUM_POSSIBLE_TIME_TO_MEMORISE = 500;
    private int gameLevel;
    private long timeToMemorise = 2500;

    public GameLevelKeeper(int startLevel){
        this.gameLevel = startLevel;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public int goToNextLevel(){
        if(timeToMemorise <= MINIMUM_POSSIBLE_TIME_TO_MEMORISE)
            return -1;

        calculateTimeToMemorise();
        this.gameLevel++;
        return gameLevel;
    }

    private void calculateTimeToMemorise(){
        timeToMemorise -= 500;
    }

    public long getTimeToMemorise(){
        return this.timeToMemorise;
    }

}
