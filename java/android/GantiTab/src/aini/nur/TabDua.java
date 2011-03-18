package aini.nur;
/**
 * TabSatu menampilkan web di android
 * @author	: Nur Aini Rakhmawati
 * @since	: March 18, 2011
 * @license	: GPL
 */

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class TabDua extends Activity {

	   /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);
        WebView myWebView = (WebView) findViewById(R.id.WebView01);
        myWebView.loadUrl("http://mobile.twitter.com/nuraini");
    }
	
}
