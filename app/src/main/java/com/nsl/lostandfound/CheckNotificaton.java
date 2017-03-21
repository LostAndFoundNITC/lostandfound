package com.nsl.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class CheckNotificaton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_notificaton);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
        WebView w;
        w = (WebView) findViewById(R.id.checknotificaion);
        w.setWebViewClient(new WebViewClient());

        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_LONG).show();

        } else {
            w.clearCache(true);
            w.clearHistory();
            w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/CheckNotification.php?email="+email+"");

        }
        Log.e("url","http://andromeda.nitc.ac.in/~m150035ca/Web/CheckNotification.php?email="+email+"");
    }
}
