package org.ghifari.pasific.memory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Memory extends Activity
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.v("on create", "1");
		
		Model model = Model.getInstance();
		Log.v("on create", "2 " + model);
		if(model.getStock() == null)
		{
			Resources resources = getResources();
			model.restock(resources);
			
		}

		builder = new AlertDialog.Builder(Memory.this).setTitle(
				"Congratulations. You solve it. Play more ?")
		// .setIcon (R.drawable.folder_home2)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						play();
					}
				});

		play();
	}

	private AlertDialog.Builder builder;

	@Override
	protected Dialog onCreateDialog(int id)
	{
		// TODO Auto-generated method stub
		if (id == 1)
		{
			return builder.create();
		} else
		{
			return super.onCreateDialog(id);
		}
	}


	public void play()
	{
		if(Model.getInstance().getDrawables() == null || Model.getInstance().getCounter() == 10)
		{
			Model.getInstance().random();
		}
		View board = new MemoryBoard(this, Model.getInstance());
		setContentView(board);
	}
}