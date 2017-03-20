package com.nsl.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class SearchLoss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_loss);
        WebView w = (WebView)findViewById(R.id.idFinder);
        Bundle extras = getIntent().getExtras();
        String email= extras.getString("email");
       // String name= extras.getString("name");
        w.loadUrl("http://andromeda.nitc.ac.in/~m150035ca/Web/search.php?email='"+email+"'");
    }
}
