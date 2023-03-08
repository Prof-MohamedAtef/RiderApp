package mo.ed.amit.uberedit.room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import mo.ed.amit.uberedit.models.Driver;

@androidx.room.Dao
public interface DriversDao {
    @Insert
    long insertDrivers(Driver driver);

    @Query("SELECT * FROM DriversTBL")
    LiveData<List<Driver>> returnOfflineDrivers();

    @Query("DELETE FROM DriversTBL")
    int deleteOfflineDrivers();
}
