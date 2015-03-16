package com.liuzhe.game.domain;

import com.liuzhe.game.R;
import com.liuzhe.game.service.GameService;

import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {
	private TextView tv_bestRecord;
	private int score;
	private TextView tv_score;
	private static MainActivity mainActivity = null;
	private AnimLayer animLayer = null;
	public AnimLayer getAnimLayer() {
		return animLayer;
	}
	public MainActivity(){
		mainActivity = this;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_score = (TextView) findViewById(R.id.tv_score);
		animLayer = (AnimLayer) findViewById(R.id.animLayer);
		tv_bestRecord = (TextView) findViewById(R.id.bestRecord);
	}
	public void showBestRecord(){
		tv_bestRecord.setText(GameService.getBest()+"");
	}
	public void clearScore(){
		score = 0;
		showScore();
	}
	public void showScore() {
		tv_score.setText(score+"");
	}
	public void addScore(int s){
		score += s;
		showScore();
	}
	public int getScore(){
		return score;
	}
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
	
}
