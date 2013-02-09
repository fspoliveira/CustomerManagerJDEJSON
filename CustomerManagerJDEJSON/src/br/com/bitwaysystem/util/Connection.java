package br.com.bitwaysystem.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public class Connection {
	
	private static final String TAG = "teste";

	public static boolean conectado(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager)
            context.getSystemService(Context.CONNECTIVITY_SERVICE);
            String LogSync = null;
            String LogToUserTitle = null;
            if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
				LogSync += "\nConectado a Internet 3G ";
                    LogToUserTitle += "Conectado a Internet 3G ";
                    //handler.sendEmptyMessage(0);
                    Log.d(TAG,"Status de conexão 3G: "+cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                    return true;
            } else if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()){
                    LogSync += "\nConectado a Internet WIFI ";
                    LogToUserTitle += "Conectado a Internet WIFI ";
                    //handler.sendEmptyMessage(0);
                    Log.d(TAG,"Status de conexão Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                    return true;
            } else {
                    LogSync += "\nNão possui conexão com a internet ";
                    LogToUserTitle += "Não possui conexão com a internet ";
                    //handler.sendEmptyMessage(0);
                    Log.e(TAG,"Status de conexão Wifi: "+cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected());
                    Log.e(TAG,"Status de conexão 3G: "+cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected());
                    return false;
            }
        } catch (Exception e) {
                Log.e(TAG,e.getMessage());
                return false;
        }
    }

}