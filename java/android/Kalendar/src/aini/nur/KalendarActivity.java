package aini.nur;

/**
 * KalendarActivity adalah alarm dengan menggunakan datepicker dan timepicker
 * akan memunculkan notifikasi pada waktu yang ditentukan
 * <br/> terinpirasi dari contoh android class alarm_service dan alarm_controller
 * @author	: Nur Aini Rakhmawati
 * @since	: March 13, 2011
 * @license	: GPL
 */

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Intent;


public class KalendarActivity extends Activity {
	
	   private int mYear;
	   private int mMonth;
	   private int mDay;
	   private int mHour;
	   private int mMinute;
	   PendingIntent sender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final DatePicker tgl = (DatePicker)findViewById(R.id.DatePicker01);
        final TimePicker waktu = (TimePicker)findViewById(R.id.TimePicker01);
                
        Button btnSetAlarm = (Button)findViewById(R.id.Button01);
        Button btnMatiAlarm = (Button)findViewById(R.id.Button02);
        
        sender = PendingIntent.getService(KalendarActivity.this,
                0, new Intent(KalendarActivity.this, KalendarService.class), 0);
        
        btnSetAlarm.setOnClickListener(new Button.OnClickListener(){

        	  @Override
        	  public void onClick(View arg0) {
        		  mMonth = tgl.getMonth();
        		  mDay = tgl.getDayOfMonth();
        		  mYear = tgl.getYear();
        		  mHour = waktu.getCurrentHour();
        		  mMinute = waktu.getCurrentMinute();
        		  
        		 StringBuilder sb=  new StringBuilder();
        		 sb.append(mDay).append("-");
        		 sb.append(mMonth + 1).append("-"); // bulan tambah 1
                 sb.append(mYear);
        		 sb.append(' ').append(mHour).append(':').append(mMinute);
        		 
       	// setting waktu
                   Calendar calendar = Calendar.getInstance();
                   calendar.setTimeInMillis(System.currentTimeMillis());
                   calendar.set(Calendar.YEAR,mYear);
                   calendar.set(Calendar.MONTH,mMonth);
                   calendar.set(Calendar.DAY_OF_MONTH,mDay);
                   calendar.set(Calendar.HOUR_OF_DAY,mHour);
                   calendar.set(Calendar.MINUTE,mMinute);

                   AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                   am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                   
        		Toast.makeText(KalendarActivity.this,sb.toString() , Toast.LENGTH_LONG).show();
        		  
       
        	  }
        });
        
        btnMatiAlarm.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
			      AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.cancel(sender);
	          
		            Toast.makeText(KalendarActivity.this, R.string.Mati,
		                    Toast.LENGTH_LONG).show();
				
			}
        	
        });
    }
        
}