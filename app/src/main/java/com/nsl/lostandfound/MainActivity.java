package com.nsl.lostandfound;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */

   // public static final String EMAIL = "com.nsl.lostandfound.MAIL";
   // public static final String PASSWORD = "com.nsl.lostandfound.PASS";
            Context context;
    EditText UsernameEt, PasswordEt;
    String result="";
    MainActivity x =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt = (EditText)findViewById(R.id.input_email);
        PasswordEt = (EditText)findViewById(R.id.input_password);
        EditText e =(EditText) findViewById(R.id.input_email);
        e.clearComposingText();
        EditText p =(EditText) findViewById(R.id.input_password);
        p.clearComposingText();
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
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();

      if(isEmailValid(username) && isPasswordValid(password))
        {
            String type = "login";
            new BackgroundWorker1(this).execute(type, username.trim(), password.trim());


   }
        else {
             TextView t =(TextView)findViewById(R.id.warning);
          t.clearComposingText();
          t.setText("Invalid Pattern");
          }

    }









    class BackgroundWorker1 extends AsyncTask<String,Void,String> {
        //Context context;

        public char res;
        AlertDialog alertDialog;
        BackgroundWorker1 (Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://andromeda.nitc.ac.in/~m150035ca/login.php";
            if(type.equals("login")) {
                try {
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream= httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                    String line="";
                    while((line = bufferedReader.readLine())!= null) {
                        result += line;
                    }
                    res=result.toCharArray()[result.length()-1];

                    bufferedReader.close();

                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }


        @Override
        protected void onPostExecute(String result) {

            TextView t =(TextView)findViewById(R.id.warning);
            t.clearComposingText();
            //alertDialog.setMessage(res);
            //alertDialog.show();
            if(res == 't')
            {
                Toast.makeText(context,"Logged In Successfully !!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(x,home.class);
                startActivity(intent);

            }
            else {


                Toast.makeText(context," Unsuccessful Login !!",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
