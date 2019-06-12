package com.morristaedt.mirror.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryModule {
    public interface BatteryListener {
        void onBatteryLevelChange(int level);
    }

    private BatteryListener listener;
    private BatteryLevelReceiver receiver;

    public BatteryModule(Context context, BatteryListener listener) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        receiver = new BatteryLevelReceiver();
        context.registerReceiver(receiver, filter);
        this.listener = listener;
    }

    public BatteryLevelReceiver getReceiver() {
        return receiver;
    }

    public class BatteryLevelReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (listener != null) {
                int rawLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (rawLevel >= 0 && scale > 0) {
                    level = (rawLevel * 100) / scale;
                }
                if (level >= 0) {
                    listener.onBatteryLevelChange(level);
                }
            }
        }
    }
}
