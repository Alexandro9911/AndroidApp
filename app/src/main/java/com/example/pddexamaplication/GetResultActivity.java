package com.example.pddexamaplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class GetResultActivity extends AppCompatActivity {
    Test tst;
    int[] part;
    int dops = 1;
    int dopQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Результат");
        Intent data = getIntent();

        tst = (Test) data.getSerializableExtra("test");
        TextView txt = findViewById(R.id.textView3);
        part = tst.getPartialAnswrs();
        String str = data.getStringExtra("text");

        Bundle bndl = data.getExtras();
        assert bndl != null;
        final int numb = bndl.getInt("numb");
        final int wasDop = bndl.getInt("wasDop");
        dopQuantity = bndl.getInt("dopQuantity");
        Log.d("watch","dopQuantity  getResult = " + dopQuantity);
        LinearLayout linearLayout = findViewById(R.id.linay3);
        txt.setText(str);
        if (numb == 0 || numb == 2) {
            ImageView image = findViewById(R.id.imageView2);
            if(numb == 0) {
                image.setImageResource(R.drawable.svetoforerror);
            } else {
                image.setImageResource(R.drawable.svetoforgood);
            }
            Button btn1 = new Button(this);
            int idbtn1 = 33;
            btn1.setId(idbtn1);
            btn1.setText("Смотреть ошибки");

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(GetResultActivity.this, ExplainListActivity.class);
                    intent.putExtra("test", tst);
                    intent.putExtra("wasDop",wasDop);
                    intent.putExtra("dopQuantity",dopQuantity);
                    intent.putExtra("dop",dops);
                    startActivity(intent);
                    finish();
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


        if (numb == 1 ) {
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
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Предупреждение")
                .setMessage("Вы действительно хотите закончить экзамен?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }
}
