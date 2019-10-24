package com.example.pddexamaplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class BiletResultActivity extends AppCompatActivity {

    Test tst;
    int bilet = 0;
    private static final String TAG = "myLogs";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biletresult);
        TextView res = findViewById(R.id.resviev);
        Intent data = getIntent();
        Bundle bndl = data.getExtras();
        assert bndl != null;
        final int numb = bndl.getInt("numb");
        bilet = numb;
        tst = (Test) data.getSerializableExtra("test");
        int errs =  20 - Integer.parseInt(data.getStringExtra("errors"));
        String str = errs + "/" + 20;
//        if(errs >= 18){
//            Log.d(TAG,"green" + " errs: " + errs);
//            res.setText(str);
//
//            res.setTextColor(getResources().getColor(R.color.tvGreen));
//            //int textColor = ContextCompat.getColor(this, R.color.tvGreen);
//            //res.setTextColor(Color.rgb(229, 0, 0));
//            //res.setTextColor(0xFF0000FF);
//         } else {
//
//            res.setTextColor(getResources().getColor(R.color.tvRed));
//            // int textColor = ContextCompat.getColor(this, R.color.tvRed);
//            // res.setTextColor(Color.rgb(0, 196, 0));
//            // res.setTextColor(0xFF0000FF);
//            Log.d(TAG,"red" + " errs: " + errs);
//            res.setText(str);
//            }
        res.setText(str);
        res.setTextColor(getResources().getColor(R.color.tvGreen));
    }

    public void onClick(View view) {
        if(view.getId() == R.id.toMain){
            Intent intent = new Intent(BiletResultActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.again) {
            Intent intent = new Intent(BiletResultActivity.this, BiletActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("test",tst);
            intent.putExtra("counter", Integer.toString(0));
            intent.putExtra("numb", bilet);
            intent.putExtra("errors", Integer.toString(0));
            startActivity(intent);
        }
        if(view.getId() == R.id.nextBilet){
            Intent intent = new Intent(BiletResultActivity.this, BiletsListActivity.class);
            startActivity(intent);
        }
    }
}
