package mo.ed.amit.uberedit.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import mo.ed.amit.uberedit.R;
import mo.ed.amit.uberedit.databinding.ActivityAuthenticationBinding;
import mo.ed.amit.uberedit.utils.Constants;
import mo.ed.amit.uberedit.utils.GoogleConfigs;
import mo.ed.amit.uberedit.utils.network.VerifyConnection;

public class AuthenticationActivity extends AppCompatActivity {

    private ActivityAuthenticationBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private String mEmail;
    private String mPassword;
    private VerifyConnection verifyConnection;
    private boolean isDriver=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
        verifyConnection = new VerifyConnection(getApplicationContext());
        firebaseAuth= FirebaseAuth.getInstance();
        if (mDatabase == null){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            mDatabase =database.getReference(Constants.USERS_KEY);
        }
//        if (firebaseAuth != null && firebaseAuth.getCurrentUser() != null){
//            startActivity(new Intent(AuthenticationActivity.this, MapActivity.class));
//        }

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEnteredData();
                if (mEmail.length() > 0 && mPassword.length() > 0) {
                    if (verifyConnection.isConnected()) {
                        LoginFirebase(mEmail, mPassword);
                    }
                }
            }
        });

        binding.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDriver=isChecked;
            }
        });

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEnteredData();
                if (mEmail.length() > 0 && mPassword.length() > 0) {
                    if (verifyConnection.isConnected()) {
                        createFirebaseAccount(mEmail, mPassword, isDriver);
                    }
                }
            }
        });
    }

    private void LoginFirebase(String mEmail, String mPassword) {
        firebaseAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                String userID = firebaseUser.getUid();
                                GoogleConfigs.FirebaseUserID = userID;
                                startActivity(new Intent(AuthenticationActivity.this, MapActivity.class));
                                Log.e("FirebaseUserID","FirebaseUserID: "+ userID);
                                enableButtons();
                            }else {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.email_verify), Toast.LENGTH_LONG).show();
                                enableButtons();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_LONG).show();
                            enableButtons();
                        }
                    }
                });
    }

    private void enableButtons() {
        binding.btnSignIn.setEnabled(true);
        binding.etPassword.setEnabled(true);
        binding.etEmail.setEnabled(true);
    }

    private void createFirebaseAccount(String mEmail, String mPassword, boolean isDriver) {
        firebaseAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser()
                                        .sendEmailVerification() /// send email to the user
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                                                    String userID = firebaseUser.getUid();
                                                    GoogleConfigs.FirebaseUserID=userID;
                                                    // save logged-in User's info
                                                    mDatabase = FirebaseDatabase.getInstance()
                                                            .getReference(GoogleConfigs.YUSOR_CHAT)
                                                            .child(GoogleConfigs.USERS)
                                                            .child(userID);

                                                    HashMap<String, String> hashMap =new HashMap<>();
                                                    hashMap.put(GoogleConfigs.FIREBASE_USER_ID, userID);
                                                    hashMap.put(GoogleConfigs.EMAIL, mEmail);
                                                    hashMap.put(GoogleConfigs.ProfilePic, Constants.ProfilePicValue);
                                                    hashMap.put(GoogleConfigs.Status, "offline");
                                                    hashMap.put(GoogleConfigs.isDriver, String.valueOf(isDriver));
                                                    hashMap.put(GoogleConfigs.LATITUDE,"29.995525");
                                                    hashMap.put(GoogleConfigs.LONGITUDE,"30.964608");
                                                    hashMap.put(GoogleConfigs.VEHICLE_TYPE,Constants.CAR);
                                                    hashMap.put(GoogleConfigs.RATING_VALUE, Constants.RATING);
                                                    hashMap.put(GoogleConfigs.PHONE_NUMBER, Constants.Phone);
                                                    hashMap.put(GoogleConfigs.PRICE, Constants.Price);
                                                    hashMap.put(GoogleConfigs.DISTANCE, Constants.Distance);
                                                    hashMap.put(GoogleConfigs.ETA, Constants.ETA_VALUE);
                                                    hashMap.put(GoogleConfigs.PAYMENT, Constants.PAYMENT);

                                                    mDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                            Toast.makeText(AuthenticationActivity.this, "Successful Registration! Login Please!",
                                                                    Toast.LENGTH_SHORT).show();
                                                            binding.btnSignUp.setVisibility(View.GONE);
                                                            enableButtons();
                                                            Log.e("FirebaseUserID","FirebaseUserID: "+ user.getUid());
                                                        }
                                                    });
                                                }else {
                                                    Toast.makeText(getApplicationContext(), task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                                                    Log.e("LOG_TAG", "Error ******** Error reason : "+ task.getException().getMessage().toString());
                                                }
                                            }
                                        });
                            }
                        }else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void getEnteredData() {
        binding.btnSignIn.setEnabled(false);
        mEmail = binding.etEmail.getText().toString();
        binding.etEmail.setEnabled(false);
        mPassword = binding.etPassword.getText().toString();
        binding.etPassword.setEnabled(false);
        if (TextUtils.isEmpty(mEmail)) {
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.enter_email), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.enter_password), Toast.LENGTH_SHORT).show();
            return;
        }
    }
}