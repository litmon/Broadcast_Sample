package com.example.broadcastreceiver_sample;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		Log.d("MY BLUETOOTH TETHERING", "STATE: " + action);
		if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
			Intent serviceIntent = new Intent(context, MyService.class);
			serviceIntent.putExtra("setBluetoothTethering", true);
			context.startService(serviceIntent);
		}else if(action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)){
			Intent serviceIntent = new Intent(context, MyService.class);
			serviceIntent.putExtra("setBluetoothTethering", false);
			context.startService(serviceIntent);			
		}
	}

}
