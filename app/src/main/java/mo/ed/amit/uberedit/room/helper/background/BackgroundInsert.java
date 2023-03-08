package mo.ed.amit.uberedit.room.helper.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.room.helper.AppDataBase;

public class BackgroundInsert extends AsyncTask<Void, Void, Boolean> {

    private final Context mContext;
    private final ProgressDialog mDialog;
    private final OnInsertFinished onInsertFinished;
    private final AppDataBase appDatabase;
    private final Driver mDriverItem;

    public BackgroundInsert(AppDataBase appDataBase, Context context, OnInsertFinished onInsertFinished, Driver driver) {
        this.mContext = context;
        mDialog = new ProgressDialog(context);
        this.onInsertFinished = onInsertFinished;
        this.mDriverItem = driver;
        this.appDatabase = appDataBase;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        long result = this.appDatabase.dao().insertDrivers(mDriverItem);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);
        if (status) {
            onInsertFinished.onInsertSuccess(status);
        } else {
            onInsertFinished.onInsertFailed(status);
        }
    }

    public interface OnInsertFinished {
        public void onInsertSuccess(boolean bo);

        public void onInsertFailed(boolean bo);
    }
}
