package com.example.newsappyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Readmore extends AppCompatActivity {
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmore);
        //action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Full News");

      //  Home=findViewById(R.id.BtnBackToHome);

        //take url from the detail activity
        Intent i=getIntent();
        String weburl=i.getStringExtra("URL");

        WebView webView=findViewById(R.id.webView);
        webView.loadUrl(weburl);
        webView.setWebViewClient(new WebViewController());

//        Home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent i=new Intent(Intent.ACTION_VIEW);
//                Intent i=new Intent(Readmore.this,MainActivity.class);
//              //  i.putExtra("URL",url);
//                // i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });
    }
}