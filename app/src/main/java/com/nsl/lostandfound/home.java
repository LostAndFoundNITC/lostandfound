/**
*\brief This is the Home file.This Code will be invoked when User Logs-In .This is the first page of the application and displays all the recent posts.
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
/**\class class with name home is being created here.home extends MainActivity and implements NavigationView.OnNavigationItemSelectedListener
    inorder to be an subclass of the class Activity and NavigationView.
*/
public class home extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener {


/**\fn onCreate function...
	*\brief Shows the activity on the screen on which all the recent posts are shown.
	*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




            WebView w;
               /**
	*Below condition detects if internet is active on the device
	*/
        if (!DetectConnection.checkInternetConnection(this)) {
           /**
                            *If internet is not active ,'No Internet' message is shown
                            */
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_LONG).show();
        } else {
            w = (WebView) findViewById(R.id.recent);
            w.setWebViewClient(new WebViewClient());

            w.clearCache(true);
            w.clearHistory();
              /**
                    *If internet is working ,All the recent posts are shown.
                    */
            w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/recent.php");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         /**
                    * Inflate the menu; this adds items to the action bar if it is present.
                    */
        getMenuInflater().inflate(R.menu.home, menu);
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



   
     
     /**\fn onBackPressed function
	*\brief Handles the action when back button is pressed i.e. redirects to Dashboard(Home Activity)
	*/
    @Override
    public void onBackPressed() {

// make sure you have this outcommented
// super.onBackPressed();
   /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


/**\fn onCreateOptionsMenu function
	*\brief Handles the action when  a perticular option from navigation drawer is selected.
	*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       /**
                    *  Handles navigation view item clicks here.
                    */
        
        int id = item.getItemId();
        Bundle extras = getIntent().getExtras();
        String name= extras.getString("name");
        String email= extras.getString("email");
        //String photo = extras.getString("photo");
        if (id == R.id.lost) {
          /**
                    * Handle the lost action
                    */
    
           Intent intent = new Intent(this, Mislayer.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
          // Handle the lost action
        } else if (id == R.id.found) {
        /**
                    * Handle the found action
                    */
            Intent intent = new Intent(this, Finder.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.user_posts) {
            /**
                    * Handle the My Posts action
                    */

            Intent intent = new Intent(this, MyPost.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        } else if (id == R.id.user_profile) {
          /**
                    * Handle the Profile action
                    */
            Intent intent = new Intent(this, UserProfile.class);
            intent.putExtra("name",name);
           intent.putExtra("email",email);
            startActivity(intent);
        }else if (id == R.id.home) {
            Intent intent = new Intent(this, home.class);
            //intent.putExtra("photo",photo);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);
        }
        else if (id == R.id.logout) {
         /**
                    * Handle the Logout action
                    */
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
                        Intent intent = new Intent(home.this , MainActivity.class);
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
