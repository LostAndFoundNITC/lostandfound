package com.nsl.lostandfound;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;


public class EditProfile extends ActionBarActivity {
    EditText AddressEt, PhoneEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        AddressEt = (EditText)findViewById(R.id.etAddress);
        PhoneEt = (EditText)findViewById(R.id.etPhone);
    }

    public void UpdateProfile(View view) {
        //Bundle extras = getIntent().getExtras();
        String address = AddressEt.getText().toString();
        String phone = PhoneEt.getText().toString();
        //String email= extras.getString("email");
        String type = "EditProfile";
        BackgroundWorkerProfile backgroundWorkerProfile = new BackgroundWorkerProfile(this);
        backgroundWorkerProfile.execute(type, address, phone);
    }

}