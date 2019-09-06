package example.krunal.appversion;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ClsGlobal {

    public static final String AppPackage = "example.krunal.appversion";
    private static final int REQUEST_CODE=12;


    public static String getEntryDateFormat(String e_Date) {
        final String DATE_DASH_FORMAT = "dd/MM/yyyy hh:mm aa";
        final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        try {
            if (e_Date != null && !e_Date.isEmpty() && e_Date != "") {
                Date date = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH).parse(e_Date);
                DateFormat formatter = new SimpleDateFormat(DATE_DASH_FORMAT, Locale.getDefault());
                e_Date = formatter.format(date.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return e_Date;
    }

    public static String getCurruntDateTime() {
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static void showNotification(Context context, String title, String content)
    {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(REQUEST_CODE, notification);

    }


}
