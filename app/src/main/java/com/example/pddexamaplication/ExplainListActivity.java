package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplainListActivity extends AppCompatActivity {


    Test test;
    int btnCounter = 0;
    int currentNumb = 0;
    int [] partial;
    List<Question> questionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_explainlistquest);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Просмотр ошибок");
        Intent data = getIntent();
        test = (Test) data.getSerializableExtra("test");
        assert partial != null;
        partial = data.getIntArrayExtra("partial");
        assert test != null;
        questionList = test.getTest();
    }

    int idParser(Button btn){
        int num = -1;
        btnCounter++;
        switch (btn.getId()){
            case R.id.buttonQuest1:
                num = 0;
                break;
            case R.id.buttonQuest2:
                num = 1;
                break;
            case R.id.buttonQuest3:
                num = 2;
                break;
            case R.id.buttonQuest4:
                num = 3;
                break;
            case R.id.buttonQuest5:
                num = 4;
                break;
            case R.id.buttonQuest6:
                num = 5;
                break;
            case R.id.buttonQuest7:
                num = 6;
                break;
            case R.id.buttonQuest8:
                num = 7;
                break;
            case R.id.buttonQuest9:
                num = 8;
                break;
            case R.id.buttonQuest10:
                num = 9;
                break;
            case R.id.buttonQuest11:
                num = 10;
                break;
            case R.id.buttonQuest12:
                num = 11;
                break;
            case R.id.buttonQuest13:
                num = 12;
                break;
            case R.id.buttonQuest14:
                num = 13;
                break;
            case R.id.buttonQuest15:
                num = 14;
                break;
            case R.id.buttonQuest16:
                num = 15;
                break;
            case R.id.buttonQuest17:
                num = 16;
                break;
            case R.id.buttonQuest18:
                num = 17;
                break;
            case R.id.buttonQuest19:
                num = 18;
                break;
            case R.id.buttonQuest20:
                num = 19;
                break;
        }
        currentNumb = num;
        return  num;
    }
    public void onClick(View view) {
        Button myButton = (Button)view;
        myButton.setBackgroundColor(Color.rgb(255,255,255));
        int number = idParser(myButton);

        Intent intent = new Intent(ExplainListActivity.this, ExplainedQuestionActivity.class);
        intent.putExtra("question", questionList.get(number));
        intent.putExtra("contImage", "1");
        intent.putExtra("partial",Integer.toString(partial[number]));
        startActivity(intent);
    }
}
