package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    int ANSWER = -1;
    int rightAnsw = -1;
    int IntentionViev = 0;

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
        rightAnsw = Integer.parseInt(right);

        if (containsImage == 1) {
            String mDrawableName ="g"+group+"n"+ident;
            int resID =  getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
            ImageView img = new ImageView(this);
            img.setImageResource(resID);
            linLay.addView(img);
        }
        TextView text = new TextView(this);
        String strText = intent.getStringExtra("textQuest");
       // String sdf = strText+ " answ ="+ rightAnsw;
        text.setText(strText);
        text.setMaxWidth(300);
        linLay.addView(text);
        int quantity = Integer.parseInt(intent.getStringExtra("quantityCase"));
        Button btn1, btn2,btn3,btn4,btn5;
        btn1 = new Button(this);
        btn2 = new Button(this);
        btn3 = new Button(this);
        btn4 = new Button(this);
        btn5 = new Button(this);
        final Button[] btnArr = {btn1, btn2,btn3,btn4,btn5};
        String variantsAnsw = intent.getStringExtra("variants");
        assert variantsAnsw != null;
        String[] variantsArr = variantsAnsw.split("#");


        for (int i = 0; i <= quantity; i++){
            String str =variantsArr[i];
            btnArr[i].setText(str);
            final int finalI = i+1;
            btnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent answerIntent = new Intent();
                    if(finalI == rightAnsw){
                        answerIntent.putExtra("answ","1");
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    } else {
                        answerIntent.putExtra("answ","0");
                        setResult(RESULT_OK, answerIntent);
                        finish();
                    }
                }
            });
            linLay.addView(btnArr[i]);
        }
    }
//    public void onClick(View view) {
//       // Intent answerIntent = new Intent();
//        //answerIntent.putExtra("answer", 0);
//        Intent intent = new Intent(QuestionActivity.this, ListQuestActivity.class);
//        //setResult(0, answerIntent);
//       // intent.putExtra("answer", 0);
//        //startActivity(intent);
//        finish();
//    }
}
