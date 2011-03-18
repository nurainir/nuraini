package aini.nur;
/**
 * Terima contoh penggunaan EditText dan passing data antar activity di android
 * class ini menerima data string dan integer dari ContohEditText
 * @author	: Nur Aini Rakhmawati
 * @since	: March 10, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Terima extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.trima);
		 Bundle b = this.getIntent().getExtras();
	
		         String s = b.getString("nilai");
		         int x = b.getInt("x");
		         ((TextView)findViewById(R.id.TextView01)).setText(s+x);
	}

}
