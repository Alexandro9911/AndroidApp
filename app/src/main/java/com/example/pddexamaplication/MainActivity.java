package com.example.pddexamaplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view) {
        if(view.getId() == R.id.bilet){
            Intent intent = new Intent(MainActivity.this, BiletsListActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, ListQuestActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public void onBackPressed() {

    }
}
