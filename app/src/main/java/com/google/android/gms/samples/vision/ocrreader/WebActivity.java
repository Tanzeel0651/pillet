package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class WebActivity extends AppCompatActivity {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    private Document doc;
    public String query = "";
    public String URL = "";
    public String result = "";
    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        query = intent.getExtras().getString("string");
        try {
            result = new background().execute(query, null, null).get();
        }catch (Exception e){
            Log.d("Error", "Error from WebActivity");
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(result);

    }

    public class background extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... voids) {
            String searchURL = GOOGLE_SEARCH_URL + "?q=" + query + "practo" + "&num=" + 1;
            try {
                doc = Jsoup.connect(searchURL).get();
            } catch (IOException e) {
                Log.d("Error 1", "");
            }
            Elements results = doc.select("h3.r > a");
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