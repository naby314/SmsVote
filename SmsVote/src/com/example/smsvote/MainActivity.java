package com.example.smsvote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button startStopButton;
	private boolean isCollecting;
	private SmsReceiver receiver = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		
		isCollecting = false;
        startStopButton = (Button) findViewById(R.id.startButton);
        startStopButton.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// STOPPING VOTES
		if (isCollecting) {
			isCollecting = false;
			startStopButton.setText(R.string.button_off);
			
			receiver.result();
			receiver = null;
		
		// STARTING VOTES
		} else {
			isCollecting = true;
			startStopButton.setText(R.string.button_on);
			receiver = new SmsReceiver();
		}
	}

}
