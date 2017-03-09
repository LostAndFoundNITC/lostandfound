package com.nsl.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Mislayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mislayer);
    }

    protected void post()
    {
        Intent intent = new Intent(this , Workinprogress.class);
        startActivity(intent);
    }
    protected void search()
    {
        Intent intent = new Intent(this , Workinprogress.class);
        startActivity(intent);
    }
    protected void notification()
    {
        Intent intent = new Intent(this , Workinprogress.class);
        startActivity(intent);
    }
}
