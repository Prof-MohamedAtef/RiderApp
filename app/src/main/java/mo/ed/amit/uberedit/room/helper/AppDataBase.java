package mo.ed.amit.uberedit.room.helper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.room.DriversDao;

@Database(entities = {Driver.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;
    private static String DATABASE_NAME = "UBER-AMIT-DB";
    public abstract DriversDao dao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public static AppDataBase getAppDatabase(Context context, final AppExecutors appExecutors){
        if (INSTANCE == null){
            try
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
                AppDataBase appDataBase = AppDataBase.getInstance(context, appExecutors);
                appDataBase.setDatabaseCreated();
            }catch (Exception e){

            }
        }
        return INSTANCE;
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }


    public static AppDataBase getInstance(final Context context, final AppExecutors executors) {
        if (INSTANCE==null){
            synchronized (AppDataBase.class){
                if (INSTANCE==null){
                    INSTANCE=getAppDatabase(context,executors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }
}
