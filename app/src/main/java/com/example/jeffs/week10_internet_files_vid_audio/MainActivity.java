package com.example.jeffs.week10_internet_files_vid_audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    int count=0;
    String url = "rtsp://mpv.cdn3.bigCDN.com:554/bigCDN/definst/mp4:bigbuckbunnyiphone_400.mp4";
    MediaPlayer mediaPlayer = new MediaPlayer();
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.tv_1);
        vv=(VideoView)findViewById(R.id.vid_view);
    }

    public void fnctCount(View view) {
        tv.setText(""+count++);
    }

    public void streamVid(View view) {
        Uri uri=Uri.parse(url);
        vv.setVideoURI(uri);
        vv.start();
    }

    public void streamSound(View view) throws Exception{

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mediaPlayer.start();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();

    }
}
