package com.example.broadcastreceiver_sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfile.ServiceListener;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

@SuppressLint("NewApi")
public class MyService extends Service {
	Class<?> bluetoothPan;
	Constructor<?> ctor;
	Object instance;
	Class[] paramSet = { boolean.class };
	Method setTetheringOn;
	boolean isTetheringOn;
	BluetoothAdapter adapter;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		isTetheringOn = intent.getBooleanExtra("setBluetoothTethering", true);
		
		Log.d("MY BLUETOOTH TETHERING", "isTetheringOn: " + isTetheringOn);
		try {
			bluetoothPan = Class.forName("android.bluetooth.BluetoothPan");

			ctor = bluetoothPan.getDeclaredConstructor(Context.class, ServiceListener.class);
			setTetheringOn = bluetoothPan.getDeclaredMethod("setBluetoothTethering", paramSet);

			ctor.setAccessible(true);
			instance = ctor.newInstance(this, new MyServiceListener(this));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return startId;
	}

	private class MyServiceListener implements ServiceListener {
		final Context context;

		public MyServiceListener(Context context) {
			this.context = context;
		}

		@Override
		public void onServiceConnected(int profile, BluetoothProfile proxy) {
			try {
				Log.d("MY BLUETOOTH TETHERING", "PAN CONNECTED");
				setTetheringOn.invoke(instance, isTetheringOn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(int profile) {
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
