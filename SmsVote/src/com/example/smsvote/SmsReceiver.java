package com.example.smsvote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{
	
	
	public DataHelper data = new DataHelper();
	
	

	@Override
	public void onReceive(Context context, Intent intent) {
		// body

		Bundle bundle = intent.getExtras();
		Object[] pdus = (Object[]) bundle.get("pdus");
		SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
		if(isInteger(messages.getMessageBody())) {
			abortBroadcast();
			data.addVote(messages.getDisplayOriginatingAddress(), messages.getDisplayMessageBody());
		}

		Toast.makeText(context, messages.getOriginatingAddress() + " : " + messages.getDisplayMessageBody(),
				Toast.LENGTH_SHORT).show();
		
	}

	private boolean isInteger(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	public String[] result() {
		return data.getUnifiedList();
	}

}
