package aini.nur;

/**
 * Nyeting contoh melakukan setting variable untuk aplikasi di Android
 * <br/>
 * panggilconfig memanggil preference (config.java) dan akan menampilkan hasil saat resume
 * @author	: Nur Aini Rakhmawati
 * @since	: March 18, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class panggilconfig extends Activity {

	TextView nama;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button tombol = (Button)findViewById(R.id.Button01);
        nama = (TextView)findViewById(R.id.namasaya);
        tombol.setOnClickListener(new Button.OnClickListener(){

        	  @Override
        	  public void onClick(View arg0) {
        	   // TODO Auto-generated method stub
        	   startActivity(new Intent(panggilconfig.this, config.class));
        	  }

			});
        	   }
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
		 nama.setText(myPreference.getString("nama", ""));
	}
	
}
