package com.syncadapterandroid;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import androidx.work.PeriodicWorkRequest;
import java.util.concurrent.TimeUnit;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.WorkManager;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import androidx.work.Constraints;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.provider.ContactsContract;
import com.syncadapterandroid.ContactsContentObserver;
import android.os.Handler;
import android.media.AudioManager;

public class ModuleApp extends ReactContextBaseJavaModule {
    public static ReactApplicationContext context;
    private PeriodicWorkRequest workRequest;
    private String ModuleName = "ModuleApp";

    Constraints constraints = new Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build();

    NetworkReceiver Receiver = new NetworkReceiver();
    ModuleApp(ReactApplicationContext context) {
        super(context);
        this.context = context;
        workRequest = new PeriodicWorkRequest.Builder(SyncWorker.class, 15, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build();

        RegisterReceiverNetworkChange();
    }
    @Override
    public String getName() {
        return ModuleName;
    } 

    @ReactMethod
    public void RunService(Promise promise) {
        Intent service = new Intent(this.context, MainService.class);
        Bundle bundle = new Bundle();

        bundle.putString("State", "start");
        service.putExtras(bundle);

        this.context.startService(service);
    }

    @ReactMethod
    public void RegisterContentObserver(Promise promise) {
        context.getContentResolver().registerContentObserver(
            ContactsContract.Data.CONTENT_URI //used to identify resource
            , true //include all changes or not
            , new ContactsContentObserver(new Handler(),this.context) //your ContentObserver
            );
    }
    @ReactMethod
    public void StartEventListenerService(Promise promise) {
        this.context.startService(new Intent(this.context, EventListenerService.class));
    }

    @ReactMethod
    public void StartBackgroundWork() {
        WorkManager.getInstance(this.context).enqueueUniquePeriodicWork("SyncWorker", ExistingPeriodicWorkPolicy.KEEP,workRequest);
    }
    @ReactMethod
    public void StopBackgroundWork() {
        WorkManager.getInstance(this.context).cancelUniqueWork("SyncWorker");
    }


    void RegisterReceiverNetworkChange (){
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.context.registerReceiver(Receiver, filter);
    }

    @ReactMethod
    public void isMusicActive(Promise promise) {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        promise.resolve(audioManager.isMusicActive());
    }

    @ReactMethod
    public void StopAudio() {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(null,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    @ReactMethod
    public void StartAudio() {
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.abandonAudioFocus(null);    
    }

    public static ReactApplicationContext GetContext(){
        return context;
    }

}