package com.example.pddexamaplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplainListActivity extends AppCompatActivity {

    Test test;
    Test dopTest;
    int btnCounter = 0;
    int currentNumb = 0;
    int[] partial;
    List<Question> questionList = new ArrayList<>();
    List<Question> dopList = new ArrayList<>();
    List<View> dopBtnArr = new ArrayList<>();
    int dopQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_explainlistquest);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Просмотр ошибок");
        Intent data = getIntent();


        test = (Test) data.getSerializableExtra("test");
        assert partial != null;
        assert test != null;
        partial = test.getPartialAnswrs();
        check();
        Bundle bndl = data.getExtras();
        assert bndl != null;
        final int wasDop = bndl.getInt("wasDop");
        dopQuantity = bndl.getInt("dopQuantity") - 20;
        assert test != null;
        questionList = test.getTest();
        String res = "ACTIVITY EXPLAINED: partials -> ";
        for(int i = 0; i < 20; i ++){
            res += test.getPartialAnswrs()[i]+ " ";
        }
        Log.d("watch",res);

        dopBtnArr.add(findViewById(R.id.button2));
        dopBtnArr.add(findViewById(R.id.button3));
        dopBtnArr.add(findViewById(R.id.button4));
        dopBtnArr.add(findViewById(R.id.button5));
        dopBtnArr.add(findViewById(R.id.button6));
        dopBtnArr.add(findViewById(R.id.button7));
        dopBtnArr.add(findViewById(R.id.button8));
        dopBtnArr.add(findViewById(R.id.button9));
        dopBtnArr.add(findViewById(R.id.button10));
        dopBtnArr.add(findViewById(R.id.button11));
        for (View v : dopBtnArr) {
            v.setVisibility(View.INVISIBLE);
            v.setEnabled(false);
        }
        if (wasDop == 0) {
            //  dopTest = (Test) data.getSerializableExtra("dopTest");

            assert dopTest != null;
            dopList = test.getDoptest();

            for (int i = 0; i < dopQuantity; i++) {
                dopBtnArr.get(i).setEnabled(true);
                Button btn = (Button) dopBtnArr.get(i);
                btn.setEnabled(true);
                btn.setVisibility(View.VISIBLE);
                final int finalI = i;
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.setBackgroundColor(Color.rgb(255, 255, 255));
                        Intent intent = new Intent(ExplainListActivity.this, ExplainedQuestionActivity.class);
                        intent.putExtra("question", dopList.get(finalI));
                        Question quest = dopList.get(finalI);
                        intent.putExtra("contImage", "1");
                        intent.putExtra("partial", test.getDopPartialAnswrs()[finalI]);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    int idParser(Button btn) {
        int num = -1;
        btnCounter++;
        switch (btn.getId()) {
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
        return num;
    }

    private void check() {
        String res = "";
        for (int i = 0; i < 20; i++) {
            res += test.getPartialAnswrs()[i] + " ";
        }
    }

    public void onClick(View view) {
        Button myButton = (Button) view;
        myButton.setBackgroundColor(Color.rgb(255, 255, 255));
        int number = idParser(myButton);

        Intent intent = new Intent(ExplainListActivity.this, ExplainedQuestionActivity.class);
        intent.putExtra("question", questionList.get(number));
        intent.putExtra("contImage", "1");

        intent.putExtra("partial", partial[number]);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Предупреждение")
                .setMessage("Вы действительно хотите закончить просмотр ошибок?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(ExplainListActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }
}
