package com.liuzhe.game.domain;

import com.liuzhe.game.config.Config;
import com.liuzhe.game.service.GameService;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

@SuppressLint("ClickableViewAccessibility")
public class GameView extends GridLayout {
	private GameService gameService;

	public GameView(Context context) {
		super(context);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}

	private void initGameView() {
		this.setColumnCount(Config.LINES);
		this.setBackgroundColor(0xffbbada0);
		this.setOnTouchListener(new View.OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;
					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							moveLeft();
						} else if (offsetX > 5) {
							moveRight();
						}
					} else {
						if (offsetY < -5) {
							moveUp();
						} else if (offsetY > 5) {
							moveDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Config.CARD_WIDTH = (Math.min(w, h) - 10) / Config.LINES;
		gameService = new GameService(getContext());
		Card[][] cardMap = gameService.addCards();
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				addView(cardMap[x][y], Config.CARD_WIDTH, Config.CARD_WIDTH);
			}
		}
		gameService.startGame();
	}

	/*
	 * 手指向上滑动
	 */
	private void moveUp() {
		gameService.moveUp();
	}

	/*
	 * 手指向下滑动
	 */
	private void moveDown() {
		gameService.moveDown();
	}

	/*
	 * 手指向右移动
	 */
	private void moveRight() {
		gameService.moveRight();
	}

	/*
	 * 手指向左移动
	 */
	private void moveLeft() {
		gameService.moveLeft();
	}
}