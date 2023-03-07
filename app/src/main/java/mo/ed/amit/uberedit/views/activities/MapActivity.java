package mo.ed.amit.uberedit.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

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
import mo.ed.amit.uberedit.network.VerifyConnection;
import mo.ed.amit.uberedit.utils.Constants;
import mo.ed.amit.uberedit.utils.GoogleConfigs;
import mo.ed.amit.uberedit.views.adapters.RiderAdapter;

public class MapActivity extends AppCompatActivity {

    private ActivityMapBinding binding;
    private VerifyConnection verifyConnection;
    private ArrayList<Driver> mDriversList;
    private ArrayList<Driver> mUsersList;
    private FirebaseUser currentFirebaseUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        Constants.MapActivity= MapActivity.this;
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
                        String isDriver= dataSnapshot.child(GoogleConfigs.isDriver).getValue(String.class);
//                        driver.setIS_DRIVER(Boolean.getBoolean(isDriver));
                        driver.setIS_DRIVER(Boolean.parseBoolean(isDriver));
                        if (driver.isDriver()){
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
        RiderAdapter riderAdapter= new RiderAdapter(getApplicationContext(), mDriversList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.rvDriversList.setLayoutManager(linearLayoutManager);
        binding.rvDriversList.setItemAnimator(new DefaultItemAnimator());
        binding.rvDriversList.setAdapter(riderAdapter);
        riderAdapter.notifyDataSetChanged();
    }
}