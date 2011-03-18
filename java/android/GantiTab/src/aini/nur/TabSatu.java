package aini.nur;
/**
 * TabSatu menampilkan rating di android
 * @author	: Nur Aini Rakhmawati
 * @since	: March 18, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;


public class TabSatu extends Activity {

	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        final RatingBar rb = (RatingBar)findViewById(R.id.RatingBar01);
        rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(TabSatu.this, "Rating Sekarang: " + rating, Toast.LENGTH_SHORT).show();
            }
        });
        
        Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(new Button.OnClickListener() {
      

			@Override
			public void onClick(View v) {
			
			

				
			}
        	
        });

    }
	
}
