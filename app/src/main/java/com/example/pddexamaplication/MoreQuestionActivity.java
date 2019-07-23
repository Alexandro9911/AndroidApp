package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoreQuestionActivity extends AppCompatActivity {
    int[] errs = {1,0};
    TestCreator creator = new TestCreator();
    Test test = creator.moreQuest(errs);
    List<Question> generated = test.getTest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morequestion);
        Intent data = getIntent();
        Test test = (Test) data.getSerializableExtra("test");
        assert test != null;
        List<Question> questions = creator.moreQuest(test.getGroups()).getTest();
        generated = creator.moreQuest(test.getGroups()).getTest();
        for(Question question : questions){
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
                        break;
                }
            }catch (IOException ex){
                System.exit(-2);
            }
        }
      //  int[] errorsByGroup = data.getIntArrayExtra("errorsByGroup");
        int[] errorsByGroup = test.getGroups();
        generated = creator.moreQuest(errorsByGroup).getTest();
        LinearLayout linLay = findViewById(R.id.linear01);
        assert errorsByGroup != null;
        if (errorsByGroup[0] != 0 && errorsByGroup[1] != 0) {
            int idCounter = 1;
            for (int i = 0; i < 10; i++) {
                final Button btn = new Button(this);
                String str = "Вопрос " + 2 + i;
                int idcounted = idCounter + 19;
                btn.setId(idcounted);
                idCounter++;
                btn.setText(str);
                final int finalI = i;
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MoreQuestionActivity.this, DopQuestionActivity.class);
                        List<Question> tst = generated;
                        intent.putExtra("question", (Serializable) generated.get(finalI));
                        intent.putExtra("contImage", "1");
                        btn.setBackgroundColor(Color.rgb(255, 255, 255));
                        startActivity(intent);
                        btn.setEnabled(false);
                    }
                });
                linLay.addView(btn);

            }
        }
        if(errorsByGroup[0] != 0 || errorsByGroup[1] != 0){
            int idCounter = 1;
            for (int i = 0; i < 5; i++) {
                final Button btn = new Button(this);
                String str = "Вопрос " + 2 + i;
                int idcounted = idCounter + 19;
                btn.setId(idcounted);
                idCounter++;
                btn.setText(str);
                final int finalI = i;
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MoreQuestionActivity.this, DopQuestionActivity.class);
                        List<Question> tst = generated;
                        intent.putExtra("question", (Serializable) generated.get(finalI));
                        intent.putExtra("contImage", "1");
                        btn.setBackgroundColor(Color.rgb(255, 255, 255));
                        startActivity(intent);
                        btn.setEnabled(false);
                    }
                });
                linLay.addView(btn);
            }
        }
    }

    int RESULT_TEST = -1;

//    public void onClick(View view) {
//        Button myButton = (Button) view;
//        int ident = myButton.getId();
//        Intent intent = new Intent(MoreQuestionActivity.this, DopQuestionActivity.class);
//        List<Question> tst = generated;
//        intent.putExtra("question", (Serializable) tst.get(ident));
//        myButton.setBackgroundColor(Color.rgb(255, 255, 255));
//        startActivity(intent);
//        myButton.setEnabled(false);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_TEST) {
            if (resultCode == RESULT_OK) {
                String answered = data.getStringExtra("answ");
                if (Integer.parseInt(answered) == 0) {
                    Intent intent = new Intent(MoreQuestionActivity.this, GetResultActivity.class);
                    intent.putExtra("pic", "0");
                    intent.putExtra("text", "Упс, что то пошло не так... Вы не сдали");
                    intent.putExtra("test", (Serializable) test);
                    startActivity(intent);
                }
            }
        }
    }
}
