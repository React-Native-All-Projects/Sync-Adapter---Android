package com.syncadapterandroid;
import android.app.Service;  
import android.content.Intent;  
import android.os.IBinder;  
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.os.Handler;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import android.os.Bundle;

public class EventListenerService extends Service {  
    private Handler handler = new Handler();

    @Override  
    public IBinder onBind(Intent intent) {  
        return null;  
    }

    @Override
    public void onCreate() {  
        this.handler.post(this.runnableCode);
    }
    
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            ReactApplicationContext context = ModuleApp.getContext();
            context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("EventListenerService",null);
            handler.postDelayed(this, 2000);
        }
    }; 
}