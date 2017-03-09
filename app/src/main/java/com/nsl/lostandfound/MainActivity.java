package com.nsl.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */

   // public static final String EMAIL = "com.nsl.lostandfound.MAIL";
   // public static final String PASSWORD = "com.nsl.lostandfound.PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    public void login(View view) {
        Intent intent = new Intent(this, home.class);
        EditText email = (EditText) findViewById(R.id.input_email);
        String mail = email.getText().toString();
        EditText password = (EditText) findViewById(R.id.input_password);
        String pass = password.getText().toString();

       // intent.putExtra(EMAIL, mail);
      // intent.putExtra(PASSWORD, pass);
       if(isEmailValid(mail) && isPasswordValid(pass))
        {
            startActivity(intent);

        }
        else {
            TextView tView = (TextView) findViewById(R.id.warning);
            tView.setText("Invalid Credentials");
        }
    }


}
