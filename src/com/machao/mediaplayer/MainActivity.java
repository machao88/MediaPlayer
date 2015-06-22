package com.machao.mediaplayer;



import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer mediaplayer;
	SoundPool soundPool;
	private int soundid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundid = soundPool.load(this, R.raw.ring, 1);
	}

	public void click(View view){
		soundPool.play(soundid, 1.0f, 1.0f, 0, 0, 1.0f);
	}



}
