package com.liuzhe.game.domain;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {
	private TextView label;
	private int num = 0;

	public Card(Context context) {
		super(context);
		label = new TextView(getContext());
		label.setTextSize(32);
		label.setBackgroundColor(0x33ffffff);
		label.setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);
		this.addView(label, lp);
	}

	public void setNum(int num) {
		this.num = num;
		label.setTextColor(0xff000000);
		if (num <= 0) {
			label.setText("");
		} else {
			label.setText(num + "");
		}
		switch (num) {
		case 0:
			label.setBackgroundColor(0x33ffffff);
			break;
		case 2:
			label.setBackgroundColor(0xffeee4da);
			break;
		case 4:
			label.setBackgroundColor(0xffede0c8);
			break;
		case 8:
			label.setBackgroundColor(0xfff2b179);
			label.setTextColor(0xffffffff);
			break;
		case 16:
			label.setBackgroundColor(0xfff59563);
			label.setTextColor(0xffffffff);
			break;
		case 32:
			label.setBackgroundColor(0xfff67c5f);
			label.setTextColor(0xffffffff);
			break;
		case 64:
			label.setBackgroundColor(0xfff65e3b);
			label.setTextColor(0xffffffff);
			break;
		case 128:
			label.setBackgroundColor(0xffedcf72);
			label.setTextColor(0xffffffff);
			break;
		case 256:
			label.setBackgroundColor(0xffedcc61);
			label.setTextColor(0xffffffff);
			break;
		case 512:
			label.setBackgroundColor(0xffedc850);
			label.setTextColor(0xffffffff);
			break;
		case 1024:
			label.setBackgroundColor(0xffedc53f);
			label.setTextColor(0xffffffff);
			break;
		case 2048:
			label.setBackgroundColor(0xffedc22e);
			label.setTextColor(0xffffffff);
			break;
		case 4096:
			label.setBackgroundColor(0xffe5c52a);
			label.setTextColor(0xffffffff);
			break;
		default:
			label.setBackgroundColor(0xff3c3a32);
			break;
		}
	}

	public boolean equals(Card card) {
		return this.getNum() == card.getNum();
	}

	public TextView getLabel() {
		return label;
	}

	public int getNum() {
		return num;
	}
}
