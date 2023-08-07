package com.example.newsappyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.http.Url;

public class NewsDetailActivity extends AppCompatActivity {
    String title,desc,content,imageURL,url;
    private TextView titleTV,subDescTV,contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        //action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Summary of News");
        //
        title =getIntent().getStringExtra("title");
        desc =getIntent().getStringExtra("desc");
        content =getIntent().getStringExtra("content");
        imageURL =getIntent().getStringExtra("image");
        url =getIntent().getStringExtra("url");
        //
        titleTV=findViewById(R.id.idTVTitile);
        subDescTV=findViewById(R.id.idTVSubDesc);
        contentTV=findViewById(R.id.idTVContent);
        newsIV=findViewById(R.id.idIVNews);
        readNewsBtn=findViewById(R.id.idbtnReadNews);
        //
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(newsIV);
        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewsDetailActivity.this, "Opening the Full News", Toast.LENGTH_SHORT).show();
               // Intent i=new Intent(Intent.ACTION_VIEW);
                Intent i=new Intent(NewsDetailActivity.this,Readmore.class);
                i.putExtra("URL",url);
               // i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}