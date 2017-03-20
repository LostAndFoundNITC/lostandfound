package com.nsl.lostandfound;

/**
 * Created by lakshit on 3/19/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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


public class BackgroundWorkerFinder extends AsyncTask<String,Void,String> {
    Context context;

    AlertDialog alertDialog;
    BackgroundWorkerFinder (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://andromeda.nitc.ac.in/~m150035ca/freport.php";
        if(type.equals("ReportFinder")) {
            try {

                String name = params[1];
                String description = params[2];
                String color = params[3];
                String length = params[4];
                String width = params[5];
                String location = params[6];
                String email = params[7];
               // Toast.makeText(context,"Email:"+email,Toast.LENGTH_LONG).show();
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"+
                        URLEncoder.encode("color","UTF-8")+"="+URLEncoder.encode(color,"UTF-8")+"&"+
                        URLEncoder.encode("length","UTF-8")+"="+URLEncoder.encode(length,"UTF-8")+"&"+
                        URLEncoder.encode("width","UTF-8")+"="+URLEncoder.encode(width,"UTF-8")+"&"+
                        URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    result +="\n\n";
                }
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

    protected   void onPretExecute(){
       // Toast.makeText(context,"Report Processing",Toast.LENGTH_LONG).show();
    }
    protected   void onPostExecute(String result){
      //  Toast.makeText(context,"Report Successfull",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}