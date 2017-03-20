package com.nsl.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Finder extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.finder, menu);
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
        int id = item.getItemId();
        Bundle extras = getIntent().getExtras();
        String name= extras.getString("name");
        String email= extras.getString("email");
        // String photo = extras.getString("photo");
        if (id == R.id.lost) {
            Intent intent = new Intent(this, Mislayer.class);
            // intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
            // Handle the lost action
        } else if (id == R.id.found) {
            Intent intent = new Intent(this, Finder.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.user_posts) {
            Intent intent = new Intent(this, MyPost.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.user_profile) {
            Intent intent = new Intent(this, UserProfile.class);
            //intent.putExtra("photo",photo);
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
                        Intent intent = new Intent(Finder.this , MainActivity.class);
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

    public void report(View view)
    {
        Intent intent = new Intent(Finder.this, FReport.class);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        String name= extras.getString("name");
        //Toast.makeText(this,email+name,Toast.LENGTH_LONG).show();
        intent.putExtra("name",name);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    public void search(View view){
        Intent intent =new Intent(this,SearchLoss.class);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        String name= extras.getString("name");
       // Toast.makeText(this,email+name,Toast.LENGTH_LONG).show();
        intent.putExtra("name",name);
        intent.putExtra("email","hello");
        startActivity(intent);

    }

    public void checkClaim(View view){
        Intent intent = new Intent(this,CheckClaim.class);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        intent.putExtra("email",email);
        startActivity(intent);
    }
}
