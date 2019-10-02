package com.example.pddexamaplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BiletsListActivity extends AppCompatActivity {

   // SQLiteDatabase DB = openOrCreateDatabase("results",MODE_PRIVATE,null);
    Test tst = new Test();
    TestCreator creator = new TestCreator();
    int counter = 0;
    int errorsCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biletslist);
        LinearLayout linearLayout = findViewById(R.id.Linear1);
        for (int i = 1; i < 41 ; i++){
            Button btn = new Button(this);
            btn.setId(i + 100);
            String str = "Билет " + i;
            btn.setText(str);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tst = creator.generateBilet(finalI);
                    fillIn(tst);
                    Intent intent = new Intent(BiletsListActivity.this, BiletActivity.class);
                    intent.putExtra("test", tst);
                    String str = Integer.toString(counter);
                    intent.putExtra("counter",str);
                    intent.putExtra("errors", Integer.toString(errorsCounter));
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn);
        }
    }
//    private String previousResult(){
//
//    }

    private void fillIn(Test test){
        List<Question> questions = test.getTest();
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
}
