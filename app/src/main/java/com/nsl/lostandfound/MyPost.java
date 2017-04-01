/**
*\brief This is the MyPost file.This Code will be invoked when 'My Posts' button is pressed
*It is in the package com.nsl.lostandfound;
*/

package com.nsl.lostandfound;
/**
*Below are all imported classes/interfaces.
*/
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static android.R.attr.name;
/**\class class with name MyPost is being created here.Mypost extends MainActivity and  implements NavigationView.OnNavigationItemSelectedListener
    inorder to be an subclass of the class Activity and NavigationView.
*/
public class MyPost extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**\fn onCreate function...
	*\brief Shows the activity on the screen on which all the posts of the current user are shown.
	*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
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
         /**
	*Email id of the currrent user is fetched.
	*/
        String email = extras.getString("email");

        WebView w;
        w = (WebView) findViewById(R.id.MyPost);
        w.setWebViewClient(new WebViewClient());
        /**
	*Below condition detects if internet is active on the device
	*/
        if (!DetectConnection.checkInternetConnection(this)) {
                 /**
                            *If internet is not active ,'No Internet' message is shown
                            */
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_LONG).show();

        } else {
                         /**
                    *If internet is working ,All the claims of current user are shown.
                    */
            w.clearCache(true);
            w.clearHistory();
            w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/mypost.php?email='" + email + "'");
        }



    }
/**\fn onBackPressed function
	*\brief Handles the action when back button is pressed i.e. redirects to Dashboard(Home Activity)
	*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this, home.class);
            //intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
            finish();
        }
    }
                /**\fn onCreateOptionsMenu function
	*\brief Handles the action when  a perticular option from navigation drawer is selected.
	*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     /**
                    * Inflate the menu; this adds items to the action bar if it is present.
                    */
       
        getMenuInflater().inflate(R.menu.my_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();

          
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
String email;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
          /**
                    *  Handle navigation view item clicks here.
                    */
       
        int id = item.getItemId();
        Bundle extras = getIntent().getExtras();
        String name= extras.getString("name");
        email= extras.getString("email");
        if (id == R.id.lost) {
            Intent intent = new Intent(this, Mislayer.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
               /**
                    * Handle the lost action
                    */
    
        }else if (id == R.id.home) {
            Intent intent = new Intent(this, home.class);
            //intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        }
          /**
                    * Handle the found action
                    */
        else if (id == R.id.found) {
            Intent intent = new Intent(this, Finder.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } 
            /**
                    * Handle the My Posts action
                    */
        else if (id == R.id.user_posts) {
            Intent intent = new Intent(this, MyPost.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } 
          /**
                    * Handle the Profile action
                    */
        else if (id == R.id.user_profile) {
            Intent intent = new Intent(this, UserProfile.class);
            //intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } 
          /**
                    * Handle the Logout action
                    */
        else if (id == R.id.logout) {
            signOut();
            revokeAccess();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
      /**\fn signOut function
	*\brief Handles the action when Logout from navigation drawer is selected.
	*/
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Intent intent = new Intent(MyPost.this , MainActivity.class);
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
}
