package mo.ed.amit.uberedit.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mo.ed.amit.uberedit.R;
import mo.ed.amit.uberedit.databinding.DriverListItemBinding;
import mo.ed.amit.uberedit.models.Driver;
import mo.ed.amit.uberedit.utils.Constants;

public class RiderAdapter extends RecyclerView.Adapter<RiderAdapter.ViewHOlder> {

    private final Context mContext;
    private final ArrayList<Driver> mDriversArrList;
    private DriverListItemBinding binding;

    public RiderAdapter(Context context, ArrayList<Driver> arrayList) {
        this.mContext=context;
        this.mDriversArrList=arrayList;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.driver_list_item, parent, false);
        return new ViewHOlder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        Driver driver = mDriversArrList.get(position);
        if (driver != null){
            binding.tvUserName.setText(driver.getEMAIL());
            binding.tvVehicleType.setText(driver.getVEHICLE_TYPE());
            binding.tvRatingVal.setText(driver.getRATING_VALUE());
            binding.ratingBar.setRating(Float.valueOf(driver.getRATING_VALUE()));
            binding.distanceVal.setText(driver.getDISTANCE());
            binding.tvEtAVal.setText(driver.getETA());
            binding.tvPriceVal.setText(driver.getPRICE());
            binding.paymentVal.setText(driver.getPAYMENT());
            Picasso.with(mContext).load(driver.getPROFILE_PICTURE())
                    .error(R.drawable.ic_chat)
                    .into(holder.mDriverItemBinding.profilePic);
            binding.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(mContext,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        if (Constants.MapActivity!=null){
                            ActivityCompat.requestPermissions( Constants.MapActivity, new String[]{android.Manifest.permission.CALL_PHONE},
                                    Constants.CALL_REQUEST_CODE);
                        }
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + driver.getPHONE_NUMBER()))
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                }
            });
            binding.btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 3/7/2023 Firebase User ID
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDriversArrList.size();
    }

    class ViewHOlder extends RecyclerView.ViewHolder {
        private final DriverListItemBinding mDriverItemBinding;

        public ViewHOlder(DriverListItemBinding binding) {
            super(binding.getRoot());
            this.mDriverItemBinding = binding;
        }
    }
}
