package com.example.newsappyc;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRvAdapter.CategoryClickInterface {
   // Api Key  9e715047b60c497ba783d3aa0f483983
    private RecyclerView newsRV,categoryRV;
    ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRvModal> categoryRvModalArrayList;
    private CategoryRvAdapter categoryRvAdapter;
    private  NewsRvAdapter newsRvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //action bar
        //getSupportActionBar().hide();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("News Feeds");
        //
        newsRV=findViewById(R.id.idnewss);
        categoryRV=findViewById(R.id.idrCategrious);
        loadingPB=findViewById(R.id.idPBLoader);
        articlesArrayList=new ArrayList<>();
        categoryRvModalArrayList=new ArrayList<>();
        newsRvAdapter=new NewsRvAdapter(articlesArrayList,this);
        categoryRvAdapter=new CategoryRvAdapter(categoryRvModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRvAdapter);
        categoryRV.setAdapter(categoryRvAdapter);
        getCategories();//toget categories
        getNews("All");//by default
        newsRvAdapter.notifyDataSetChanged();


//        ///Share app
//        StrictMode.VmPolicy.Builder builder =
//                new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        if(Build.VERSION.SDK_INT>=24){
//            try{
//                java.lang.reflect.Method m =
//                        StrictMode.class.getMethod(
//                                "disableDeathOnFileUriExposure");
//                m.invoke(null);
//            }
//            catch(Exception e){
//              //  showMessage(e.toString());
//            }
//        }
//        ////////
    }
    //Exit button
    ////share method
//    private void shareApplication() {
//        android.content.pm.ApplicationInfo app =
//                getApplicationContext().getApplicationInfo();
//        String filePath = app.sourceDir;
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//        java.io.File originalApk = new java.io.File(filePath);
//        try {
//            java.io.File tempFile = new java.io.File(getExternalCacheDir() + "/ExtractedApk");
//            if (!tempFile.isDirectory())
//                if (!tempFile.mkdirs())
//                    return;
//            tempFile = new java.io.File(tempFile.getPath() + "/" +
//                    "export.apk");
//            if (!tempFile.exists())
//            {
//                try{
//                    if (!tempFile.createNewFile()) {
//                        return; }
//                }
//                catch (java.io.IOException e){}
//            }
//            java.io.InputStream in = new java.io.FileInputStream (originalApk);
//            java.io.OutputStream out = new java.io.FileOutputStream(tempFile);
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = in.read(buf)) > 0) {
//                out.write(buf, 0, len);
//            }
//            in.close();
//            out.close();
//            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
//            startActivity(Intent.createChooser(intent, "Share app via"));
//        }
//        catch (java.io.IOException e)
//        {
//          //  showMessage(e.toString());
//        }
//    }
    ///////////////


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure,You want to exit ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // finish();
                        MainActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

//        super.onBackPressed();
    }

    //menu code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id=item.getItemId();
        if (item_id==R.id.Rate){
            Toast.makeText(this,"Opening Developer Website",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(Intent.ACTION_VIEW);
            //Intent i=new Intent(NewsDetailActivity.this,Readmore.class);
            //i.putExtra("URL",url);
            i.setData(Uri.parse("https://yashchutake.netlify.app/"));
            startActivity(i);
        }
        else if (item_id==R.id.share){

            Toast.makeText(this,"Sharing the app",Toast.LENGTH_SHORT).show();
//            //Sharing app from app
//            ApplicationInfo api=getApplicationContext().getApplicationInfo();
//            String apkpath=api.sourceDir;
//
//            Intent intent=new Intent(Intent.ACTION_SEND);
//            intent.setType("application/vnd.android.package-archive");
//
//            intent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(new File(apkpath)));
//            startActivity(Intent.createChooser(intent,"Share the app using..."));


//            shareApplication();




            //
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);

            // type of the content to be shared
            sharingIntent.setType("text/plain");

            // Body of the content
            String shareBody = "Read latest news from your favorite sources\n" +
                    "   Yc News is a news app that selects latest and best news from multiple national and international sources and summarises them to present in a short and crisp 60 words or less format, personalized for you,All summarised stories contain only headlines and facts, no opinions, to help you stay informed of the current affairs. Whether it's the latest government policies or shakeups in bollywood, we get them covered and delivered super fast! Get updated with the latest news...!\n" +
                    "Download From Below Link :-\n" +
                    "https://yashchutake.netlify.app/#portfolio";

            // subject of the content. you can share anything
            String shareSubject = "line no 2";

            // passing body of the content
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

            // passing subject of the content
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        }
        else if (item_id==R.id.exit){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Are you sure,You want to exit ?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // finish();
                            MainActivity.super.onBackPressed();
                        }
                    })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
        return true;
    }

    //
    private void getCategories(){
        categoryRvModalArrayList.add(new CategoryRvModal("All","https://images.unsplash.com/photo-1504288145234-919e7bbc6d19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Entertainment","https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=772&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Science","https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
       // categoryRvModalArrayList.add(new CategoryRvModal("Sports","https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("General","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=872&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Business","https://images.unsplash.com/39/lIZrwvbeRuuzqOoWJUEn_Photoaday_CSD%20(1%20of%201)-5.jpg?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        //categoryRvModalArrayList.add(new CategoryRvModal("Entertainment","https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=772&q=80"));
        categoryRvModalArrayList.add(new CategoryRvModal("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=499&q=80"));
     //   categoryRvModalArrayList.add(new CategoryRvModal("Political","https://images.unsplash.com/photo-1555848965-141f2e58d1a9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1100&q=80"));

        categoryRvAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        // String categoryURL = "http://newsapi.org/v2/top-headlines?c..." + category + "&apiKey=Enter your api key";
        // String url = "http://newsapi.org/v2/top-headlines?c... your api key";

        String categoryURL ="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=9e715047b60c497ba783d3aa0f483983";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishAt&language=en&apiKey=9e715047b60c497ba783d3aa0f483983";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModel> call;
        if(category.equals("All")){
            call=retrofitAPI.getAllNews(url);
        }
        else{
            call=retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel=response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModel.getArticles();
                for (int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }
                //notify the adapter the data has been changed
                newsRvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get News", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onCategoryClick(int position) {
        String category=categoryRvModalArrayList.get(position).getCategory();
        getNews(category);

    }
}