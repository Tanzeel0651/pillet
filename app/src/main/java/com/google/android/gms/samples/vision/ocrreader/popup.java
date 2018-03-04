package com.google.android.gms.samples.vision.ocrreader;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class popup extends AppCompatActivity {
    String text;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .4));
        edt = (EditText) findViewById(R.id.symtom);
        edt.setHint("Enter Drug Name");
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText("");
            }
        });
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = edt.getText().toString();
                if(!text.equals("")) {
                    OcrCaptureActivity.list.add(text);
                    Intent intent = new Intent(popup.this, medindia.class);
                    intent.putExtra("string", text);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(popup.this, "Enter Drug Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
