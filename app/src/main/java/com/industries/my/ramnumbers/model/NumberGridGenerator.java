package com.industries.my.ramnumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.industries.my.ramnumbers.constants.GameConstants.EMPTY_GRID_SPACE;
import static com.industries.my.ramnumbers.constants.GameConstants.GRID_DEFAULT_SIZE;
import static com.industries.my.ramnumbers.constants.GameConstants.HIDDEN_GRID_SPACE;

public class NumberGridGenerator {

    private int gridSize;

    public NumberGridGenerator(){
        this.gridSize = GRID_DEFAULT_SIZE;
    }

   public List<String> generateEmptyGrid() {
        List<String> grid = new ArrayList<>();

        for(int i = 0; i < gridSize; i++){
                grid.add(EMPTY_GRID_SPACE);
        }

        return grid;
   }

   public int getGridColumns(){
        Double columns = Math.sqrt(gridSize);
        return columns.intValue();
   }

   public List<String> generateNonRepeatableRandomNumberUpTo(int upToNumber){
        List<String> randomNumberLayout = new ArrayList<>();

        for(int i=1, j=0; j<GRID_DEFAULT_SIZE; j++){
            if (i <= upToNumber){
                randomNumberLayout.add(Integer.toString(i));
                i++;
            } else{
                randomNumberLayout.add("-");
            }
        }

        Collections.shuffle(randomNumberLayout);

        return randomNumberLayout;
   }

   public List<String> generateHiddenGrid() {
        List<String> grid = new ArrayList<>();

        for(int i = 0; i < gridSize; i++){
                grid.add(HIDDEN_GRID_SPACE);
        }

        return grid;
   }
}
