package com.machao.mediaplayer;



import java.io.File;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnClickListener{
	MediaPlayer mediaplayer;
	private EditText et;
	private Button bt_play,bt_pause,bt_replay,bt_stop;
	private SeekBar sb;
	private boolean isPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText)findViewById(R.id.et);
		sb = (SeekBar)findViewById(R.id.sb);
		bt_play = (Button)findViewById(R.id.play);
		bt_pause = (Button)findViewById(R.id.pause);
		bt_replay = (Button)findViewById(R.id.replay);
		bt_stop = (Button)findViewById(R.id.stop);
		
		bt_play.setOnClickListener(this);
		bt_pause.setOnClickListener(this);
		bt_replay.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar sb1) {
				// TODO Auto-generated method stub
				int current = sb1.getProgress();
				if (mediaplayer != null && mediaplayer.isPlaying()){
					mediaplayer.seekTo(current);
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.play:
			play();
			break;
		case R.id.pause:
			pause();
			break;
		case R.id.replay:
			replay();
			break;
		case R.id.stop:
			stop();
			break;
		}
	}
	
	public void play(){
		
		String path = et.getText().toString().trim();
		File file = new File(path);
		if (file.exists() && file.length() > 0) {
			
			try {
				mediaplayer = new MediaPlayer();
				mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaplayer.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						bt_play.setEnabled(true);
					}
				});
				mediaplayer.setOnPreparedListener(new OnPreparedListener() {
					
					@Override
					public void onPrepared(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						int max = mediaplayer.getDuration();
						sb.setMax(max);
						
						new Thread(){
							public void run(){
								isPlaying = true;
								while (isPlaying){
									int position = mediaplayer.getCurrentPosition();
									sb.setProgress(position);
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}.start();
						
						
					}
				});
				
				mediaplayer.setOnErrorListener(new OnErrorListener() {
					
					@Override
					public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
						// Tbt_play.setEnabled(true);
						bt_play.setEnabled(true);
						return true;
					}
				});
				mediaplayer.setDataSource(path);
				mediaplayer.prepare();
				mediaplayer.start();
				System.out.println("播放OK！");
				bt_play.setEnabled(false);
			} catch (Exception e) {
				System.out.println("播放出错！");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("播放的文件不存在");
		}
		
	}
	public void pause(){
		if("暂停".equals(bt_pause.getText().toString().trim())){
			mediaplayer.pause();
			bt_pause.setText("继续");
		}
		else if (mediaplayer != null && mediaplayer.isPlaying() == false){
			mediaplayer.start();
			bt_pause.setText("暂停");
		}
	}
	public void replay(){
		if(mediaplayer != null && mediaplayer.isPlaying()){
			mediaplayer.seekTo(0);
			bt_pause.setText("暂停");
		}
		play();
	}

	public void stop(){
		if(mediaplayer != null && mediaplayer.isPlaying()){
			mediaplayer.stop();
			bt_play.setEnabled(true);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mediaplayer != null) {
			mediaplayer.stop();
			mediaplayer.release();
		}
	}
}
