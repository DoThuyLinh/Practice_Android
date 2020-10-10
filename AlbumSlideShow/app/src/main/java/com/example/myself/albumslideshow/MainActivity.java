package com.example.myself.albumslideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    CheckBox chkTuDong;
    ImageButton btnPrevious, btnNext;
    int currentPosition = 0;
    ArrayList<String> album;
    Timer timer = null;
    TimerTask timerTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXemHinhKeTiep();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXemHinhTruoc();
            }
        });

        chkTuDong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    btnPrevious.setEnabled(false);
                    btnNext.setEnabled(false);

                    xuLyTuDongChay();
                }
                else {
                    btnPrevious.setEnabled(true);
                    btnNext.setEnabled(true);
                    if(timer != null)
                        timer.cancel();
                }
            }
        });
    }

    private void xuLyTuDongChay() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentPosition++;
                        if(currentPosition == album.size()-1){
                            currentPosition = 0;
                        }
                        ImageTask task = new ImageTask();
                        task.execute(album.get(currentPosition));

                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,0,5000);
    }

    private void xuLyXemHinhTruoc() {
        currentPosition--;
        if(currentPosition == -1){
            currentPosition = album.size()-1;
        }
        ImageTask task = new ImageTask();
        task.execute(album.get(currentPosition));
    }

    private void xuLyXemHinhKeTiep() {
        currentPosition++;
        if(currentPosition == album.size()-1){
            currentPosition = 0;
        }
        ImageTask task = new ImageTask();
        task.execute(album.get(currentPosition));
    }

    private void addControls() {
        imgHinh = findViewById(R.id.imgHinh);
        chkTuDong = findViewById(R.id.chkTuDong);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        album = new ArrayList<String>();
        album.add("https://photo-3-baomoi.zadn.vn/w1000_r1/2020_02_29_329_34142183/d372ffbcdcff35a16cee.jpg");
        album.add("https://i.pinimg.com/originals/e4/23/75/e42375f727a7ced27c5e090bcb952f03.jpg");
        album.add("https://photo-1-baomoi.zadn.vn/w700_r1/2020_03_16_240_34328475/8b09945a85196c473508.jpg");
        album.add("https://i.pinimg.com/originals/ce/d6/ac/ced6ac3edeef82ee9a79d2c3e1b7d633.png");
        album.add("https://pbs.twimg.com/media/C27LsOyUkAAyg2p.jpg");
        album.add("https://images.vov.vn/w720/uploaded/krb8sl5hrwuly8uzveukg/2016_04_01/song_rntv.png");
        album.add("https://uploads-ssl.webflow.com/5d9ff05c2ca4f64e9f7ea570/5de0d852d4143494cad1c0e0_trai-dep-6-mui-3.jpg");
        album.add("https://media.ngoisao.vn/resize_580/news/2016/04/04/trao-luu-trai-dep-bung-6-mui-vao-bep-ngoisao.vn.JPG");
        album.add("https://www.elleman.vn/wp-content/uploads/2017/10/27/co-bung-6-mui-elle-man-18.jpg");
        album.add("https://www.elleman.vn/wp-content/uploads/2017/10/26/co-bung-6-mui-elle-man-1.jpg");
        album.add("https://www.elleman.vn/wp-content/uploads/2017/10/29/co-bung-6-mui-elle-man-22.jpg");
        album.add("https://www.elleman.vn/wp-content/uploads/2017/11/07/co-bung-6-mui-channing-tatum-elle-man-475x356.jpg");
        album.add("https://www.elleman.vn/wp-content/uploads/2017/11/07/co-bung-6-mui-chris-evan-elle-man-475x588.jpg");
        album.add("http://www.elleman.vn/wp-content/uploads/2017/11/02/co-bung-6-mui-elle-6.jpg");
        album.add("http://www.elleman.vn/wp-content/uploads/2017/11/02/co-bung-6-mui-elle-2.jpg");

        ImageTask task = new ImageTask();
        task.execute(album.get(currentPosition));
    }
    class ImageTask extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                String link = strings[0];
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(link).getContent());
                return bitmap;
            }catch (Exception ex){
                Log.e("LOI",ex.toString());
            }
            return null;
        }
    }
}
