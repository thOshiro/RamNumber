package com.industries.my.ramnumbers.model;

import com.industries.my.ramnumbers.enums.GameStatusEnum;

import java.util.ArrayList;
import java.util.List;

import static com.industries.my.ramnumbers.constants.GameConstants.GRID_DEFAULT_SIZE;

public class GameStatusKeeper {


    private List<Integer> answerOrder = new ArrayList<>();
    private static final int ANSWER_POSITION = 0;
    private GameStatusEnum currentGameStatusEnum;

    private void setNewGameStatus(int pos){
        try{

            if(currentGameStatusEnum == GameStatusEnum.BEGINNING)
                return;

            if(answerOrder.get(ANSWER_POSITION) == pos){
                answerOrder.remove(ANSWER_POSITION);

                if(answerOrder.isEmpty()) {
                    currentGameStatusEnum = GameStatusEnum.WON;
                    return;
                }

                currentGameStatusEnum = GameStatusEnum.GUESSED_CORRECTLY;
                return;
            }
        } catch(IndexOutOfBoundsException eOut){
            currentGameStatusEnum = GameStatusEnum.NOT_PLAYING;
            return;
        }

        answerOrder.clear();
        currentGameStatusEnum = GameStatusEnum.LOST;
    }

    public GameStatusEnum checkNewGameStatus(int positionPressed){
        setNewGameStatus(positionPressed);
        return currentGameStatusEnum;
    }

   public void generateAnswerSheet(List<String> gridNumberLayout){
        answerOrder = new ArrayList<>();
        int indexOfNumber;

        for(int nextNumberOrder = 1; nextNumberOrder <= GRID_DEFAULT_SIZE; nextNumberOrder++){
            indexOfNumber = gridNumberLayout.indexOf(Integer.toString(nextNumberOrder));

            if(indexOfNumber == -1 )
                break;

            answerOrder.add(indexOfNumber);
        }
   }

   public void setCurrentGameStatusEnum(GameStatusEnum gameStatusEnum){
        this.currentGameStatusEnum = gameStatusEnum;
   }
}
