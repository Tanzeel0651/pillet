package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    float x1,y1,x2,y2;
    TextView textView1, textView2, textView3, textView4, textView5;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("list");
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        for(int i=0;i<list.size();i++){
            try{
                if(i==0){
                    textView1.setText(list.get(0));
                }
                if(i==1){
                    textView2.setText(list.get(1));
                    if(textView2.getText().toString().equals(textView1.getText().toString())){
                        textView2.setText("");
                    }
                }
                if(i==2){
                    textView3.setText(list.get(2));
                    if(textView3.getText().toString().equals(textView2.getText().toString()) || textView3.getText().toString().equals(textView1.getText().toString())){
                        textView3.setText("");
                    }
                }
                if(i==3){
                    textView4.setText(list.get(3));
                    if(textView4.getText().toString().equals(textView3.getText().toString()) || textView4.getText().toString().equals(textView2.getText().toString()) || textView4.getText().toString().equals(textView1.getText().toString()) ){
                        textView4.setText("");
                    }
                }
                if(i==4){
                    textView5.setText(list.get(4));
                    if(textView5.getText().toString().equals(textView4.getText().toString()) || textView5.getText().toString().equals(textView3.getText().toString()) || textView5.getText().toString().equals(textView2.getText().toString()) || textView5.getText().toString().equals(textView1.getText().toString())) {
                        textView5.setText("");
                    }
                }
            }catch (Exception e){
                continue;
            }
        }
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, WebActivity.class);
                intent.putExtra("string", textView1.getText().toString());
                startActivity(intent);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, WebActivity.class);
                intent.putExtra("string", textView2.getText().toString());
                startActivity(intent);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, WebActivity.class);
                intent.putExtra("string", textView3.getText().toString());
                startActivity(intent);
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, WebActivity.class);
                intent.putExtra("string", textView4.getText().toString());
                startActivity(intent);
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, WebActivity.class);
                intent.putExtra("string", textView5.getText().toString());
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent touchEvent) {
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 > x2){
                    Intent intent = new Intent(History.this, OcrCaptureActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }
    @Override
    public void onResume(){
        super.onResume();

    }
}
