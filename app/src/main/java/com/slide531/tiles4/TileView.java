package com.slide531.tiles4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class TileView extends View {

	private Paint p;
	private Paint background;
	private int w = 0;
	private int h = 0;
	private int entry = 0;
	private int exit = 0;

	public TileView(Context context) {
		super(context);
		init();
	}

	public TileView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TileView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		p = new Paint();
		p.setStrokeWidth(3.0f);
		p.setAntiAlias(true);
		p.setStyle(Paint.Style.STROKE);
		p.setColor(Color.BLACK);

		Random r = new Random(this.hashCode());
		int first = Math.abs(r.nextInt(4));
		int second = (first + 1 + (Math.abs(r.nextInt(3)))) % 4;
		entry = Math.min(first, second);
		exit = Math.max(first, second);

		background = new Paint();
		p.setStrokeWidth(2.0f);
		background.setStyle(Paint.Style.STROKE);
		background.setColor(Color.GRAY);

		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int first = (entry + 1) % 4;
				int second = (exit + 1) % 4;
				entry = Math.min(first, second);
				exit = Math.max(first, second);
				invalidate();
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawRect(2, 2, w - 2, h - 2, background);
		if (Math.abs(entry - exit) != 2) {
			drawArc(canvas);
		} else {
			drawLine(canvas);
		}
	}

	private void drawLine(Canvas canvas) {
		if (entry == 0) {
			canvas.drawLine(w / 2, 0, w / 2, h, p);
		} else {
			canvas.drawLine(0, h / 2, w, h / 2, p);
		}
	}

	private void drawArc(Canvas canvas) {
		RectF rect = new RectF();
		int startAngle = 0;
		if (entry == 0 && exit == 1) {
			rect.top = -h / 2;
			rect.bottom = h / 2;
			rect.left = w / 2;
			rect.right = (w / 2) * 3;
			startAngle = 90;
		} else if (entry == 0 && exit == 3) {
			rect.top = -h / 2;
			rect.bottom = h / 2;
			rect.left = -(w / 2);
			rect.right = w / 2;
			startAngle = 0;
		} else if (entry == 1 && exit == 2) {
			rect.top = h / 2;
			rect.bottom = (h / 2) * 3;
			rect.left = w / 2;
			rect.right = (w / 2) * 3;
			startAngle = 180;
		} else if (entry == 2 && exit == 3) {
			rect.top = h / 2;
			rect.bottom = (h / 2) * 3;
			rect.left = -w / 2;
			rect.right = w / 2;
			startAngle = 270;
		}
		canvas.drawArc(rect, startAngle, 90, false, p);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.w = w;
		this.h = h;
	}
}
