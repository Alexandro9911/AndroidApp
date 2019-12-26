package com.example.pddexamaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BiletResultActivity extends AppCompatActivity {

    Test tst;
    int bilet = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biletresult);
        TextView res = findViewById(R.id.resviev);
        Intent data = getIntent();
        Bundle bndl = data.getExtras();
        assert bndl != null;
        bilet = bndl.getInt("numb");
        tst = (Test) data.getSerializableExtra("test");
        int errs =  20 - Integer.parseInt(data.getStringExtra("errors"));
        String str = errs + "/" + 20;
        if(errs >= 18){
            res.setText(str);
            res.setTextColor(getResources().getColor(R.color.tvGreen));
         } else {
            res.setTextColor(getResources().getColor(R.color.tvRed));
            res.setText(str);
            }
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
