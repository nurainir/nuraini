package aini.nur;
/**
 * Indonesia adalah contoh penggunaan TextSwitcher, Menu, String Array dan perpindahan Activity
 * Aktivity dalam bahasa Indonesia
 * <br/> terinpirasi dari contoh android API di SDK 
 * @author	: Nur Aini Rakhmawati
 * @since	: March 16, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


public class Indonesia extends Activity implements ViewSwitcher.ViewFactory{
    /** Called when the activity is first created. */
	int pos=0;
	private  Button prev,next;
	private TextSwitcher mSwitcher;

    private static final int ID = Menu.FIRST;
    
    private static final int EN = Menu.FIRST+1;
    boolean IND = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id);
        Resources res = getResources();
        final String[] nomor = res.getStringArray(R.array.nomorbaru);
       mSwitcher = (TextSwitcher) findViewById(R.id.nomor); 
        mSwitcher.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);

     
        

        
        prev = (Button) findViewById(R.id.sebelum);
        prev.setOnClickListener(new Button.OnClickListener() {
      

			@Override
			public void onClick(View v) {
				
				pos--;
				mSwitcher.setText(nomor[pos]);
				update();
			}
        	
        });
        next = (Button) findViewById(R.id.sesudah);
        next.setOnClickListener(new Button.OnClickListener() {
      

			@Override
			public void onClick(View v) {
				
				pos++;
				mSwitcher.setText(nomor[pos]);
				update();
			}
        	
        });
        this.update();
    }

    /* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	       menu.add(0, ID, 0, "Indonesia");
	        menu.add(0, EN, 0, "English").setCheckable(true);
	        return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
         case ID:
             return true;
         case EN:
        	 startActivity(new Intent(this, English.class));
             return true;
         default:
             return super.onOptionsItemSelected(item);
		 }
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		 menu.findItem(ID).setChecked(IND);
		return super.onPrepareOptionsMenu(menu);
	}

	private void update()
    {	
    	if(pos==0)
		prev.setEnabled(false);
    	else if(pos==3)
		next.setEnabled(false);
    	else
    	{
    		next.setEnabled(true);
    		prev.setEnabled(true);
    	}
    
    	
    }
	@Override
	public View makeView() {
		  TextView t = new TextView(this);
	        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
	        t.setTextSize(36);
	        return t;

	}
}