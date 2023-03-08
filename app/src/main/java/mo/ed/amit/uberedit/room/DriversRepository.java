package mo.ed.amit.uberedit.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.room.helper.AppDataBase;

public class DriversRepository {

    public static DriversRepository driversRepository;

    private final AppDataBase mDatabase;

    private MediatorLiveData<List<Driver>> mObservableDrivers;

    public DriversRepository(AppDataBase appDataBase){
        this.mDatabase = appDataBase;
        mObservableDrivers = new MediatorLiveData<>();
        mObservableDrivers.addSource(mDatabase.dao().returnOfflineDrivers(),
                drivers->{
                    if (mDatabase.getDatabaseCreated().getValue()!=null){
                        mObservableDrivers.postValue(drivers);
                    }
                });
    }

    public long insertDrivers(Driver driver){
        return mDatabase.dao().insertDrivers(driver);
    }

    public int deleteDrivers(){
        return mDatabase.dao().deleteOfflineDrivers();
    }

    public LiveData<List<Driver>> returnDrivers(){
        return mDatabase.dao().returnOfflineDrivers();
    }
}