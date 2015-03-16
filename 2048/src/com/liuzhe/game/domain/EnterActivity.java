package com.liuzhe.game.domain;

import com.liuzhe.game.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class EnterActivity extends Activity {
	private final int DELAY_TIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(EnterActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, DELAY_TIME);
	}

}
