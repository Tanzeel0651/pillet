package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class medindia extends AppCompatActivity {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public String query1;
    public String result1;
    public Document doc1;
    public String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medindia);

        WebView webView = (WebView) findViewById(R.id.medindia);
        Intent intent = getIntent();
        query1 = intent.getExtras().getString("string");
        Log.d("String", query1);
        try {
            result1 = new background().execute(query1, null, null).get();
        }catch (Exception e){
            Log.d("Error", "Error from WebActivity");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(result1);
    }

    public class background extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String searchURL1 = GOOGLE_SEARCH_URL + "?q=" + query1 + "medindia.net" + "&num=" + 1;
            try {
                doc1 = Jsoup.connect(searchURL1).get();
            } catch (IOException e) {
                Log.d("Error 1", "");
            }
            Elements results = doc1.select("h3.r > a");
            //Element results = doc.getElementById("cite");
            for (Element result : results) {
                String linkHref = result.attr("href");
                URL = linkHref/*.substring(6, linkHref.indexOf("&"))*/;
                Log.d("URL: ", URL);
            }
            return URL;
        }
    }
}
