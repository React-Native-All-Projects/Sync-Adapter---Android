package com.syncadapterandroid;
import android.content.Context;
import android.database.ContentObserver;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactApplicationContext;
import android.os.Handler;

public class ContactsContentObserver extends ContentObserver {
    private ReactApplicationContext context;

    public ContactsContentObserver(Handler handler, ReactApplicationContext context) {
        super(handler);
        this.context = context;
    }
    
    @Override 
    public boolean deliverSelfNotifications() { 
        return false; 
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("EventListenerContacts",null);
    }
}