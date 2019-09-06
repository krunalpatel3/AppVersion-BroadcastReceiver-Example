package example.krunal.appversion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class UpdatePackageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Intent Package Name: " + intent.getPackage(), Toast.LENGTH_LONG).show();

        // Receiver for when app get's update.

        Log.e("onReceive","onReceive call");
        if (intent.getAction().equals(Intent.ACTION_MY_PACKAGE_REPLACED)
                && intent.getPackage().equalsIgnoreCase(context.getPackageName())) {

            try {
                PackageManager pm = context.getPackageManager();
                PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);

                String versionName = info.versionName;
                int versionCode = info.versionCode;

                Toast.makeText(context, "[My Package replaced] name: " + versionName + " code: " + versionCode, Toast.LENGTH_LONG).show();

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

        sendNotification("Update","Update Call","",context);
    }

    public static void sendNotification(String title, String message, String mode, Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //If on Oreo then notification required a notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);

        if (mode.equalsIgnoreCase("Send PDF")) {
            notification.setOngoing(true);
        }

        notificationManager.notify(1, notification.build());
    }
}
