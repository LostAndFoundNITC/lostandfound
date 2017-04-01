/**
*\brief This is the BackGroundWorkerFinder file.This Code will be invoked when User clicks the report button in Finder activity .
*This class is recieving data from Finder activity and sending it to the php file.
*It is in the package com.nsl.lostandfound;
*/

package com.nsl.lostandfound;
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

/**\class class with name BackgroundWorkerFinder is being created here.BackgroundWorkerFinder extends AsyncTask.
*/

public class BackgroundWorkerFinder extends AsyncTask<String,Void,String> {
    Context context;

    AlertDialog alertDialog;
    BackgroundWorkerFinder (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://andromeda.nitc.ac.in/~m150035ca/report.php";
        if(type.equals("login")) {
            try {
                String name = params[1];
                String description = params[2];
                String color = params[3];
                String length = params[4];
                String width = params[5];
                String location = params[6];
                String email = params[7];
                String image = params[8];


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
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(image,"UTF-8")+"&"+
                        URLEncoder.encode("Status","UTF-8")+"="+URLEncoder.encode("f","UTF-8");

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

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Report Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
//
///**
// * Created by nik on 3/10/2017.
// */
//
//        import android.app.AlertDialog;
//        import android.content.Context;
//        import android.os.AsyncTask;
//
//        import java.io.BufferedReader;
//        import java.io.BufferedWriter;
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.io.InputStreamReader;
//        import java.io.OutputStream;
//        import java.io.OutputStreamWriter;
//        import java.net.HttpURLConnection;
//        import java.net.MalformedURLException;
//        import java.net.URL;
//        import java.net.URLEncoder;
//
//
//public class BackgroundWorker extends AsyncTask<String,Void,String> {
//    Context context;
//
//    AlertDialog alertDialog;
//    BackgroundWorker (Context ctx) {
//        context = ctx;
//    }
//    @Override
//    protected String doInBackground(String... params) {
//        String type = params[0];
//        String login_url = "http://andromeda.nitc.ac.in/~m150035ca/report.php";
//        if(type.equals("login")) {
//            try {
//                String name = params[1];
//                String description = params[2];
//                String color = params[3];
//                String length = params[4];
//                String width = params[5];
//                String location = params[6];
//                String email = params[7];
//
//                URL url = new URL(login_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//
//                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
//                        URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"+
//                        URLEncoder.encode("color","UTF-8")+"="+URLEncoder.encode(color,"UTF-8")+"&"+
//                        URLEncoder.encode("length","UTF-8")+"="+URLEncoder.encode(length,"UTF-8")+"&"+
//                        URLEncoder.encode("width","UTF-8")+"="+URLEncoder.encode(width,"UTF-8")+"&"+
//                        URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"+
//                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
//
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
//                String result="";
//                String line="";
//                while((line = bufferedReader.readLine())!= null) {
//                    result += line;
//                    result +="\n\n";
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}
