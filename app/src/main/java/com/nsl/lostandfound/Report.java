package com.nsl.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Report extends ActionBarActivity {
    EditText NameEt, DescriptionEt, ColorEt, LengthEt, WidthEt, LocationEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        NameEt = (EditText)findViewById(R.id.etName);
        DescriptionEt = (EditText)findViewById(R.id.etDes);
        ColorEt = (EditText)findViewById(R.id.etColor);
        LengthEt = (EditText)findViewById(R.id.etLength);
        WidthEt = (EditText)findViewById(R.id.etWidth);
        LocationEt = (EditText)findViewById(R.id.etLocation);
    }

    public void OnLogin(View view) {
        String name = NameEt.getText().toString();
        String description = DescriptionEt.getText().toString();
        String color = ColorEt.getText().toString();
        String length = LengthEt.getText().toString();
        String width = WidthEt.getText().toString();
        String location = LocationEt.getText().toString();
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, description, color, length, width, location,email);
        Toast.makeText(this,"Report Successfull",Toast.LENGTH_LONG).show();
        extras = getIntent().getExtras();
        String username= extras.getString("name");
        Intent intent = new Intent(this, Mislayer.class);
        intent.putExtra("name",username);
        intent.putExtra("email",email);
        startActivity(intent);
    }

}