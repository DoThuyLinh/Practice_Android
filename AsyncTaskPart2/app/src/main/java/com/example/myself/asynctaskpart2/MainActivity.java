package com.example.myself.asynctaskpart2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ImageView imgDownload;
    ProgressDialog progressDialog;

    String link = "https://photo-3-baomoi.zadn.vn/w1000_r1/2020_02_29_329_34142183/d372ffbcdcff35a16cee.jpg";
    String link2 = "https://i.pinimg.com/originals/e4/23/75/e42375f727a7ced27c5e090bcb952f03.jpg";
    String link3 = "https://photo-1-baomoi.zadn.vn/w700_r1/2020_03_16_240_34328475/8b09945a85196c473508.jpg";
    String link4 = "https://i.pinimg.com/originals/ce/d6/ac/ced6ac3edeef82ee9a79d2c3e1b7d633.png";
    String link5 = "https://pbs.twimg.com/media/C27LsOyUkAAyg2p.jpg";

    ArrayList<String> arrayList;

    Random rd = new Random();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    private void downloadImage() {
        int n = rd.nextInt(5);

        ImageTask imageTask = new ImageTask();
        imageTask.execute(arrayList.get(n));
    }

    private void addControls() {
        btnDownload = findViewById(R.id.btnDownload);
        imgDownload = findViewById(R.id.imgDownload);
        arrayList = new ArrayList<String>();
        arrayList.add(link);
        arrayList.add(link2);
        arrayList.add(link3);
        arrayList.add(link4);
        arrayList.add(link5);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Thong bao");
        progressDialog.setMessage("Dang tai vui long cho");
        progressDialog.setCanceledOnTouchOutside(false);
    }
    class ImageTask extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgDownload.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                String link = strings[0];
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(link).getContent());
                return bitmap;
            }catch (Exception ex){
                Log.e("LOI",ex.toString());
            }
            return null;
        }
    }
}
