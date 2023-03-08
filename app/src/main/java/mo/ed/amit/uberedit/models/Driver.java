package mo.ed.amit.uberedit.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "DriversTBL")
public class Driver implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "DriverID")
    private int DriverID;

    @NonNull
    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }
    @NonNull
    @ColumnInfo()
    String DISTANCE;

    @NonNull
    @ColumnInfo()
    String EMAIL;

    @NonNull
    @ColumnInfo()
    String ETA;

    @NonNull
    @ColumnInfo()
    String FIREBASE_USER_ID;

    @NonNull
    @ColumnInfo()
    String LATITUDE;

    @NonNull
    @ColumnInfo()
    String LONGITUDE;

    @NonNull
    @ColumnInfo()
    String PAYMENT;

    @NonNull
    @ColumnInfo()
    String PHONE_NUMBER;

    @NonNull
    @ColumnInfo()
    String PRICE;

    @NonNull
    @ColumnInfo()
    String PROFILE_PICTURE;

    @NonNull
    @ColumnInfo()
    String RATING_VALUE;

    @NonNull
    @ColumnInfo()
    String STATUS;

    @NonNull
    @ColumnInfo()
    String VEHICLE_TYPE;

    @NonNull
    @ColumnInfo()
    String IS_DRIVER;

    public Driver() {
    }

    @NonNull
    public String getDISTANCE() {
        return DISTANCE;
    }

    public void setDISTANCE(String DISTANCE) {
        this.DISTANCE = DISTANCE;
    }

    @NonNull
    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    @NonNull
    public String getETA() {
        return ETA;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    @NonNull
    public String getFIREBASE_USER_ID() {
        return FIREBASE_USER_ID;
    }

    public void setFIREBASE_USER_ID(String FIREBASE_USER_ID) {
        this.FIREBASE_USER_ID = FIREBASE_USER_ID;
    }

    @NonNull
    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    @NonNull
    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    @NonNull
    public String getPAYMENT() {
        return PAYMENT;
    }

    public void setPAYMENT(String PAYMENT) {
        this.PAYMENT = PAYMENT;
    }

    @NonNull
    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    @NonNull
    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    @NonNull
    public String getPROFILE_PICTURE() {
        return PROFILE_PICTURE;
    }

    public void setPROFILE_PICTURE(String PROFILE_PICTURE) {
        this.PROFILE_PICTURE = PROFILE_PICTURE;
    }

    @NonNull
    public String getRATING_VALUE() {
        return RATING_VALUE;
    }

    public void setRATING_VALUE(String RATING_VALUE) {
        this.RATING_VALUE = RATING_VALUE;
    }

    @NonNull
    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @NonNull
    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    @NonNull
    public String getIS_DRIVER() {
        return IS_DRIVER;
    }

    public void setIS_DRIVER(String IS_DRIVER) {
        this.IS_DRIVER = IS_DRIVER;
    }

    public Driver(String DISTANCE, String EMAIL, String ETA, String FIREBASE_USER_ID, String LATITUDE, String LONGITUDE, String PAYMENT, String PHONE_NUMBER, String PRICE, String PROFILE_PICTURE, String RATING_VALUE, String STATUS, String VEHICLE_TYPE, String IS_DRIVER) {
        this.DISTANCE = DISTANCE;
        this.EMAIL = EMAIL;
        this.ETA = ETA;
        this.FIREBASE_USER_ID = FIREBASE_USER_ID;
        this.LATITUDE = LATITUDE;
        this.LONGITUDE = LONGITUDE;
        this.PAYMENT = PAYMENT;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.PRICE = PRICE;
        this.PROFILE_PICTURE = PROFILE_PICTURE;
        this.RATING_VALUE = RATING_VALUE;
        this.STATUS = STATUS;
        this.VEHICLE_TYPE = VEHICLE_TYPE;
        this.IS_DRIVER = IS_DRIVER;
    }
}
