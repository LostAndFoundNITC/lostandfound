/**
*\brief This is the Search file.This Code will be invoked when User clicks the search functionality in the Finder activity .This provides an interface for user to search lost items.
*It is in the package com.nsl.lostandfound;
*/
package com.nsl.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
/**\class class with name SearchLoss is being created here.Search extends AppCompatActivity .
*/
public class SearchLoss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_loss);
        WebView w = (WebView)findViewById(R.id.idFinder);
        w.setWebViewClient(new WebViewClient());
  /**
	*Email id of the currrent user is fetched.
	*/
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
      
       // String name= extras.getString("name");
 /**
	*Below condition detects if internet is active on the device
	*/
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_LONG).show();

        } else {

            w.clearCache(true);
            w.clearHistory();
             /**
                    *If internet is working ,All the claims of current user are shown.
                    */
            w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/search.php?email='"+email+"'");

        }

    }
}
