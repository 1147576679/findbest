/*
package com.ksec.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;

public class NetworkChecker {
	*/
/**
	 * 检查网络是否可用
	 * @param context
	 * @return
	 *//*

	public static boolean isNetworkConnected(Context context) {
		if(context!=null) {
			ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
			if(mNetworkInfo!=null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static boolean isWifiOpen(Context context) {
		boolean isOpen=false;
		if(context != null) {
			ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(mWiFiNetworkInfo != null) {
				State wifi=mWiFiNetworkInfo.getState();
				if(wifi == State.CONNECTED||wifi==State.CONNECTING) isOpen=true;
			}
		}

		return isOpen;
	}


	public static boolean isWifiConnected(Context context) {
		if(context != null) {
			ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo=mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static WifiInfo getWifiInfo(Context context){
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		return wifiInfo;
	}

	public static boolean isWiFiActive(Context context) {
		ConnectivityManager connectivity=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static int getCurrentNetworkType(Context context) {
		int state=0;
		if(context!=null) {
			ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info=mConnectivityManager.getActiveNetworkInfo();
			if(info!=null && info.isAvailable()) {
				state=1;
				String name=info.getTypeName();
				if(name.toLowerCase().equals("wifi")) state=2;
			}
		}
		return state;
	}

}
*/
