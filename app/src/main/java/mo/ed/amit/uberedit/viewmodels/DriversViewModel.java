package mo.ed.amit.uberedit.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.room.DriversDao;
import mo.ed.amit.uberedit.room.DriversRepository;
import mo.ed.amit.uberedit.room.helper.AppDataBase;
import mo.ed.amit.uberedit.room.helper.AppExecutors;

public class DriversViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<Driver>> mDriversMediatorLiveData;
    private final DriversRepository driversRepository;
    private AppDataBase mDatabase;
    private final AppExecutors mAppExecutors;

    public DriversViewModel(@NonNull Application application) {
        super(application);
        this.mDriversMediatorLiveData = new MediatorLiveData<>();
        this.mDriversMediatorLiveData.setValue(null);

        mDatabase = new AppDataBase() {
            @Override
            public DriversDao dao() {
                return null;
            }
        };
        mAppExecutors = new AppExecutors();
        driversRepository = new DriversRepository(AppDataBase.getAppDatabase(application.getApplicationContext(),
                mAppExecutors));
    }

    public long insertDrivers(Driver driver){
        return driversRepository.insertDrivers(driver);
    }

    public int deleteDrivers(){
        return driversRepository.deleteDrivers();
    }

    public LiveData<List<Driver>> returnDrivers(){
        return driversRepository.returnDrivers();
    }
}
