
/**
*\brief This is the CheckClaim file.This Code will be invoked when 'check claims' button is pressed
*It is in the package com.nsl.lostandfound;
*/

package com.nsl.lostandfound;
/**
*Below are all imported classes/interfaces.
*/
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
/**\class class with name CheckClaim is being created here.CheckClaim extends AppCompatActivity
    inorder to be an subclass of the class Activity.
*/
public class CheckClaim extends AppCompatActivity {

     /**\fn onCreate function...
	*\brief Shows the activity on the screen on which all the claim of the current user are shown.
	*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_claim);
        Bundle extras = getIntent().getExtras();
        /**
	*Email id of the currrent user is fetched.
	*/
        String email= extras.getString("email");
       WebView w;
        w = (WebView) findViewById(R.id.checkclaim);
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

            w.clearCache(true);
            w.clearHistory();
                 /**
                    *If internet is working ,All the claims of current user are shown.
                    */
            w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/CheckClaim.php?email="+email);
        }
    }
}
