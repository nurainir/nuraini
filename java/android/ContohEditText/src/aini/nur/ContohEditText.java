package aini.nur;

/**
 * ContohEditText contoh penggunaan EditText di android
 * @author	: Nur Aini Rakhmawati
 * @since	: March 10, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
				// tampilkan nilai edittext pada toast
				 Toast.makeText(ContohEditText.this, isi.getText(),
		                    Toast.LENGTH_SHORT).show();

				
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