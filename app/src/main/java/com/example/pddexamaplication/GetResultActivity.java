package com.example.pddexamaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent data = getIntent();
        TextView txt = findViewById(R.id.textView3);
        String str = data.getStringExtra("text");
        String pic = data.getStringExtra("pic");
        assert pic != null;
        if(pic.equals("0")){
            ImageView image = findViewById(R.id.imageView2);
            image.setImageResource(R.drawable.svetoforerror);
        } else{
            ImageView image = findViewById(R.id.imageView2);
            image.setImageResource(R.drawable.svetoforgood);
        }
        txt.setText(str);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                finish();
                break;
            case R.id.button3:
                Intent intent = new Intent(GetResultActivity.this, MainActivity.class);
                startActivity(intent);
        }
    }
    }
