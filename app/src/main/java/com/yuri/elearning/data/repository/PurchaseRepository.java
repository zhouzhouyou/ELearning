package com.yuri.elearning.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.datasource.LocalPurchaseDataSource;
import com.yuri.elearning.data.datasource.PurchaseDataSource;
import com.yuri.elearning.data.network.datasource.RemotePurchaseDataSource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

public class PurchaseRepository implements PurchaseDataSource {
    private static final String TAG = "PurchaseRepository";
    private final LocalPurchaseDataSource mLocalPurchaseDataSource;
    private final RemotePurchaseDataSource mRemotePurchaseDataSource;

    public PurchaseRepository(AppDatabase appDatabase) {
        mLocalPurchaseDataSource = new LocalPurchaseDataSource(appDatabase);
        mRemotePurchaseDataSource = new RemotePurchaseDataSource();
    }

    @Override
    public void purchase(Context context, PurchaseCallback callback, int cid, int uid) {
        Log.d(TAG, "purchase: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            mRemotePurchaseDataSource.purchase(context, new PurchaseCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(Double s) {
                    UserRepository.MONEY = s;
                    List<Integer> list = new ArrayList<>();
                    list.add(cid);
                    mLocalPurchaseDataSource.insert(list, uid);
                    callback.onSuccess(s);
                }

                @Override
                public void onFailed(String errorMessage) {

                }
            }, cid, uid);
        }
    }
}
