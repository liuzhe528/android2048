package com.liuzhe.game.service;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;

import com.liuzhe.game.config.Config;
import com.liuzhe.game.dao.GameDao;
import com.liuzhe.game.domain.Card;
import com.liuzhe.game.domain.MainActivity;

public class GameService {
	private Card[][] cardMap = new Card[Config.LINES][Config.LINES];
	private List<Point> emptyPoints = new ArrayList<Point>();
	private Context context;
	private GameDao gameDao;
	private static int best = 0;

	public GameService(Context context) {
		this.context = context;
		this.gameDao = new GameDao(context);
	}

	public Card[][] addCards() {
		Card c;
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				c = new Card(context);
				c.setNum(0);
				cardMap[x][y] = c;
			}
		}
		best = gameDao.find();
		return cardMap;
	}

	public void addRandomNum() {
		emptyPoints.clear();
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				if (cardMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));
		cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
		if (MainActivity.getMainActivity() != null) {
			MainActivity.getMainActivity().getAnimLayer()
					.createScaleToNewCard(cardMap[p.x][p.y]);
		}
	}

	/*
	 * 手指向上滑动
	 */
	public void moveUp() {
		// 标记是否发生移动
		boolean merge = false;
		for (int x = 0; x < Config.LINES; x++) {
			for (int y = 0; y < Config.LINES; y++) {
				for (int y1 = y + 1; y1 < Config.LINES; y1++) {
					if (cardMap[x][y1].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x][y1],
											cardMap[x][y], x, x, y1, y);
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							y--;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1])) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x][y1],
											cardMap[x][y], x, x, y1, y);
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							MainActivity.getMainActivity().getAnimLayer()
									.createScaleToMergeCard(cardMap[x][y]);
							cardMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	/*
	 * 手指向下滑动
	 */
	public void moveDown() {
		// 标记是否发生移动
		boolean merge = false;
		for (int x = 0; x < Config.LINES; x++) {
			for (int y = Config.LINES - 1; y >= 0; y--) {
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardMap[x][y1].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x][y1],
											cardMap[x][y], x, x, y1, y);
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							y++;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1])) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x][y1],
											cardMap[x][y], x, x, y1, y);
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							MainActivity.getMainActivity().getAnimLayer()
									.createScaleToMergeCard(cardMap[x][y]);
							cardMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	/*
	 * 手指向右移动
	 */
	public void moveRight() {
		// 标记是否发生移动
		boolean merge = false;
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = Config.LINES - 1; x >= 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardMap[x1][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x1][y],
											cardMap[x][y], x1, x, y, y);
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							x++;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x1][y])) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x1][y],
											cardMap[x][y], x1, x, y, y);
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							MainActivity.getMainActivity().getAnimLayer()
									.createScaleToMergeCard(cardMap[x][y]);
							cardMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	/*
	 * 手指向左移动
	 */
	public void moveLeft() {
		// 标记是否发生移动
		boolean merge = false;
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				for (int x1 = x + 1; x1 < Config.LINES; x1++) {
					if (cardMap[x1][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x1][y],
											cardMap[x][y], x1, x, y, y);
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							x--;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x1][y])) {
							MainActivity
									.getMainActivity()
									.getAnimLayer()
									.createMoveAnim(cardMap[x1][y],
											cardMap[x][y], x1, x, y, y);
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							MainActivity.getMainActivity().getAnimLayer()
									.createScaleToMergeCard(cardMap[x][y]);
							cardMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	/*
	 * 判断游戏是否结束
	 */
	public void checkComplete() {
		boolean complete = true;
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				if (cardMap[x][y].getNum() <= 0
						|| (x > 0 && cardMap[x][y].equals(cardMap[x - 1][y]) || (y > 0 && cardMap[x][y]
								.equals(cardMap[x][y - 1])))) {
					complete = false;
					break;
				}
			}
			if (!complete) {
				break;
			}
		}
		if (complete) {
			int score = MainActivity.getMainActivity().getScore();
			if (best < score) {
				best = score;
				gameDao.update(best);
			}
			new AlertDialog.Builder(context)
					.setTitle("Alert")
					.setMessage("Game over\nClick Restart to restart")
					.setPositiveButton("Restart",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									startGame();
								}
							}).show();
		}
	}

	public void startGame() {
		if (MainActivity.getMainActivity() != null) {
			MainActivity.getMainActivity().clearScore();
			MainActivity.getMainActivity().showBestRecord();
		}
		for (int y = 0; y < Config.LINES; y++) {
			for (int x = 0; x < Config.LINES; x++) {
				cardMap[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	public static int getBest() {
		return best;
	}
}
