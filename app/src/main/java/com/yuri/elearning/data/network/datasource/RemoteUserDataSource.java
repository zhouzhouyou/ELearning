package com.yuri.elearning.data.network.datasource;

import android.content.Context;
import android.util.Log;

import com.yuri.elearning.data.datasource.UserDataSource;
import com.yuri.elearning.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteUserDataSource extends AbstractRemoteDataSource implements UserDataSource {
    @Override
    public void login(Context context, LoginCallback callback, String username, String password) {
        Log.d(TAG, "login: ");
        Call<User> call = mApiInterface.signIn(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailed("Not valid");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                    callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void signUp(Context context, SignUpCallback signUpCallback, String username, String password) {

    }

    @Override
    public void didIBought(Context context, DidIBoughtCallback callback, int uid, int cid) {
        //ignore
    }

    @Override
    public void getBought(Context context, GetBoughtCallback callback, int uid) {
        Log.d(TAG, "getBought: ");
        Call<List<Integer>> call = mApiInterface.bought(uid);
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }

    @Override
    public void recharge(Context context, RechargeCallback callback, int uid, Double money) {
        Log.d(TAG, "recharge: ");
        Call<Double> call = mApiInterface.recharge(money, uid);
        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
