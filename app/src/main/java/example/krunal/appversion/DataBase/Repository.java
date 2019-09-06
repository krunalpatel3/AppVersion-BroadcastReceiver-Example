package example.krunal.appversion.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import java.util.List;

public class Repository {

    private LiveData<List<LogsEntity>> mLogsList;
    private AppDatabase mAppDatabase;


    public Repository(Context context,String mode) {
        this.mAppDatabase = AppDatabase.getInstance(context);

        if (mode.equalsIgnoreCase("LoadList")){
            this.mLogsList = mAppDatabase.getLogsDao().getAllLogs();
        }

    }


    public void Insert(LogsEntity logsEntity) {

        Thread thread = new Thread(() ->
                mAppDatabase.getLogsDao().InsertLog(logsEntity));

        thread.start();

    }

    public LiveData<List<LogsEntity>> getAllLogsList() {
        return mLogsList;

    }


}
