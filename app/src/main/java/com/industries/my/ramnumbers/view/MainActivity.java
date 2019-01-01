package com.industries.my.ramnumbers.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.industries.my.ramnumbers.R;
import com.industries.my.ramnumbers.presenter.NumberGridPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity implements NumberGridView{

    @BindView(R.id.current_level_text_id)
    TextView levelTittle;

    @BindView(R.id.number_grid_id)
    GridView numberGridView;

    @BindView(R.id.title_text_id)
    TextView applicationTitle;

    NumberGridPresenter numberGridPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        numberGridPresenter = new NumberGridPresenter(this);
        numberGridPresenter.onCreate();
    }

    @OnClick(R.id.play_button_id)
    public void playGame(View view){
        numberGridPresenter.startNewGame();
        applicationTitle.setText(getString(R.string.ready_set));
    }

    @Override
    public void setGameGrid(List<String> numberGrid, int numberOfColumns) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.number_grid_item, numberGrid);

        numberGridView.setNumColumns(numberOfColumns);
        numberGridView.setAdapter(adapter);
    }

    @OnItemClick(R.id.number_grid_id)
    public void gridItemClick(int position){
        numberGridPresenter.checkIfWon(position);
    }

    @Override
    public void setWin() {
        applicationTitle.setText(getString(R.string.you_win_congratulations));
    }

    @Override
    public void setLose() {
        applicationTitle.setText(getString(R.string.you_lost));
    }

    @Override
    public void suggestNewGame() {
        applicationTitle.setText(R.string.start_new_game_suggestion);
    }

    @Override
    public void setGoText() {
        applicationTitle.setText(getString(R.string.go));
    }

    @Override
    public void toastWaitForIt() {
        Toast.makeText(MainActivity.this, getString(R.string.wait_for_it), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLevelTitle(int currentLevel) {
        switch (currentLevel){
             case -1:{
                levelTittle.setText(getString(R.string.there_is_no_next_level));
                break;
            }
            case 0:{
                levelTittle.setText(getString(R.string.noobie));
                break;
            }
            case 1:{
                levelTittle.setText(getString(R.string.not_bad));
                break;
            }
            case 2:{
                levelTittle.setText(getString(R.string.moderate));
                break;
            }
            case 3:{
                levelTittle.setText(getString(R.string.impressive));
                break;
            }
            case 4:{
                levelTittle.setText(getString(R.string.beyound));
                break;
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        numberGridPresenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        numberGridPresenter.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
        numberGridPresenter.onStop();
    }
}
