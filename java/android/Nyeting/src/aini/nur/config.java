package aini.nur;

/**
 * Nyeting contoh melakukan setting variable untuk aplikasi di Android
 * <br/>
 * config menampilkan dialog untuk seting nama
 * @author	: Nur Aini Rakhmawati
 * @since	: March 18, 2011
 * @license	: GPL
 */

import android.os.Bundle;
import android.preference.PreferenceActivity;


public class config extends PreferenceActivity {
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.konfigurasi);
        	   }
}