/*
 * MemoryBoard.java
 *
 * Created on April 12, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ghifari.pasific.memory;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * @author ekobs
 */
public class MemoryBoard extends View
{
	private float modelSize;
	int scrSize;

	private boolean[][] visible;
	private Drawable[] drawables;

	private int[][] key;
	private Model model;
	
	private Memory controller;
	public MemoryBoard(Context ctx, Model model)
	{
		super(ctx);
		controller = (Memory) ctx;
		this.model = model;
		this.key = model.getKey();
		this.drawables = model.getDrawables();
		this.visible = model.getVisible();

		this.modelSize = 4.5f;
		

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		
		Paint paint = new Paint();
		
		this.scrSize = getWidth();
		// ICanvas iCanvas = new ICanvas(5, 5, canvas, getWidth(), getHeight());

		int lineColor = Color.parseColor("#008000");
		paint.setColor(lineColor);
		paint.setStyle(Style.FILL);

		float x = .1f;
		float y;
		for (int i = 0; i < 4; i++)
		{
			y = .15f;
			for (int j = 0; j < 5; j++)
			{

				Log.v("KEY=", "i=" + i + " j=" + j + " key=" + key[i][j] + " visible :" + visible[i][j]);
				if (visible[i][j])
				{
					// Log.v("KEY=", "i=" + i + " j=" + j + " visible=" +
					// visible[i][j]);
					// drawBitmap(canvas, drawables[key[i][j]], i + .5f, j +
					// 1.5f);
					drawBitmap(canvas, drawables[key[i][j]], x, y + 1);
				}

				drawLine(canvas, x, y, x + 1, y, paint);
				drawLine(canvas, x, y + 1, x + 1, y + 1, paint);
				drawLine(canvas, x, y, x, y + 1, paint);
				drawLine(canvas, x + 1, y, x + 1, y + 1, paint);

				y += 1.15;
			}
			x += 1.1;
		}
		/*
		 * for (int i = 0; i < 5; i++) {
		 * 
		 * drawLine(canvas, .5f, .5f + i, 4.5f, .5f + i, paint); } for (int i =
		 * 0; i < 5; i++) { drawLine(canvas, .5f + i, .5f, .5f + i, 4.5f,
		 * paint); }
		 */
		/*
		 * iCanvas.drawBitmap (drawables[key[0][0]], .5f, .5f);
		 * iCanvas.drawBitmap (drawables[key[1][1]], 1.5f, 1.5f);
		 * iCanvas.drawBitmap (drawables[key[2][2]], 2.5f, 2.5f);
		 * iCanvas.drawBitmap (drawables[key[3][3]], 3.5f, 3.5f);
		 */
	}

	
	private Handler mHandler = new Handler();

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		if (MotionEvent.ACTION_DOWN == event.getAction())
		{
			onActionDown(event);

		}

		return true;
	}

	public boolean onActionDown(MotionEvent event)
	{
		
		// Log.v("Touch", "key0=" + key0 + " key1=" + key1 + " clickable :" +
		// clickable);
		if (Model.clickable)
		// dua opsi :
		// 1 : belum diklik dua
		// 2 : sudah diklik 2 tapi salah
		// 3 : baru menang
		{
			Model.clickable = false;
			int action = event.getAction();
			boolean mCurDown = action == MotionEvent.ACTION_DOWN
					|| action == MotionEvent.ACTION_MOVE;

			float x = event.getX();
			float y = event.getY();

			y = getHeight() - y;
			// Log.v("Touch", "x=" + x + " y=" + y);

			float xU = getWidth() / 4.5f;
			float yU = getWidth() / 4.5f;

			float xF = x / xU;
			float yF = y / yU;

			xF -= .1f;
			yF -= .15f;

			// float xPos = x - xU / 2;
			// float yPos = (getWidth() - y) - yU / 2;

			// Log.v("Touch", "xU=" + xU + " yu=" + yU);
			Log.v("Touch", "x=" + x + " xF=" + xF);
			Log.v("Touch", "y=" + x + " yF=" + yF);

			int i = (int) Math.floor(xF / 1.1);
			int j = (int) Math.floor(yF / 1.15);

			Log.v("clicked ini ", "i=" + i + " j =" + j);
			Log.v("clicked ini ", "xxx=" + (1 + ((i) * 1.1)) + " yyyy ="
					+ (1 + ((j) * 1.1)));
			if (xF > 1 + i * 1.1)
			{
				i = -1;
			}

			if (yF > 1 + j * 1.15)
			{
				j = -1;
			}

			Log.v("clicked ini ", "i=" + i + " j =" + j);
			Log.v("clicked ini ", "key0=" + Model.key0 + " key1 =" + Model.key1);

			
			

			// sudah diklik ke-1, belum yg ke 2
			if (i < 0 || i > 3 || j < 0 || j > 4 || visible[i][j])
			{
				
				Model.clickable = true;
				// out of bound
			} else if (Model.key0 >= 0)
			{
				invalidate();
				visible[i][j] = true;
				Model.i1 = i;
				Model.j1 = j;
				Model.key1 = key[i][j];

				if (Model.key0 != Model.key1)
				{
					Model.clickable = false;

					mHandler.postDelayed(mUpdateTimeTask, 500);
				} else
				{
					Log.v("test", "ok sama !");
					Model.getInstance().setCounter(Model.getInstance().getCounter() + 1);
					
					if(Model.getInstance().getCounter() == 10)
					{
						controller.showDialog(1);
					}
					
					Model.key0 = -1;
					Model.key1 = -1;
					Model.i0 = -1;
					Model.j0 = -1;
					Model.i1 = -1;
					Model.j1 = -1;
					Model.clickable = true;
				}
			} else
			{
				invalidate();
				visible[i][j] = true;
				Model.clickable = true;
				Model.i0 = i;
				Model.j0 = j;
				Model.key0 = key[i][j];
			}

		}

		return true;
	}

	private void onTimer()
	{
		
		visible[Model.i0][Model.j0] = false;
		visible[Model.i1][Model.j1] = false;
		Model.key0 = -1;
		Model.key1 = -1;
		Model.i0 = -1;
		Model.j0 = -1;
		Model.i1 = -1;
		Model.j1 = -1;

		try
		{
			invalidate();
		} catch (Exception e)
		{
			Log.v("err", e.toString());
		}
		Model.clickable = true;
	}

	private Runnable mUpdateTimeTask = new Runnable()
	{
		public void run()
		{

			onTimer();
			mHandler.removeCallbacks(mUpdateTimeTask);
		}
	};

	private float getX(float x)
	{
		float x2 = x / modelSize * scrSize;
		return x2;
	}

	private float getY(float y)
	{
		// float y2 = (1 - y/modelHeight) * scrHeight;
		// float y2 = y/modelWidth * scrWidth;
		float y2 = getHeight() - (y / modelSize) * scrSize;
		return y2;
	}

	public void drawLine(Canvas canvas, float startX, float startY,
			float stopX, float stopY, Paint paint)
	{
		canvas.drawLine(getX(startX), getY(startY), getX(stopX), getY(stopY),
				paint);
	}

	public void drawBitmap(Canvas canvas, Drawable draw, float x, float y)
	{
		draw.setBounds((int) getX(x), (int) getY(y), (int) getX(x + 1),
				(int) getY(y - 1));
		draw.draw(canvas);

	}
}