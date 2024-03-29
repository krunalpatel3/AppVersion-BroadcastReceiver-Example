package example.krunal.appversion.DataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {LogsEntity.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LogsDao getLogsDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context context){
        String dbname= "db";
        return Room.databaseBuilder(context,AppDatabase.class,dbname).build();
    }

}
