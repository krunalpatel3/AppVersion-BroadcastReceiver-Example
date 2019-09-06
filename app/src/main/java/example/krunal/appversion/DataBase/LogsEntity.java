package example.krunal.appversion.DataBase;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Logs")
public class LogsEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "status")
    private String status;

    @Ignore
    public LogsEntity(int id,String title,String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public LogsEntity(String title,String status) {
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }
}
