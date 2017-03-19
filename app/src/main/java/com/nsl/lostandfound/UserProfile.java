package com.nsl.lostandfound;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

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

public class UserProfile extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        String name= extras.getString("name");
        String email= extras.getString("email");
        //String photo= extras.getString("photo");
        TextView t  =(TextView)findViewById(R.id.PersonName);
        t.setText(name);
        TextView t1  =(TextView)findViewById(R.id.PersonEmail);
        t1.setText(email);

        String type="GetProfile";
        BackgroundWorkerGetContact backgroundWorker1 = new BackgroundWorkerGetContact(this);
        backgroundWorker1.execute(type,email);

        String type2="GetAddress";
        BackgroundWorkerGetAddress backgroundWorker2 = new BackgroundWorkerGetAddress(this);
        backgroundWorker2.execute(type2,email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.lost) {
            Intent intent = new Intent(this, Mislayer.class);
            startActivity(intent);
            // Handle the lost action
        } else if (id == R.id.found) {
            Intent intent = new Intent(this, Workinprogress.class);
            startActivity(intent);
        } else if (id == R.id.user_posts) {
            Intent intent = new Intent(this, Workinprogress.class);
            startActivity(intent);
        } else if (id == R.id.user_profile) {

            Bundle extras = getIntent().getExtras();
            String name= extras.getString("name");
            String email= extras.getString("email");
            String photo = extras.getString("photo");
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.logout) {
            signOut();
            revokeAccess();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent intent = new Intent(UserProfile.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                    }
                });
    }

    public void EditProfile(View view)
    { Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, EditProfile.class);
        String email= extras.getString("email");
        intent.putExtra("email",email);
        String name= extras.getString("name");
        intent.putExtra("name",name);
        startActivity(intent);
        //Toast.makeText(this,email,Toast.LENGTH_LONG).show();
    }


    String result="";




    public class BackgroundWorkerGetContact extends AsyncTask<String,Void,String> {
        Context context;

        AlertDialog alertDialog;
        BackgroundWorkerGetContact (Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://andromeda.nitc.ac.in/~m150035ca/GetProfile.php";
            if(type.equals("GetProfile")) {
                try {

                    String email = params[1];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data =URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

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
            TextView t2  =(TextView)findViewById(R.id.PersonContact);
            t2.setText(result);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }



    String address="";




    public class BackgroundWorkerGetAddress extends AsyncTask<String,Void,String> {
        Context context;

        AlertDialog alertDialog;
        BackgroundWorkerGetAddress (Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String login_url = "http://andromeda.nitc.ac.in/~m150035ca/GetAddress.php";
            if(type.equals("GetAddress")) {
                try {

                    String email = params[1];

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data =URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                    String line="";
                    while((line = bufferedReader.readLine())!= null) {
                        address += line;
                        address +="\n\n";
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return address;
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
            TextView t  =(TextView)findViewById(R.id.PersonAddress);
            t.setText(address);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }





}
