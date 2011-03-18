package aini.nur;

/**
 * TabUtama tampilan utama yang mengandung TabSatu dan TabDua di android
 * @author	: Nur Aini Rakhmawati
 * @since	: March 18, 2011
 * @license	: GPL
 */

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabUtama extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        TabSpec tabSpec;
        Intent intent;
        
        intent = new Intent().setClass(this, TabSatu.class);
        
        tabSpec = tabHost.newTabSpec("tid1");
        tabSpec.setIndicator("Tab Satu").setContent(intent);
        tabHost.addTab(tabSpec);
        
        intent = new Intent().setClass(this, TabDua.class);
        tabSpec = tabHost.newTabSpec("tid2");
        tabSpec.setIndicator("Tab Dua").setContent(intent);
        tabHost.addTab(tabSpec);
    }
}