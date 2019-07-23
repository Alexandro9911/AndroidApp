package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class QuestionActivity extends AppCompatActivity {
    int rightAnsw = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        LinearLayout linLay = findViewById(R.id.Linear1);
        final Intent intent = getIntent();
        int containsImage = Integer.parseInt(intent.getStringExtra("contImage"));
        String group = intent.getStringExtra("group");
        String ident = intent.getStringExtra("ident");
        String right = intent.getStringExtra("rightAnsw");
        String numb = intent.getStringExtra("number");
        int normal = Integer.parseInt(numb) +1;
        String txtAction = "Вопрос № "+ normal;
        Objects.requireNonNull(getSupportActionBar()).setTitle(txtAction);
        rightAnsw = Integer.parseInt(right);

        if (containsImage == 1) {
            String mDrawableName = "g" + group + "n" + ident;
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            ImageView img = new ImageView(this);
            img.setImageResource(resID);
            linLay.addView(img);
        }
        TextView text = new TextView(this);
        String strText = intent.getStringExtra("textQuest");
        // String sdf = strText+ " answ ="+ rightAnsw;
        text.setText(strText);
        //text.setMaxWidth(300);
        linLay.addView(text);
        //int quantity = Integer.parseInt(intent.getStringExtra("quantityCase"));
        final Question question = (Question) intent.getSerializableExtra("class");
        assert question != null;
        final int quantity = question.getQuantity();
        Button btn1, btn2, btn3, btn4, btn5;
        btn1 = new Button(this);
        btn2 = new Button(this);
        btn3 = new Button(this);
        btn4 = new Button(this);
        btn5 = new Button(this);
        final Button[] btnArr = {btn1, btn2, btn3, btn4, btn5};
        String[] vr = question.getVariantsAnsw();
        for (int i = 0; i < quantity; i++) {
            String str = vr[i];
            btnArr[i].setText(str);
            final int identify = i + 1;
            btnArr[i].setId(identify);
            final int finalI = i;
            btnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent answerIntent = new Intent();
                    question.setPartialAnsw(finalI);
                    if (identify == question.getRighAnsw()) {
                        answerIntent.putExtra("answ", "1");
                        answerIntent.putExtra("partial", Integer.toString(question.getPartialAnsw()));
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    } else {
                        answerIntent.putExtra("answ", "0");
                        answerIntent.putExtra("partial", Integer.toString(question.getPartialAnsw()));
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    }
                }
            });
            linLay.addView(btnArr[i]);
        }
    }
}
