package com.example.smsvote;


import java.util.Calendar;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private Button startStopButton;
	private boolean isCollecting = false;
	private SmsReceiver smsReceiver = null;
	private String[] latestList = null;
	private ListView lv = null;
	
	public int startingSeconds;
	public int endingSeconds;
	
	public void registerReceiver() {
		smsReceiver = new SmsReceiver();
		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		intentFilter.setPriority(999);
		this.registerReceiver(smsReceiver, intentFilter);
	}

	public void unregisterReceiver() {
		if (smsReceiver != null) {
			this.unregisterReceiver(smsReceiver);
			smsReceiver = null;
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver();
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		lv = (ListView) findViewById(R.id.resultList);

		isCollecting = false;
		startStopButton = (Button) findViewById(R.id.startButton);
		startStopButton.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// STARTING VOTES
		if (isCollecting == false) {
			isCollecting = true;
			startStopButton.setText(R.string.button_on);
			registerReceiver();
			
			Calendar c = Calendar.getInstance(); 
			startingSeconds = c.get(Calendar.SECOND);



			
		// STOPPING VOTES
		} else {
			isCollecting = false;
			startStopButton.setText(R.string.button_off);

			if (smsReceiver != null) {
				latestList = smsReceiver.result();
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, latestList );
				lv.setAdapter(arrayAdapter);
			}

			unregisterReceiver();
			
			Calendar c = Calendar.getInstance(); 
			endingSeconds = c.get(Calendar.SECOND);


		}
	}



}
