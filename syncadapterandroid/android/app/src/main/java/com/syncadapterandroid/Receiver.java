package com.syncadapterandroid;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;
import android.util.Log;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );

            if (noConnectivity) {
                Intent service = new Intent(context, NetworkService.class);
                Bundle bundle = new Bundle();
                bundle.putString("Params", intent.getAction());
                bundle.putString("NetworkState", "Off");
                service.putExtras(bundle);

                context.startService(service);
            } else {
                Intent service = new Intent(context, NetworkService.class);
                Bundle bundle = new Bundle();
                bundle.putString("Params", intent.getAction());
                bundle.putString("NetworkState", "On");
                service.putExtras(bundle);

                context.startService(service);
            }
        }
    }
}