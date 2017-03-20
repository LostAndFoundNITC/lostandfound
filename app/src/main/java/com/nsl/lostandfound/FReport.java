package com.nsl.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FReport extends AppCompatActivity {
    EditText NameEt, DescriptionEt, ColorEt, LengthEt, WidthEt, LocationEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freport);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        String name= extras.getString("name");
        Toast.makeText(this,email+name,Toast.LENGTH_LONG).show();
        NameEt = (EditText)findViewById(R.id.etName);
        DescriptionEt = (EditText)findViewById(R.id.etDes);
        ColorEt = (EditText)findViewById(R.id.etColor);
        LengthEt = (EditText)findViewById(R.id.etLength);
        WidthEt = (EditText)findViewById(R.id.etWidth);
        LocationEt = (EditText)findViewById(R.id.etLocation);
    }

    public void freport(View view){
        String name = NameEt.getText().toString();
        String description = DescriptionEt.getText().toString();
        String color = ColorEt.getText().toString();
        String length = LengthEt.getText().toString();
        String width = WidthEt.getText().toString();
        String location = LocationEt.getText().toString();
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        String type = "ReportFinder";
        BackgroundWorkerFinder backgroundWorker = new BackgroundWorkerFinder(this);
        backgroundWorker.execute(type, name, description, color, length, width, location,email);
        Toast.makeText(this,email,Toast.LENGTH_LONG).show();
        extras = getIntent().getExtras();
        String username= extras.getString("name");
        Intent intent = new Intent(this, Finder.class);
        intent.putExtra("name",username);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
