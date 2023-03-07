package mo.ed.amit.uberedit.models;

public class Driver {
    String DISTANCE, EMAIL, ETA, FIREBASE_USER_ID, LATITUDE, LONGITUDE, PAYMENT, PHONE_NUMBER, PRICE, PROFILE_PICTURE, RATING_VALUE, STATUS, VEHICLE_TYPE;
    boolean isDriver;

    public Driver() {
    }

    public String getDISTANCE() {
        return DISTANCE;
    }

    public void setDISTANCE(String DISTANCE) {
        this.DISTANCE = DISTANCE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getETA() {
        return ETA;
    }

    public void setETA(String ETA) {
        this.ETA = ETA;
    }

    public String getFIREBASE_USER_ID() {
        return FIREBASE_USER_ID;
    }

    public void setFIREBASE_USER_ID(String FIREBASE_USER_ID) {
        this.FIREBASE_USER_ID = FIREBASE_USER_ID;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getPAYMENT() {
        return PAYMENT;
    }

    public void setPAYMENT(String PAYMENT) {
        this.PAYMENT = PAYMENT;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getPROFILE_PICTURE() {
        return PROFILE_PICTURE;
    }

    public void setPROFILE_PICTURE(String PROFILE_PICTURE) {
        this.PROFILE_PICTURE = PROFILE_PICTURE;
    }

    public String getRATING_VALUE() {
        return RATING_VALUE;
    }

    public void setRATING_VALUE(String RATING_VALUE) {
        this.RATING_VALUE = RATING_VALUE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setIS_DRIVER(boolean IS_DRIVER) {
        this.isDriver = IS_DRIVER;
    }

    public Driver(String DISTANCE, String EMAIL, String ETA, String FIREBASE_USER_ID, String LATITUDE, String LONGITUDE, String PAYMENT, String PHONE_NUMBER, String PRICE, String PROFILE_PICTURE, String RATING_VALUE, String STATUS, String VEHICLE_TYPE) {
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
    }
}
