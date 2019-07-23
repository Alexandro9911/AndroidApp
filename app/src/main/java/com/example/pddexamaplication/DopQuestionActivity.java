package com.example.pddexamaplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DopQuestionActivity extends AppCompatActivity {
    Question quest = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dop);
        LinearLayout linLay = new LinearLayout(this);
        Intent data = getIntent();

        Question question = (Question) data.getSerializableExtra("question");
        quest = question;

        int containsImage = Integer.parseInt(data.getStringExtra("contImage"));
        if (containsImage  == 1) {
            ImageView imageView = new ImageView(this);
            assert question != null;
            String mDrawableName = "g" + question.getGroup() + "n" + question.getId();
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            imageView.setImageResource(resID);
            linLay.addView(imageView);
        }

        TextView textView = new TextView(this);
        assert question != null;
        textView.setText(question.getTextQuest());
        linLay.addView(textView);

        int quantityCase = quest.getQuantity();
        String[] variants = quest.getVariantsAnsw();
        for (int i = 0; i < quantityCase; i++) {
            final Button btn = new Button(this);
            btn.setId(i + 1);
            String textQuest = variants[i];
            btn.setText(textQuest);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent answerIntent = new Intent();
                    if (btn.getId() == quest.getRighAnsw()) {
                        answerIntent.putExtra("answ", "1");
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    } else {
                        answerIntent.putExtra("answ", "0");
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    }
                }
            });
            linLay.addView(btn);
        }
    }
}
