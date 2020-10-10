package com.example.myself.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtPercent;
    EditText txtSoButton;
    Button btnVeButton;
    ProgressBar progressBarPercent;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnVeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyVeButtonTime();
            }
        });
    }

    private void XuLyVeButtonTime() {
        int n = Integer.parseInt(txtSoButton.getText().toString());
        ButtonTask task = new ButtonTask();
        task.execute(n);
    }

    private void addControls() {
        txtPercent = findViewById(R.id.txtPercent);
        txtSoButton = findViewById(R.id.txtSoButton);
        btnVeButton = findViewById(R.id.btnVeButton);
        progressBarPercent = findViewById(R.id.progressBarPercent);
        linearLayout = findViewById(R.id.layoutButton);
    }

    class ButtonTask extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            int n =integers[0];
            for (int i=0; i<n;i++){
                int percent = i*100/n;
                int value = i+1;
                publishProgress(percent,value);
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            linearLayout.removeAllViews();
            progressBarPercent.setProgress(0);
            txtPercent.setText("");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarPercent.setProgress(100);
            txtPercent.setText(100+"");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int value = values[1];
            int percent = values[0];
            progressBarPercent.setProgress(percent);
            txtPercent.setText(percent+"");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            Button btn = new Button(MainActivity.this);
            btn.setLayoutParams(params);
            btn.setText(value+" ");

            linearLayout.addView(btn);
        }
    }

}
