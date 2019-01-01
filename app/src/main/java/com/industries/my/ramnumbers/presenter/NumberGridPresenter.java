package com.industries.my.ramnumbers.presenter;

import android.os.CountDownTimer;

import com.industries.my.ramnumbers.enums.GameStatusEnum;
import com.industries.my.ramnumbers.model.GameLevelKeeper;
import com.industries.my.ramnumbers.model.GameStatusKeeper;
import com.industries.my.ramnumbers.model.NumberGridGenerator;
import com.industries.my.ramnumbers.view.MainActivity;

import java.util.List;

import static com.industries.my.ramnumbers.constants.GameConstants.COUNT_DOWN_INTERVAL;
import static com.industries.my.ramnumbers.constants.GameConstants.GAME_INITIAL_LEVEL;
import static com.industries.my.ramnumbers.constants.GameConstants.GRID_DEFAULT_NUMBERS_TO_GUESS;

public class NumberGridPresenter implements Presenter{

    private MainActivity mainActivity;
    private NumberGridGenerator numberGridGenerator;
    private GameStatusKeeper gameStatusKeeper;
    private List<String> currentGameGrid;
    private CountDownTimer countDownTimer;
    private GameLevelKeeper gameLevelKeeper;

    public NumberGridPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.numberGridGenerator = new NumberGridGenerator();
        this.gameStatusKeeper = new GameStatusKeeper();
        this.currentGameGrid = numberGridGenerator.generateEmptyGrid();
        this.gameLevelKeeper = new GameLevelKeeper(GAME_INITIAL_LEVEL);
    }

    @Override
    public void onCreate(){
        setMainGameGrid(currentGameGrid);
    }

    @Override
    public void onResume() {
        setMainGameGrid(currentGameGrid);
    }

    @Override
    public void onPause() {
       setMainGameGrid(numberGridGenerator.generateHiddenGrid());
    }

    @Override
    public void onStop() {
        setMainGameGrid(numberGridGenerator.generateEmptyGrid());
    }

    public void startNewGame(){
        mainActivity.setLevelTitle(gameLevelKeeper.getGameLevel());

        currentGameGrid = numberGridGenerator.generateNonRepeatableRandomNumberUpTo(GRID_DEFAULT_NUMBERS_TO_GUESS);
        mainActivity.setGameGrid(currentGameGrid, numberGridGenerator.getGridColumns());

        gameStatusKeeper.generateAnswerSheet(currentGameGrid);
        gameStatusKeeper.setCurrentGameStatusEnum(GameStatusEnum.BEGINNING);

        countDown(GRID_DEFAULT_NUMBERS_TO_GUESS*gameLevelKeeper.getTimeToMemorise());
    }

    public void checkIfWon(int position){
       GameStatusEnum gameStatusEnum = gameStatusKeeper.checkNewGameStatus(position);

       switch (gameStatusEnum){
           case WON: {
               mainActivity.setWin();
               setMainGameGrid(currentGameGrid);
               mainActivity.setLevelTitle(gameLevelKeeper.goToNextLevel());
               break;
           }
           case LOST:{
               mainActivity.setLose();
               setMainGameGrid(currentGameGrid);
               break;
           }
           case BEGINNING:{
               mainActivity.toastWaitForIt();
               break;
           }
           case NOT_PLAYING:{
               mainActivity.suggestNewGame();
               break;
           }
       }
    }

    private void countDown(long countDownTime){
        if(countDownTimer != null)
            countDownTimer.cancel();

        countDownTimer = new CountDownTimer(countDownTime, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                currentGameGrid = numberGridGenerator.generateHiddenGrid();
                setMainGameGrid(currentGameGrid);
                mainActivity.setGoText();
                gameStatusKeeper.setCurrentGameStatusEnum(GameStatusEnum.PLAYING);
            }
        }.start();
    }

    private void setMainGameGrid(List<String> gameGridToSet){
        mainActivity.setGameGrid(gameGridToSet, numberGridGenerator.getGridColumns());
    }
}
