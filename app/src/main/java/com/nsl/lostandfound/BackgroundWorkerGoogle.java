package com.nsl.lostandfound;

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

/**
 * Created by lakshit on 3/17/2017.
 */
class BackgroundWorkerGoogle extends AsyncTask<String, Void, String> {
    public char res;
    String result = "";

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://andromeda.nitc.ac.in/~m150037ca/login.php";
        if (type.equals("GoogleLogin")) {
            try {
                String personName = params[1];
                String personEmail = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("personName", "UTF-8") + "=" + URLEncoder.encode(personName, "UTF-8") + "&"
                        + URLEncoder.encode("personEmail", "UTF-8") + "=" + URLEncoder.encode(personEmail, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                res = result.toCharArray()[result.length() - 1];

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

}
