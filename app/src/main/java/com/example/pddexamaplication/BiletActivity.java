package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BiletActivity extends AppCompatActivity {
    List<Integer> answrs = new ArrayList<>();
    Test test = new Test();
    int count = 0;
    int errors = 0;
    Question quest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilet);
        final Intent data = getIntent();
        test = (Test) data.getSerializableExtra("test");
        count = Integer.parseInt(data.getStringExtra("counter"));
        errors = Integer.parseInt(data.getStringExtra("errors"));
        assert test != null;
        final List<Question> tst = test.getTest();
        LinearLayout linearLayout = findViewById(R.id.LinearBilet);
        int counter = 0;
        final Question question = tst.get(count);
        quest = question;
        final List<Button> listBtn = new ArrayList<>();
        TextView numberQuestView = new TextView(this);
        ImageView img = new ImageView(this);
        TextView textView = new TextView(this);
        String nuberText = "Вопрос № " + (count +1);
        numberQuestView.setText(nuberText);
        linearLayout.addView(numberQuestView);

        String mDrawableName = "g" + question.getGroup() + "n" + question.getId();
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        img.setImageResource(resID);
        linearLayout.addView(img);

        String text = question.getTextQuest();
        textView.setText(text);
        linearLayout.addView(textView);
        String[] vr = question.getVariantsAnsw();
        for (int i = 0; i < question.getQuantity(); i++) {
            Button btn = new Button(this);
            btn.setId(i+1);
            btn.setText(vr[i]);
            listBtn.add(btn);
        }

        for (final Button btn : listBtn) {

            linearLayout.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    question.setPartialAnsw(view.getId());
                    for (Button button : listBtn) {
                        button.setEnabled(false);
                    }
                    boolean res = question.checkAnsw();
                    if (res) {
                        int right = question.getRighAnsw();
                        Button rightbtn = findViewById(right);
                        rightbtn.setBackgroundColor(Color.rgb(0, 196, 0));
                    } else {
                        errors++;
                        int right = question.getRighAnsw();
                        Button rightbtn = findViewById(right);
                        rightbtn.setBackgroundColor(Color.rgb(0, 196, 0));

                        int partial = question.getPartialAnsw();
                        Button partialBtn = findViewById(partial);
                        partialBtn.setBackgroundColor(Color.rgb(196, 0, 0));
                    }
                    buildLay();
                }
            });
        }
    }

    private void buildLay(){
        TextView explainText = new TextView(this);
        explainText.setText(quest.getExplainText());
        LinearLayout linearLayout = findViewById(R.id.LinearBilet);
        linearLayout.addView(explainText);
        Button exitBtn = new Button(this);
        exitBtn.setText("Дальше");
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 20){
                    Intent intent = new Intent(BiletActivity.this,BiletResultActivity.class);
                    intent.putExtra("errors", Integer.toString(errors));
                    intent.putExtra("test", test);
                    startActivity(intent);

                }
                count++;
                Intent intent = new Intent(BiletActivity.this, BiletActivity.class);
                intent.putExtra("test", test);
                intent.putExtra("counter", Integer.toString(count));
                intent.putExtra("errors", Integer.toString(errors));
                startActivity(intent);
            }
        });
        linearLayout.addView(exitBtn);
    }
}
