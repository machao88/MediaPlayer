package com.machao.mediaplayer;



import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer mediaplayer;
	private SurfaceView sv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sv = (SurfaceView)findViewById(R.id.sv);
		sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		mediaplayer = new MediaPlayer();
		
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		
		mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mediaplayer.start();
			}
		});
		
		
	}
	
	public void play(View view){
		
		try {
			mediaplayer.setDisplay(sv.getHolder());
			mediaplayer.setDataSource("/storage/extSdCard/mp3/渡情.flv");
//			mediaplayer.setLooping(true);
			mediaplayer.prepareAsync();
			mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.start();
				}
			});
			
			System.out.println("播放OK！");
		} catch (Exception e) {
			System.out.println("播放出错！");
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaplayer.stop();
		mediaplayer.release();
	}



}
