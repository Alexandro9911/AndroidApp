package com.example.pddexamaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GetResultActivity extends AppCompatActivity {
    Test tst;
    int[] part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Результат");
        Intent data = getIntent();
        tst = (Test) data.getSerializableExtra("test");
        TextView txt = findViewById(R.id.textView3);
        part = data.getIntArrayExtra("partialArr");
        String str = data.getStringExtra("text");
        String pic = data.getStringExtra("pic");
        final String quantity = data.getStringExtra("quantity");
        LinearLayout linearLayout = findViewById(R.id.linay3);
        assert pic != null;
        txt.setText(str);
        if (pic.equals("0")) {
            ImageView image = findViewById(R.id.imageView2);
            image.setImageResource(R.drawable.svetoforerror);
            Button btn1 = new Button(this);
            int idbtn1 = 33;
            btn1.setId(idbtn1);
            btn1.setText("Смотреть ошибки");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GetResultActivity.this, ExplainListActivity.class);
                    intent.putExtra("test", tst);
                    intent.putExtra("partial", part);
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn1);
            Button btn2 = new Button(this);
            int idbtn2 = 44;
            btn2.setId(idbtn2);
            btn2.setText("Заново");
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            linearLayout.addView(btn2);
        }
        if (pic.equals("1")) {
            ImageView image = findViewById(R.id.imageView2);
            image.setImageResource(R.drawable.svetoforgood);
            Button btn2 = new Button(this);
            int idbtn2 = 44;
            btn2.setId(idbtn2);
            btn2.setText("Заново");
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            linearLayout.addView(btn2);
        }
        if (pic.equals("2")) {
            ImageView image = findViewById(R.id.imageView2);
            image.setImageResource(R.drawable.svetofordop);
//            Button btn2 = new Button(this);
//            int idbtn2 = 44;
//            btn2.setId(idbtn2);
//            btn2.setText("начать");
//            btn2.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(GetResultActivity.this, MoreQuestionActivity.class);
//                    intent.putExtra("test", tst);
//                    intent.putExtra("quantity", quantity);
//                    startActivity(intent);
//                }
//            });
//            linearLayout.addView(btn2);
//        }
//
            {
               // ImageView image = findViewById(R.id.imageView2);
               // image.setImageResource(R.drawable.svetoforerror);
                Button btn1 = new Button(this);
                int idbtn1 = 33;
                btn1.setId(idbtn1);
                btn1.setText("Смотреть ошибки");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GetResultActivity.this, ExplainListActivity.class);
                        intent.putExtra("test", tst);
                        intent.putExtra("partial", part);
                        startActivity(intent);
                    }
                });
                linearLayout.addView(btn1);
                Button btn2 = new Button(this);
                int idbtn2 = 44;
                btn2.setId(idbtn2);
                btn2.setText("Заново");
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                linearLayout.addView(btn2);
            }
        }
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.button2:
//                finish();
//                break;
//            case R.id.button3:
//                Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
//                startActivity(intent);
//        }
//    }
    }
}
