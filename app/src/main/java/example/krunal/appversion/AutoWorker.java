package example.krunal.appversion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import example.krunal.appversion.DataBase.LogsEntity;
import example.krunal.appversion.DataBase.Repository;

import static example.krunal.appversion.ClsGlobal.getCurruntDateTime;
import static example.krunal.appversion.ClsGlobal.getEntryDateFormat;
import static example.krunal.appversion.ClsGlobal.showNotification;

public class AutoWorker extends Worker {

    private Context context;


    public AutoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        showNotification(context,"AutoWorker","Call From AutoWorker At Time: "+
                getEntryDateFormat(getCurruntDateTime()));

        new Repository(context,"No").Insert(new LogsEntity("AutoWorker",
                "Call From AutoWorker At Time: " + getEntryDateFormat(getCurruntDateTime())));


        return Result.success();
    }




}
