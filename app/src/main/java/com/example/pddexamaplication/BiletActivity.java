package com.example.pddexamaplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BiletActivity extends AppCompatActivity {
    Test test = new Test();
    int count = 0;
    int errors = 0;
    Question quest;
    int bilet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilet);

        final Intent data = getIntent();
        final ScrollView scroll = findViewById(R.id.scrollv);
        Bundle bndl = data.getExtras();
        assert bndl != null;
        final int numb = bndl.getInt("numb");
        bilet = numb;
        Objects.requireNonNull(getSupportActionBar()).setTitle("Билет №" + numb);

        test = (Test) data.getSerializableExtra("test");
        count = Integer.parseInt(data.getStringExtra("counter"));
        errors = Integer.parseInt(data.getStringExtra("errors"));

        assert test != null;
        final List<Question> tst = test.getTest();
        final LinearLayout linearLayout = findViewById(R.id.LinearBilet);
        if (count < 20) {
            final Question question = tst.get(count);
            quest = question;
            final List<Button> listBtn = new ArrayList<>();
            TextView numberQuestView = new TextView(this);
            ImageView img = new ImageView(this);
            TextView textView = new TextView(this);

            String nuberText = "Вопрос № " + (count + 1);
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
                btn.setId(i + 1);
                btn.setText(vr[i]);
                listBtn.add(btn);
            }

            for (final Button btn : listBtn) {
                linearLayout.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scroll.fullScroll(ScrollView.FOCUS_DOWN);
                        question.setPartialAnsw(view.getId());
                        for (Button button : listBtn) {
                            button.setEnabled(false);
                        }
                        boolean res = question.checkAnsw();
                        if (res) {
                            int right = question.getRighAnsw();
                            Button rightbtn = findViewById(right);
                            rightbtn.setBackgroundColor(Color.rgb(0, 196, 0));
                            if (count == 19) {
                                Intent intent = new Intent(BiletActivity.this, BiletResultActivity.class);
                                intent.putExtra("errors", Integer.toString(errors));
                                intent.putExtra("test", test);
                                intent.putExtra("test", test);
                                intent.putExtra("numb", bilet);
                                startActivity(intent);
                            } else {
                                count++;
                                Intent intent = new Intent(BiletActivity.this, BiletActivity.class);
                                intent.putExtra("test", test);
                                intent.putExtra("numb", numb);
                                intent.putExtra("counter", Integer.toString(count));
                                intent.putExtra("errors", Integer.toString(errors));
                                startActivity(intent);
                            }
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
                        scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        }
    }

    private void buildLay() {
        TextView explainText = new TextView(this);
        explainText.setText(quest.getExplainText());
        LinearLayout linearLayout = findViewById(R.id.LinearBilet);
        linearLayout.addView(explainText);
        Button exitBtn = new Button(this);
        if (count == 19) {
            exitBtn.setText("Завершить");
        } else {
            exitBtn.setText("Дальше");
        }
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 19) {
                    Intent intent = new Intent(BiletActivity.this, BiletResultActivity.class);
                    intent.putExtra("errors", Integer.toString(errors));
                    intent.putExtra("test", test);
                    intent.putExtra("numb", bilet);

                    startActivity(intent);
                } else {
                    count++;
                    Intent intent = new Intent(BiletActivity.this, BiletActivity.class);
                    intent.putExtra("test", test);
                    intent.putExtra("numb", bilet);
                    intent.putExtra("counter", Integer.toString(count));
                    intent.putExtra("errors", Integer.toString(errors));
                    startActivity(intent);
                }
            }
        });
        linearLayout.addView(exitBtn);
    }

    @Override
    public void onBackPressed() {
        if (count != 0) {
            count--;
            Intent intent = new Intent(BiletActivity.this, BiletActivity.class);

            intent.putExtra("errors", Integer.toString(errors));
            intent.putExtra("test", test);
            intent.putExtra("counter", Integer.toString(count));
            intent.putExtra("numb", bilet);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Предупреждение")
                    .setMessage("Вы действительно хотите закончить выполнение билета?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(BiletActivity.this, BiletsListActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    }).show();
        }
    }
}
