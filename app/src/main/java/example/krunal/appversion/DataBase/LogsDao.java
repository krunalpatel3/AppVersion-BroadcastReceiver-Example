package example.krunal.appversion.DataBase;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface LogsDao {

    @Query("Select * from Logs")
    LiveData<List<LogsEntity>> getAllLogs();

    @Insert
    void InsertLog(LogsEntity logsEntity);

}
