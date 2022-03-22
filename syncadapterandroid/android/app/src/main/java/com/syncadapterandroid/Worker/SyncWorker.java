package com.syncadapterandroid;
import android.content.Context;
import android.os.Bundle;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Intent;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class SyncWorker extends Worker {
    private Context context;

    public SyncWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @Override
    public Result doWork() {
            Intent service = new Intent(this.context, MainService.class);
            Bundle bundle = new Bundle();
            bundle.putString("State", "start");
            service.putExtras(bundle);

            this.context.startService(service);
        return Result.success();
    }
}