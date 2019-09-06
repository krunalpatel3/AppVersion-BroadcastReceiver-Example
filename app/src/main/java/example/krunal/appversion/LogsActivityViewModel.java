package example.krunal.appversion;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import example.krunal.appversion.DataBase.LogsEntity;
import example.krunal.appversion.DataBase.Repository;

public class LogsActivityViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<LogsEntity>> mlist;

    public LogsActivityViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new Repository(application,"LoadList");
        this.mlist = mRepository.getAllLogsList();
    }


    public LiveData<List<LogsEntity>> getLogsList(){
        return mlist;
    }
}
