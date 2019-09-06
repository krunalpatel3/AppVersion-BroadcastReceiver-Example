package example.krunal.appversion;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
        implements InstallStateUpdatedListener {

    SharedPreferenceTime mSharedPreferenceTime;
    int hour, min;
    TextView set_Time;
    Button button, button2;
    private Calendar c;
    private int mHour, mMinute;
    private AppUpdateManager appUpdateManager;
    private final int REQUEST_APP_UPDATE = 293;
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        set_Time = findViewById(R.id.set_Time);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        // ------------------------- This is to show Update popup ---------------------------//

        // Creates instance of the manager.
        appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);
        appUpdateManager.registerListener(this);
        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // For a flexible update, use AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.

                // Checks that the platform will allow the specified type of update.
                if (appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // Request the update.
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo,
                                AppUpdateType.IMMEDIATE,
                                this,
                                REQUEST_APP_UPDATE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackbarForCompleteUpdate();
                } else {
                    Log.e(TAG, "checkForAppUpdateAvailability: something else");
                }
            }
        });

        // ------------------------- This is to show Update popup End---------------------------//

        mSharedPreferenceTime = new SharedPreferenceTime(getApplication());

//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
//        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
//        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
//        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
//        intentFilter.addDataScheme("example.krunal.appversion");
//
//        registerReceiver(updatePackageReceiver, intentFilter);

        set_Time.setOnClickListener(view -> {

            // Get Current Time
            c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view1, hourOfDay, minute) -> {

                        SetupdateTime(hourOfDay, minute);

                    }, mHour, mMinute, false);

            timePickerDialog.show();

        });

        hour = mSharedPreferenceTime.get_hour();
        min = mSharedPreferenceTime.get_min();
        Log.e("TimeInHoursAndMin", hour + String.valueOf(min));

        DisplayTime(hour, min);


        button.setOnClickListener(v -> {

//            PeriodicWorkRequest periodicWorkRequest =
//                    new PeriodicWorkRequest.Builder(AutoWorker.class,
//                            1, TimeUnit.DAYS)
//                            .build();
//
//            WorkManager.getInstance().enqueueUniquePeriodicWork(ClsGlobal.AppPackage.
//                            concat(".AutoWorkerTask")
//                    , ExistingPeriodicWorkPolicy.KEEP
//                    , periodicWorkRequest);

        });


        button2.setOnClickListener(v -> {
//            startActivity(new Intent(MainActivity.this, LogsActivity.class));
        });

    }


    private void SetupdateTime(int hours, int mins) {

        Log.e("TimeInHoursAndMin", String.valueOf(hours) + mins);

        Log.e("HourMinInMillisecond", String.valueOf(TimeUnit.HOURS.toMillis((long) hours)
                + TimeUnit.MINUTES.toMillis((long) mins)));

        mSharedPreferenceTime.set_hour(hours);
        mSharedPreferenceTime.set_min(mins);


        Scheduler.setReminder(MainActivity.this, AlarmTask.class
                , mSharedPreferenceTime.get_hour(), mSharedPreferenceTime.get_min());


        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12) {
            timeSet = "PM";
        }else {
            timeSet = "AM";
        }
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        set_Time.setText(aTime);
    }


    private void DisplayTime(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12) {
            timeSet = "PM";
        } else {
            timeSet = "AM";
        }
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();


        set_Time.setText(aTime);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e(TAG, "onActivityResult: app download failed");
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

//        appUpdateManager
//                .getAppUpdateInfo()
//                .addOnSuccessListener(
//                        appUpdateInfo -> {
//
//                            if (appUpdateInfo.updateAvailability()
//                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
//                                // If an in-app update is already running, resume the update.
//                                try {
//                                    appUpdateManager.startUpdateFlowForResult(
//                                            appUpdateInfo,
//                                            AppUpdateType.IMMEDIATE,
//                                            this,
//                                            REQUEST_APP_UPDATE);
//                                } catch (IntentSender.SendIntentException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        appUpdateManager.unregisterListener(this);
//        appUpdateManager.unregisterListener();
    }


    /* Displays the snackbar notification and call to action. */
    private void popupSnackbarForCompleteUpdate() {
        Snackbar snackbar =
                Snackbar.make(
                        findViewById(R.id.main_layout),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("RESTART", view ->
                appUpdateManager.completeUpdate());

        snackbar.setActionTextColor(
                getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void onStateUpdate(InstallState installState) {

        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            popupSnackbarForCompleteUpdate();
        } else if (installState.installStatus() == InstallStatus.INSTALLED) {
            if (appUpdateManager != null) {
                appUpdateManager.unregisterListener(this);
            }

        } else {
            Log.i(TAG, "InstallStateUpdatedListener: state: " + installState.installStatus());
        }
    }



}
