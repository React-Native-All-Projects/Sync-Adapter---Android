package com.syncadapterandroid;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import com.facebook.react.bridge.Promise;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import androidx.work.PeriodicWorkRequest;
import java.util.concurrent.TimeUnit;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.WorkManager;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.Arguments;
import androidx.work.Constraints;


import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class ModuleApp extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;
    private PeriodicWorkRequest workRequest;
    public String ModuleName = "ModuleApp";

    Constraints constraints = new Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build();

    Receiver Receiver = new Receiver();
    ModuleApp(ReactApplicationContext context) {
        super(context);
        this.context = context;
        workRequest = new PeriodicWorkRequest.Builder(SyncWorker.class, 20, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build();

        registerReceiverNetworkChange();
    }
    @Override
    public String getName() {
        return ModuleName;
    }
    
    @ReactMethod
    public void getNameReactMethod(Promise promise) {
        promise.resolve(ModuleName);
    }
    @ReactMethod
    public void add(Double a, Double b, Promise promise) {
        Double sum = a + b;
        promise.resolve(sum);
    }

    @Override
    public void initialize() {
        super.initialize();
    }
    

    @ReactMethod
    public void startService(Promise promise) {
        Intent service = new Intent(this.context, MainService.class);
        Bundle bundle = new Bundle();

        bundle.putString("State", "start");
        service.putExtras(bundle);

        this.context.startService(service);
    }

    @ReactMethod
    public void stopService(Promise promise) {
        Intent service = new Intent(this.context, MainService.class);
        Bundle bundle = new Bundle();

        bundle.putString("State", "stop");
        service.putExtras(bundle);

        this.context.startService(service);
    }

    @ReactMethod
    public void startBackgroundWork() {
        WorkManager.getInstance(this.context).enqueueUniquePeriodicWork("SyncWorker", ExistingPeriodicWorkPolicy.KEEP,workRequest);
    }
    @ReactMethod
    public void stopBackgroundWork() {
        WorkManager.getInstance(this.context).cancelUniqueWork("SyncWorker");
    }


    void registerReceiverNetworkChange (){
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.context.registerReceiver(Receiver, filter);
    }

}