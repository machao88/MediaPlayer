package com.machao.mediaplayer;



import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;

public class MainActivity extends Activity {
	MediaPlayer mediaplayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mediaplayer = new MediaPlayer();
		mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaplayer.setDataSource("/mnt/sdcard/新娘不是我.mp3");
			mediaplayer.setLooping(true);
			mediaplayer.prepareAsync();
			
			mediaplayer.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					mediaplayer.start();
				}
			});
			
			System.out.println("播放OK！");
			
		} catch (Exception e) {
			System.out.println("播放出错！");
			e.printStackTrace();
		}
		
		/*			mediaplayer.setOnCompletionListener(new OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			mediaplayer.start();
		}
	});*/
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaplayer.stop();
		mediaplayer.release();
	}



}
