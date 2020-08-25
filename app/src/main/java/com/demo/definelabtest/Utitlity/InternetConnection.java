package com.demo.definelabtest.Utitlity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection {
    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            return false;
        }
        else {
            if(info.isConnected()) {
                return true;
            }
            else {
                return true;
            }

        }
    }
}
