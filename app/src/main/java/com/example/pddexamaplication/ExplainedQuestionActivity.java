package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ExplainedQuestionActivity extends AppCompatActivity {

    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explainedquestion);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Просмотр ошибок");
        Intent data = getIntent();
        ScrollView scroll = new ScrollView(this);
        scroll.removeAllViews();
        question = (Question) data.getSerializableExtra("question");
        LinearLayout linLay = findViewById(R.id.Linear2);

        int containsImage = Integer.parseInt(data.getStringExtra("contImage"));
        String group = Integer.toString(question.getGroup());
        String ident = Integer.toString(question.getId());
        int rightAnsw = question.getRighAnsw();
        int partialAnsw = Integer.parseInt(data.getStringExtra("partial"));
        TextView text = new TextView(this);
        String strText = question.getTextQuest();
        text.setText(strText);
        linLay.addView(text);

        if (containsImage == 1) {
            String mDrawableName = "g" + group + "n" + ident;
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            ImageView img = new ImageView(this);
            img.setImageResource(resID);
            linLay.addView(img);
        }
        assert question != null;
        int quantity = question.getQuantity();
        Button btn1, btn2, btn3, btn4, btn5;
        btn1 = new Button( this);
        btn2 = new Button(this);
        btn3 = new Button(this);
        btn4 = new Button(this);
        btn5 = new Button(this);
        final Button[] btnArr = {btn1, btn2, btn3, btn4, btn5};
        String[] vr = question.getVariantsAnsw();
        for (int i = 0; i < quantity; i++) {
            String str = vr[i];
            btnArr[i].setText(str);
            btnArr[i].setEnabled(false);

                if (i == partialAnsw) {
                    btnArr[i].setBackgroundColor(Color.rgb(229, 0, 0));
                }
                if (i + 1 == rightAnsw) {
                    btnArr[i].setBackgroundColor(Color.rgb(0, 196, 0));
                }
            linLay.addView(btnArr[i]);
        }
        TextView explain = new TextView(this);
        explain.setText(question.getExplainText());
        linLay.addView(explain);
        Button button = new Button(this);
        button.setText("Назад");
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        linLay.addView(button);
    }
}
