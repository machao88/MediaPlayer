package com.machao.mediaplayer;



import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer mediaplayer;
	private SurfaceView sv;
	private static int progress = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sv = (SurfaceView)findViewById(R.id.sv);
		sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sv.getHolder().addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				if(mediaplayer != null && mediaplayer.isPlaying()){
					progress = mediaplayer.getCurrentPosition();
					mediaplayer.stop();
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				// TODO Auto-generated method stub
				if(progress > 0) {
					//play(null);
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
			mediaplayer.setDataSource("/storage/extSdCard/mp3/渡情.flv");
			mediaplayer.setDisplay(sv.getHolder());
//			mediaplayer.setLooping(true);
			mediaplayer.prepareAsync();
			mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mp.start();
					mp.seekTo(progress);
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
		progress = 0;
	}



}
