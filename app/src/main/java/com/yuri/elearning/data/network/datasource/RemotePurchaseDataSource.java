package com.yuri.elearning.data.network.datasource;

import android.content.Context;

import com.yuri.elearning.data.datasource.PurchaseDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemotePurchaseDataSource extends AbstractRemoteDataSource implements PurchaseDataSource {
    @Override
    public void purchase(Context context, PurchaseCallback callback, int cid, int uid) {
        Call<Double> call = mApiInterface.purchase(cid, uid);
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailed("no money!");
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
