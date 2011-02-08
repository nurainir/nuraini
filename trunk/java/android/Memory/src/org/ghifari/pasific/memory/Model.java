package org.ghifari.pasific.memory;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;


public class Model
{
	private static Model instance = new Model();
	private Model()
	{
		
	}
	
	public static Model getInstance()
	{
		return instance;
	}
	
	
	public List<Drawable> getStock()
	{
		return stock;
	}

	public void setStock(List<Drawable> stock)
	{
		this.stock = stock;
	}

	public boolean[][] getVisible()
	{
		return visible;
	}

	public void setVisible(boolean[][] visible)
	{
		this.visible = visible;
	}

	public Drawable[] getDrawables()
	{
		return drawables;
	}

	public void setDrawables(Drawable[] drawables)
	{
		this.drawables = drawables;
	}

	public int[][] getKey()
	{
		return key;
	}

	public void setKey(int[][] key)
	{
		this.key = key;
	}

	public int getCounter()
	{
		return counter;
	}

	public void setCounter(int counter)
	{
		this.counter = counter;
	}

	public static void setInstance(Model instance)
	{
		Model.instance = instance;
	}


	public  List<Drawable> stock;
	
	public boolean[][] visible;
	public Drawable[] drawables;

	public int[][] key;
	public int counter = 0;
	
	private Resources resources;
	
	public void restock(Resources resources)
	{
		Log.v("on create", "3 " + resources);
		this.resources = resources;
		stock = new ArrayList<Drawable>();
		 
		stock.add(resources.getDrawable(R.drawable.orchid_blue_0));
		stock.add(resources.getDrawable(R.drawable.orchid_blue_1));
		stock.add(resources.getDrawable(R.drawable.orchid_blue_2));
		stock.add(resources.getDrawable(R.drawable.orchid_blue_3));

		stock.add(resources.getDrawable(R.drawable.orchid_green_0));
		stock.add(resources.getDrawable(R.drawable.orchid_green_1));
		stock.add(resources.getDrawable(R.drawable.orchid_green_2));
		stock.add(resources.getDrawable(R.drawable.orchid_green_3));

		stock.add(resources.getDrawable(R.drawable.orchid_orange_0));
		stock.add(resources.getDrawable(R.drawable.orchid_orange_1));
		stock.add(resources.getDrawable(R.drawable.orchid_orange_2));
		stock.add(resources.getDrawable(R.drawable.orchid_orange_3));

		stock.add(resources.getDrawable(R.drawable.orchid_pink_0));
		stock.add(resources.getDrawable(R.drawable.orchid_pink_1));
		stock.add(resources.getDrawable(R.drawable.orchid_pink_2));
		stock.add(resources.getDrawable(R.drawable.orchid_pink_3));
		
		stock.add(resources.getDrawable(R.drawable.orchid_purple_0));
		stock.add(resources.getDrawable(R.drawable.orchid_purple_1));
		stock.add(resources.getDrawable(R.drawable.orchid_purple_2));
		stock.add(resources.getDrawable(R.drawable.orchid_purple_3));

		stock.add(resources.getDrawable(R.drawable.orchid_red_0));
		stock.add(resources.getDrawable(R.drawable.orchid_red_1));
		stock.add(resources.getDrawable(R.drawable.orchid_red_2));
		stock.add(resources.getDrawable(R.drawable.orchid_red_3));

		stock.add(resources.getDrawable(R.drawable.orchid_white_0));
		stock.add(resources.getDrawable(R.drawable.orchid_white_1));
		stock.add(resources.getDrawable(R.drawable.orchid_white_2));
		stock.add(resources.getDrawable(R.drawable.orchid_white_3));
		
		stock.add(resources.getDrawable(R.drawable.orchid_yellow_0));
		stock.add(resources.getDrawable(R.drawable.orchid_yellow_1));
		stock.add(resources.getDrawable(R.drawable.orchid_yellow_2));
		stock.add(resources.getDrawable(R.drawable.orchid_yellow_3));
	}
	
	private Drawable getDrawable()
	{
		return stock.remove((int)(stock.size() * Math.random()));
	}
	
	protected void random()
	{
		
		Log.v("on create", "3 random ");
		
		if(stock == null || stock.size() < 10)
		{
			restock(resources);
		}
		
			drawables = new Drawable[10];
		

		for (int i = 0; i < drawables.length; i++)
		{
			drawables[i] = getDrawable();
		}
		/*
		 * Drawable people = getResources ().getDrawable (R.drawable.bug);
		 * Drawable target = getResources ().getDrawable
		 * (R.drawable.folder_home2);
		 */
		// view = new LabirynthZone (this, dialog, people, target,
		// drawables);

		// harusnya jangan static, tetapi singleton

		key = new int[4][5];

		int x = 0;
		for (int j = 0; j < 5; j++)
		{
			for (int i = 0; i < 2; i++)
			{
				Log.v("randomize", " i=" + i + " j=" + j);
				key[2*i][j] = x;
				key[2*i+1][j] = x;
				x++;
				// randomize value
			}
		}

		// randomization
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				//if (Math.random() < 0.5)
				{
					int i2 = (int) (4 * Math.random());
					int j2 = (int) (5 * Math.random());

					int tmp = key[i][j];
					key[i][j] = key[i2][j2];
					key[i2][j2] = tmp;
				}
				// randomize value
			}
		}
		
		visible = new boolean[4][5];
		counter = 0;
	}

	
	// temporary ... mestinya gak boleh static
	
	public static boolean clickable = true;
	public static int key0 = -1, i0, j0, key1 = -1, i1, j1;

}
