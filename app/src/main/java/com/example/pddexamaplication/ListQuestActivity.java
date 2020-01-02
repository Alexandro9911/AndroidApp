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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListQuestActivity extends AppCompatActivity {

    TestCreator creator = new TestCreator();
    Test test = creator.testCreator();
    List<Question> questions = test.getTest();
    
    int[] partialAnswers = new int[20];
    int currentNumb = 0;
    int btnCounter = 0;
    int errorCounter = 0;

    Test dopTest = new Test();
    int dopQuantity = 0;
    int[] groupForDop = {0, 0};
    int dopCounter = 0;
    int currDop = 0;
    int[] dopPartialAnswrs = new int[10];
    List<Question> dopQuestions = new ArrayList<>();

    List<Integer> answers = new ArrayList<>();

    List<View> dopBtnArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listquest);
        for (int i = 0; i < 10; i++) {
            dopPartialAnswrs[i] = -1;
        }
        for (int i = 0; i < 20; i++) {
            partialAnswers[i] = -1;
        }
        test.fillInUnused();
        test.setPartialAnswrs(partialAnswers);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Список вопросов");
        for (Question question : questions) {
            answers.add(-1);
            fillQuestion(question);
        }
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
        int coun = 0;
        for (View v : dopBtnArr) {
            dopBtnArr.get(coun).setEnabled(false);
            Button btn = (Button) dopBtnArr.get(coun);
            btn.setEnabled(false);
            btn.setVisibility(View.INVISIBLE);
            coun++;
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

    public void onClick(View view) {
        Button myButton = (Button) view;
        int number = idParser(myButton);
        Intent intent = new Intent(ListQuestActivity.this, QuestionActivity.class);
        intent.putExtra("class", questions.get(number));
        intent.putExtra("isDop", 0);
        myButton.setEnabled(false);
        myButton.setBackgroundColor(Color.rgb(255, 255, 255));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bndl = data.getExtras();
        assert bndl != null;
        Question question = (Question) bndl.getSerializable("quest");
        assert question != null;
//        partialAnswers[currentNumb] = question.getPartialAnsw();
//        test.setpartialAnswr(currentNumb,question.getPartialAnsw());
//        test.setPartialAnswrs(partialAnswers);
        int answ = bndl.getInt("answ");
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                dopPartialAnswrs[currDop -1] = question.getPartialAnsw();
                test.setDopPartialAnswr(currDop,question.getPartialAnsw());
                test.setDopPartialAnswrs(dopPartialAnswrs);

                String res = "ACTIVITY LISTQUEST: cuur partial = ";
                for(int i = 0; i < 10; i++){
                    res += test.getDopPartialAnswrs()[i] + " ";
                }
                Log.d("watch", res);
                if (answ == 0) {
                    test.setDopPartialAnswrs(dopPartialAnswrs);
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("numb", 0);
                    intent.putExtra("text", "Экзамен не сдан!");
                    intent.putExtra("dopQuantity", dopQuantity);
                    intent.putExtra("wasDop", 0);
                    intent.putExtra("test", test);
                    startActivity(intent);
                    finish();
                }

                if (btnCounter == dopQuantity) {
                    test.setDopPartialAnswrs(dopPartialAnswrs);
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("text", "Экзамен сдан!");
                    intent.putExtra("numb", 2);
                    intent.putExtra("wasDop", 0);
                    intent.putExtra("dopQuantity", dopQuantity);
                    intent.putExtra("test", test);
                    startActivity(intent);
                    finish();
                }
            }
        }
        if (requestCode == 1) {
            partialAnswers[currentNumb] = question.getPartialAnsw();
            test.setpartialAnswr(currentNumb,question.getPartialAnsw());
            test.setPartialAnswrs(partialAnswers);
            if (resultCode == RESULT_OK) {
                if (answ == 0) {
                    errorCounter++;
                    if (dopCounter < 2) {
                        groupForDop[dopCounter] = questions.get(currentNumb).getGroup();
                        dopCounter++;
                    }
                }

                if (errorCounter >= 3 || (groupForDop[0] == groupForDop[1] && groupForDop[0] != 0)) {
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    test.setPartialAnswrs(partialAnswers);
                    intent.putExtra("numb", 0);
                    intent.putExtra("text", "Экзамен не сдан!");
                    intent.putExtra("wasDop", 1);
                    intent.putExtra("dopQuantity", dopQuantity);
                    intent.putExtra("test", test);
                    startActivity(intent);
                    finish();
                }
                if (errorCounter <= 2 && btnCounter == 20) {
                    goDop();
                }

                if (errorCounter == 0 && btnCounter == 20) {
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("text", "Экзамен сдан!");
                    intent.putExtra("wasDop", 1);
                    intent.putExtra("dopQuantity", dopQuantity);
                    intent.putExtra("numb", 1);
                    intent.putExtra("test", test);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    void createDop(int[] groupForDop) {
        TestCreator creator = new TestCreator();
        dopQuestions = creator.generateDop(groupForDop);
       // dopTest.setTest(dopQuestions);
        for (Question question : dopQuestions) {
            answers.add(-1);
            fillQuestion(question);
            question.setPartialAnsw(-1);
        }
        for(Question qu : dopQuestions){
            qu.setPartialAnsw(-1);
        }
        test.setDopTest(dopQuestions);
    }

    void goDop() {
        String str;
        final int flag;
        if (groupForDop[0] != 0 && groupForDop[1] != 0) {
            str = "Вы допустили две ошибки в разных категориях вопроса. Вам предложено решить 10 дополнительных вопросов";
            flag = 10;
            dopQuantity = flag + 20;
        } else {
            flag = 5;
            dopQuantity = flag + 20;
            str = "Вы допустили одну ошибку. Вам предложено решить 5 дополнительных вопросов";
        }
        createDop(groupForDop);
        int count = 0;
        for (View view : dopBtnArr) {
            final int index = +count;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListQuestActivity.this, QuestionActivity.class);
                    intent.putExtra("class", dopQuestions.get(index));
                    intent.putExtra("isDop", 1);
                    btnCounter++;
                    currDop++;
                    view.setEnabled(false);
                    view.setBackgroundColor(Color.rgb(255, 255, 255));
                    startActivityForResult(intent, 0);
                }
            });
            count++;
        }
        new AlertDialog.Builder(this)
                .setTitle("Внимание")
                .setMessage(str)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Приступить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        for (int i = 0; i < flag; i++) {
                            Button btn = (Button) dopBtnArr.get(i);
                            btn.setEnabled(true);
                            btn.setVisibility(View.VISIBLE);
                        }
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Предупреждение")
                .setMessage("Вы действительно хотите закончить тестирование?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(ListQuestActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }


    private void fillQuestion(Question question){
        try {
            switch (question.getGroup()) {
                case 1:
                    InputStream stream = this.getResources().openRawResource(R.raw.textsgroup1);
                    question.setTextQuest(stream, question.getId());
                    stream.close();
                    InputStream stream11 = this.getResources().openRawResource(R.raw.variants1);
                    question.setVariants(stream11, question.getId());
                    stream11.close();
                    InputStream stream12 = this.getResources().openRawResource(R.raw.answgroup1);
                    question.setRighAnsw(stream12, question.getId());
                    stream12.close();

                    InputStream stream123 = this.getResources().openRawResource(R.raw.explain1);
                    question.setExplainText(stream123, question.getId());
                    stream123.close();
                    break;
                case 2:
                    InputStream stream2 = this.getResources().openRawResource(R.raw.textsgroup2);
                    question.setTextQuest(stream2, question.getId());
                    stream2.close();
                    InputStream stream21 = this.getResources().openRawResource(R.raw.variants2);
                    question.setVariants(stream21, question.getId());
                    stream21.close();
                    InputStream stream22 = this.getResources().openRawResource(R.raw.answgroup2);
                    question.setRighAnsw(stream22, question.getId());
                    stream22.close();

                    InputStream stream122 = this.getResources().openRawResource(R.raw.explain2);
                    question.setExplainText(stream122, question.getId());
                    stream122.close();
                    break;
                case 3:
                    InputStream stream3 = this.getResources().openRawResource(R.raw.textsgroup3);
                    question.setTextQuest(stream3, question.getId());
                    stream3.close();
                    InputStream stream31 = this.getResources().openRawResource(R.raw.variants3);
                    question.setVariants(stream31, question.getId());
                    stream31.close();
                    InputStream stream33 = this.getResources().openRawResource(R.raw.answgroup3);
                    question.setRighAnsw(stream33, question.getId());
                    stream33.close();

                    InputStream stream121 = this.getResources().openRawResource(R.raw.explain3);
                    question.setExplainText(stream121, question.getId());
                    stream121.close();
                    break;
                case 4:
                    InputStream stream4 = this.getResources().openRawResource(R.raw.textsgroup4);
                    question.setTextQuest(stream4, question.getId());
                    stream4.close();
                    InputStream stream41 = this.getResources().openRawResource(R.raw.variants4);
                    question.setVariants(stream41, question.getId());
                    stream41.close();
                    InputStream stream44 = this.getResources().openRawResource(R.raw.answgroup4);
                    question.setRighAnsw(stream44, question.getId());
                    stream44.close();

                    InputStream stream124 = this.getResources().openRawResource(R.raw.explain4);
                    question.setExplainText(stream124, question.getId());
                    stream124.close();
                    break;
            }
        } catch (IOException ex) {
            System.exit(-2);
        }
    }
}
