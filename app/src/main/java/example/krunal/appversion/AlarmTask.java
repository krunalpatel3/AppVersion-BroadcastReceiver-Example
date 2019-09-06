package example.krunal.appversion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import example.krunal.appversion.DataBase.LogsEntity;
import example.krunal.appversion.DataBase.Repository;

import static example.krunal.appversion.ClsGlobal.getCurruntDateTime;
import static example.krunal.appversion.ClsGlobal.getEntryDateFormat;

public class AlarmTask extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d("onReceive", "onReceive: BOOT_COMPLETED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                SharedPreferenceTime localData = new SharedPreferenceTime(context);
                Scheduler.setReminder(context, AlarmTask.class, localData.get_hour(), localData.get_min());
                return;
            }

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_TIME_CHANGED)) {
                // Set the alarm here.
                Log.d("onReceive", "onReceive: ACTION_TIME_CHANGED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                SharedPreferenceTime localData = new SharedPreferenceTime(context);
                Scheduler.setReminder(context, AlarmTask.class, localData.get_hour(), localData.get_min());
                return;
            }

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_TIMEZONE_CHANGED)) {
                // Set the alarm here.
                Log.d("onReceive", "onReceive: ACTION_TIMEZONE_CHANGED");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                SharedPreferenceTime localData = new SharedPreferenceTime(context);
                Scheduler.setReminder(context, AlarmTask.class, localData.get_hour(), localData.get_min());
                return;
            }

            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_REBOOT)) {
                // Set the alarm here.
                Log.d("onReceive", "onReceive: ACTION_REBOOT");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                SharedPreferenceTime localData = new SharedPreferenceTime(context);
                Scheduler.setReminder(context, AlarmTask.class, localData.get_hour(), localData.get_min());
                return;
            }

            if (intent.getAction().equalsIgnoreCase("android.intent.action.QUICKBOOT_POWERON")) {
                // Set the alarm here.
                Log.d("onReceive", "onReceive: android.intent.action.QUICKBOOT_POWERON");
//                Toast.makeText(context,"After Boot",Toast.LENGTH_SHORT).show();
                SharedPreferenceTime localData = new SharedPreferenceTime(context);
                Scheduler.setReminder(context, AlarmTask.class, localData.get_hour(), localData.get_min());
                return;
            }
        }


        ClsGlobal.showNotification(context, "AlarmTask", "AlarmTask call At Time: " +
                getEntryDateFormat(getCurruntDateTime()));


        new Repository(context, "No").Insert(new LogsEntity("AlarmTask",
                "AlarmTask call At Time: " + getEntryDateFormat(getCurruntDateTime())));

    }
}
