package mo.ed.amit.uberedit.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mo.ed.amit.uberedit.R;
import mo.ed.amit.uberedit.databinding.ActivityMapBinding;
import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.room.DriversDao;
import mo.ed.amit.uberedit.room.helper.AppDataBase;
import mo.ed.amit.uberedit.room.helper.AppExecutors;
import mo.ed.amit.uberedit.room.helper.background.BackgroundInsert;
import mo.ed.amit.uberedit.utils.network.VerifyConnection;
import mo.ed.amit.uberedit.utils.Constants;
import mo.ed.amit.uberedit.utils.GoogleConfigs;
import mo.ed.amit.uberedit.views.adapters.RiderAdapter;

public class MapActivity extends AppCompatActivity implements BackgroundInsert.OnInsertFinished {

    private ActivityMapBinding binding;
    private VerifyConnection verifyConnection;
    private ArrayList<Driver> mDriversList;
    private ArrayList<Driver> mUsersList;
    private FirebaseUser currentFirebaseUser;
    private DatabaseReference mDatabase;
    private AppDataBase mAppDatabase;
    private AppExecutors appExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        Constants.MapActivity= MapActivity.this;
        appExecutor= new AppExecutors();
        mAppDatabase = AppDataBase.getAppDatabase(getApplicationContext(), appExecutor);
        verifyConnection = new VerifyConnection(getApplicationContext());
        mDriversList=new ArrayList<>();
        mUsersList = new ArrayList<>();

        currentFirebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Log.e("FirebaseUserId: ", currentFirebaseUser.getUid());

        mDatabase = FirebaseDatabase.getInstance().getReference(GoogleConfigs.YUSOR_CHAT).child(GoogleConfigs.USERS);

        if (verifyConnection.isConnected()){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mDriversList.clear();
                    mUsersList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Driver driver = dataSnapshot.getValue(Driver.class);
//                        String isDriverStr= dataSnapshot.child(GoogleConfigs.isDriver).getValue(String.class);
//                        driver.setIS_DRIVER(Boolean.getBoolean(isDriver));

                        boolean isDriver = Boolean.parseBoolean(driver.getIS_DRIVER());

//                        driver.setIS_DRIVER(Boolean.parseBoolean(isDriver));
                        if (isDriver){
                            mDriversList.add(driver);
                        }else {
                            mUsersList.add(driver);
                        }
                    }

                    populateDriversRecyclerView(mDriversList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {

        }

    }

    private void populateDriversRecyclerView(ArrayList<Driver> mDriversList) {
        for (int i = 0; i < mDriversList.size(); i++) {
            Driver driver = mDriversList.get(i);
            BackgroundInsert backgroundInsert = new BackgroundInsert(mAppDatabase, getApplicationContext(),
                    MapActivity.this, driver);
            backgroundInsert.execute();
        }
        RiderAdapter riderAdapter= new RiderAdapter(getApplicationContext(), mDriversList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvDriversList.setLayoutManager(linearLayoutManager);
        binding.rvDriversList.setItemAnimator(new DefaultItemAnimator());
        binding.rvDriversList.setAdapter(riderAdapter);
        riderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInsertSuccess(boolean bo) {
        Log.e("InsertSuccess", String.valueOf(bo));
    }

    @Override
    public void onInsertFailed(boolean bo) {
        Log.e("InsertFailed", String.valueOf(bo));
//        ShowSnackBar(parentLayout, getString(R.string.internal_error));
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ShowSnackBar(parentLayout, getString(R.string.you_can_load_your_cart_in_good_internet_connection));
//            }
//        }, 2500);
    }
}