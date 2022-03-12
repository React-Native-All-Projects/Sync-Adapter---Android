package com.syncadapterandroid;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import javax.annotation.Nullable;

public class NetworkService extends HeadlessJsTaskService {


 @Override
  protected @Nullable HeadlessJsTaskConfig getTaskConfig(Intent intent) {
    Bundle extras = intent.getExtras();
    if (extras != null) {
      return new HeadlessJsTaskConfig(
          "NetworkService",
          Arguments.fromBundle(extras),
          10000, // timeout for the task
          true  // optional: defines whether or not  the task is allowed in foreground. Default is false
        );
    }
    return null;
  }
  
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}