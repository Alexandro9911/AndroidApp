package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListQuestActivity extends AppCompatActivity {

    TestCreator creator = new TestCreator();
    Test test = creator.testCreator();
    List<Question> questions = test.getTest();
    int currentNumb = 0;
    int btnCounter = 0;
    int errorCounter = 0;
    int[] groupsWithQuantityError = {0,0,0,0};
    int[] groupForDop = {0,0};
    int[] partialAnswers = new int[20];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listquest);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Список вопросов");
        for(int i = 0; i < 20;i ++){
            partialAnswers[i] = -1;
        }
        for(Question question : questions){
            answers.add(-1);
            try {
                switch (question.getGroup()) {
                    case 1:
                        InputStream stream = this.getResources().openRawResource(R.raw.textsgroup1);
                        question.setTextQuest(stream,question.getId());
                        stream.close();
                        InputStream stream11 = this.getResources().openRawResource(R.raw.variants1);
                        question.setVariants(stream11,question.getId());
                        stream11.close();
                        InputStream stream12 = this.getResources().openRawResource(R.raw.answgroup1);
                        question.setRighAnsw(stream12,question.getId());
                        stream12.close();

                        InputStream stream123 = this.getResources().openRawResource(R.raw.explain1);
                        question.setExplainText(stream123,question.getId());
                        stream123.close();
                        break;
                    case 2:
                        InputStream stream2 = this.getResources().openRawResource(R.raw.textsgroup2);
                        question.setTextQuest(stream2,question.getId());
                        stream2.close();
                        InputStream stream21 = this.getResources().openRawResource(R.raw.variants2);
                        question.setVariants(stream21,question.getId());
                        stream21.close();
                        InputStream stream22 = this.getResources().openRawResource(R.raw.answgroup2);
                        question.setRighAnsw(stream22,question.getId());
                        stream22.close();

                        InputStream stream122 = this.getResources().openRawResource(R.raw.explain2);
                        question.setExplainText(stream122,question.getId());
                        stream122.close();
                        break;
                    case 3:
                        InputStream stream3 = this.getResources().openRawResource(R.raw.textsgroup3);
                        question.setTextQuest(stream3,question.getId());
                        stream3.close();
                        InputStream stream31 = this.getResources().openRawResource(R.raw.variants3);
                        question.setVariants(stream31,question.getId());
                        stream31.close();
                        InputStream stream33 = this.getResources().openRawResource(R.raw.answgroup3);
                        question.setRighAnsw(stream33,question.getId());
                        stream33.close();

                        InputStream stream121 = this.getResources().openRawResource(R.raw.explain3);
                        question.setExplainText(stream121,question.getId());
                        stream121.close();
                        break;
                    case 4:
                        InputStream stream4 = this.getResources().openRawResource(R.raw.textsgroup4);
                        question.setTextQuest(stream4,question.getId());
                        stream4.close();
                        InputStream stream41 = this.getResources().openRawResource(R.raw.variants4);
                        question.setVariants(stream41,question.getId());
                        stream41.close();
                        InputStream stream44 = this.getResources().openRawResource(R.raw.answgroup4);
                        question.setRighAnsw(stream44,question.getId());
                        stream44.close();

                        InputStream stream124 = this.getResources().openRawResource(R.raw.explain4);
                        question.setExplainText(stream124,question.getId());
                        stream124.close();
                        break;
                }
            }catch (IOException ex){
                System.exit(-2);
            }
        }
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
    static final private int RESULT_TEST = 0;

    public void onClick(View view) {
        Button myButton = (Button)view;
        int number = idParser(myButton);
        Intent intent = new Intent(ListQuestActivity.this, QuestionActivity.class);
        String stringQuest = questions.get(number).getTextQuest()+ "answ = "+ questions.get(number).getRighAnsw();
        intent.putExtra("textQuest", stringQuest);
        intent.putExtra("number",Integer.toString(number));
        intent.putExtra("quantityCase",Integer.toString(questions.get(number).getQuantity()));
        intent.putExtra("contImage", "1");
        //intent.putExtra("variants",variantsAnsw);
        intent.putExtra("ident",Integer.toString(questions.get(number).getId()));
        intent.putExtra("group",Integer.toString(questions.get(number).getGroup()));
        intent.putExtra("rightAnsw", Integer.toString(questions.get(number).getRighAnsw()));
        intent.putExtra("class", questions.get(number));
        myButton.setEnabled(false);
        myButton.setBackgroundColor(Color.rgb(255, 255, 255));
        startActivityForResult(intent,RESULT_TEST);
    }

    List<Integer> answers = new ArrayList<>();

    private int getGroup(int number){
        int answ = -1;
        if(number >= 1 && number <= 5){
            answ =  1;
        }
        if(number >= 6 && number <= 10){
            answ = 2;
        }
        if(number >= 11 && number <= 15){
            answ = 3;
        }
        if(number >= 16 && number <= 20){
            answ = 4;
        }

        return answ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_TEST ) {
            if (resultCode == RESULT_OK) {
                String answerResult = data.getStringExtra("answ");
                partialAnswers[currentNumb] = Integer.parseInt(data.getStringExtra("partial"));
               // answers.add(currentNumb,Integer.parseInt(answered));
                int res = Integer.parseInt(answerResult);
                answers.set(currentNumb,res);
                int gr =getGroup(btnCounter);
                if(res ==0 ){
                    errorCounter++;
                    groupsWithQuantityError[gr-1]++;
                }
                int fatal = 0;
                int summ = 0;
                int cnt = 0;
                for(int i = 0; i < 4; i++) {
                    if (groupsWithQuantityError[i] >= 2) {
                        fatal = 1;
                    }
                    if(groupsWithQuantityError[i]==1) {
                        summ += groupsWithQuantityError[i];
                        groupForDop[cnt] += 1;
                        cnt++;
                    }
                }
                if(errorCounter >= 3 || fatal ==1){
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("pic", "0");
                    intent.putExtra("text", "Упс, что то пошло не так... Вы не сдали");
                    intent.putExtra("partialArr", partialAnswers);
                    intent.putExtra("test", (Serializable) test);
                    startActivity(intent);
                }
                if(!answers.contains(0) && btnCounter ==20){
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("text", "Вы все решили");
                    intent.putExtra("pic", "1");
                    intent.putExtra("test", (Serializable) test);
                    startActivity(intent);
                }
                if(summ!= 0 && btnCounter ==20){
                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
                    intent.putExtra("text", "Вы допустили ошибки.");
                    intent.putExtra("pic", "2");
                    intent.putExtra("partialArr", partialAnswers);
                    //intent.putExtra("guantity", "10");
                    //test.setDop(groupForDop);
                    intent.putExtra("test", (Serializable) test);
                    startActivity(intent);
                }
//                if(summ == 1 && btnCounter == 20){
//                    Intent intent = new Intent(ListQuestActivity.this, GetResultActivity.class);
//                    intent.putExtra("text", "Вы допустили одну ошибку. Вам предложено решить 5 дополнительнвх поросов");
//                    intent.putExtra("pic", "2");
//                    intent.putExtra("guantity", "5");
//                    test.setDop(groupForDop);
//                    intent.putExtra("test", (Serializable) test);
//                    startActivity(intent);
//                }
            }
        }
    }
}
