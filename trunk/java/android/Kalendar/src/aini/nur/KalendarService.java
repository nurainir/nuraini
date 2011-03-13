package aini.nur;
/**
 * KalendarService membuat servis untuk memulai alarm dan
 * memunculkan notifikasi pada waktu yang ditentukan
 * @author	: Nur Aini Rakhmawati
 * @since	: March 10, 2011
 * @license	: GPL
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class KalendarService extends Service {

	@Override
	public void onCreate() {
	     NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	     CharSequence text = getText(R.string.peringatan);

	      Notification notification = new Notification(R.drawable.icon, text,
	                System.currentTimeMillis());

	      // Munculkan tampilan kalender
	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
	                new Intent(this, KalendarActivity.class), 0);

	        // tampilkan notifikasi
	        notification.setLatestEventInfo(this, getText(R.string.peringatan),
	                       text, contentIntent);

	        // kirim notifikasi 
	        mNotificationManager.notify(R.string.cekperingatan, notification);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}

    private final IBinder mBinder = new Binder() {
        @Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
		        int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };
}
