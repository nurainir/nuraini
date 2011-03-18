package aini.nur;

/**
 * ContohEditText contoh penggunaan EditText dan passing data antar activity di android
 * @author	: Nur Aini Rakhmawati
 * @since	: March 10, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ContohEditText extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText isi = (EditText) findViewById(R.id.EditTextIsi);
        Button button = (Button) findViewById(R.id.btnOK);
        button.setOnClickListener(new Button.OnClickListener() {
      

			@Override
			public void onClick(View v) {
			
				Intent i = new Intent(ContohEditText.this,Terima.class);    
				Bundle b = new Bundle();
				b.putString("nilai", isi.getText().toString());
				b.putInt("x", 3);
				i.putExtras(b);
				startActivityForResult(i, 0);
				


				
			}
        	
        });
        
        isi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// kosongkan editText
				isi.setText("");
				
			}
		});
    }
}