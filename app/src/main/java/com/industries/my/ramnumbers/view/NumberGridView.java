package com.industries.my.ramnumbers.view;

import java.util.List;

public interface NumberGridView {

    void setGameGrid(List<String> numberGrid, int numberOfColumns);
    void setWin();
    void setLose();
    void suggestNewGame();
    void setGoText();
    void toastWaitForIt();
    void setLevelTitle(int currentLevel);
}
