package cn.com.wanyueliang.exoplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import cn.com.wanyueliang.exolibrary.listener.OnCompletionListener;
import cn.com.wanyueliang.exolibrary.listener.OnPreparedListener;
import cn.com.wanyueliang.exolibrary.ui.widget.VideoView;
import cn.com.wanyueliang.exoplayer.videoControl.SimpleVideoControl;

public class MainActivity extends AppCompatActivity {
    String url1 = "https://bj.bcebos.com/v1/tomato-dev/35b4f5af-e140-4fdf-9b43-fc9251553065/film/a4bd1148-6443-4092-b0b7-8e2c496fb11b.mp4?s=17235125";
    String url2 = "https://bj.bcebos.com/v1/tomato-dev/1613a9be-2567-11e7-b1b0-001e4f166239/film/a7c8a95a-5d33-436c-8380-9cde4bf66a6b.mp4?s=17233995";

    /*view*/
    private VideoView videoView;
    private AppCompatButton btnPlay;
    /*data*/
    private Context mContext;
    private String TAG = getClass().getSimpleName();
    private String playUrl;
    private SimpleVideoControl simpleVideoControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        findView();
        initView();
        initListener();

    }

    private void initView() {
        playUrl = url1;

        simpleVideoControl = new SimpleVideoControl(mContext);
        videoView.setControls(simpleVideoControl);
        simpleVideoControl.setTitle("歌曲");

    }

    private void initListener() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playUrl.equals(url1)) {
                    playUrl = url2;
                    videoView.setVideoURI(Uri.parse(url2));
                    videoView.start();
                } else {
                    playUrl = url1;
                    videoView.setVideoURI(Uri.parse(url1));
                    videoView.start();
                }
            }
        });

        videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {

                Log.i(TAG, "onPrepared: ");
            }
        });

        videoView.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion() {
                videoView.restart();
            }
        });
    }

    private void findView() {

        videoView = (VideoView) findViewById(R.id.video_view);
        btnPlay = (AppCompatButton) findViewById(R.id.btn_play);

    }
}
