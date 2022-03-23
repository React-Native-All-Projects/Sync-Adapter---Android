package com.syncadapterandroid;
import android.app.Service;  
import android.content.Intent;  
import android.os.IBinder;  
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android.os.Handler;
import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import android.os.Binder;

public class EventListenerService extends Service {  
    private Handler handler = new Handler();
    private final IBinder myBinder = new LocalService();

    @Override  
    public IBinder onBind(Intent intent) {  
        return myBinder;  
    }

    @Override
    public void onCreate() {  
        this.handler.post(this.runnableCode);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    private Runnable runnableCode = new Runnable() {
        ReactApplicationContext context = ModuleApp.GetContext();
        @Override
        public void run() {
            context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("EventListenerService",null);
            handler.postDelayed(this, 2000);
        }
    };

    public class LocalService extends Binder{
        EventListenerService getService(){
            return EventListenerService.this;
        }
    } 
}