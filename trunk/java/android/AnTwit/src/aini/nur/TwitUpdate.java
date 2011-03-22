package aini.nur;

/**
 * Twitupdate contoh melakukan authentifikasi OAUTH twitter dan melakukan update status
 * <br/>
 * menggunakan library jwitter http://www.winterwell.com/software/jtwitter.php
 * dan signpost http://code.google.com/p/oauth-signpost/downloads/detail?name=signpost-core-1.2.1.1.jar
 * @author	: Nur Aini Rakhmawati
 * @since	: March 22, 2011
 * @license	: GPL
 */
import java.net.URI;
import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException.Repetition;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TwitUpdate extends Activity {
	Twitter twitter;
	String pin=null;
	 OAuthSignpostClient oauthClient;
	 boolean setpin = false;
	 // dua variabel berikut didapat setelah registrasi aplikasi di twitter
	 static String JTWITTER_OAUTH_KEY = "XXXX";
	 static String jTWITTER_OAUTH_SECRET = "XXXX";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences settings = getSharedPreferences("twit", 0);
        String key = settings.getString("accesstoken0", null);
        String secret = settings.getString("accesstoken1", null);
        final Button btnsetpin = (Button)findViewById(R.id.btnpin);
        final Button btnupdate = (Button)findViewById(R.id.btnupdate);
        final EditText status = (EditText)findViewById(R.id.status);
  
        
       if(key==null)
       { 
	       oauthClient = new OAuthSignpostClient(JTWITTER_OAUTH_KEY, jTWITTER_OAUTH_SECRET, "oob");
	       URI uri = oauthClient.authorizeUrl();
	      status.setVisibility(View.INVISIBLE);
	      btnupdate.setVisibility(View.INVISIBLE);
	      
	       final Dialog dialog = new Dialog(this);
	       dialog.setContentView(R.layout.twdialog);
	       WebView wv = (WebView)dialog.findViewById(R.id.webtweet);
	       wv.loadUrl(uri.toString());
	       btnsetpin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					dialog.show();
				}

               });

			final EditText nilaipin = (EditText)dialog.findViewById(R.id.pin);
			nilaipin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					nilaipin.setText("");
					
				}
			});


			Button btnok = (Button)dialog.findViewById(R.id.btntweet);
			btnok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					pin=nilaipin.getText().toString();
					setpin=true;
					dialog.dismiss();
					status.setVisibility(View.VISIBLE);
			    	btnsetpin.setVisibility(View.INVISIBLE);
			    	btnupdate.setVisibility(View.VISIBLE);
				}

               });

       }
       else
       {
    	   oauthClient = new OAuthSignpostClient(JTWITTER_OAUTH_KEY, jTWITTER_OAUTH_SECRET, key, secret);
    	   twitter = new Twitter("nuraini", oauthClient);
    	   setpin = false;
    	   status.setVisibility(View.VISIBLE);
    	   btnsetpin.setVisibility(View.INVISIBLE);
    	   btnupdate.setVisibility(View.VISIBLE);
    	   btnupdate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					twitter.setSource("keyrani");	
					try{
						twitter.updateStatus(status.getText().toString());
						
					}catch (Repetition e) {
						 Toast.makeText(TwitUpdate.this, "status tdk boleh sama", Toast.LENGTH_SHORT).show();
					}
					
					
				}

              });
    	   
       }
      
    
        
     
              }
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(pin!=null && setpin)
		{
			oauthClient.setAuthorizationCode(pin);
			String [] accessToken = oauthClient.getAccessToken();
			  SharedPreferences settings = getSharedPreferences("twit", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("accesstoken0", accessToken[0]);
            editor.putString("accesstoken1", accessToken[1]);
            editor.commit();
			
		}
	}
}