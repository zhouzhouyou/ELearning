package com.yuri.elearning.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.datasource.LocalPurchaseDataSource;
import com.yuri.elearning.data.database.datasource.LocalUserDataSource;
import com.yuri.elearning.data.datasource.UserDataSource;
import com.yuri.elearning.data.network.datasource.RemoteUserDataSource;
import com.yuri.elearning.model.User;

import java.util.List;

import androidx.annotation.RequiresApi;

public class UserRepository implements UserDataSource {
    public static String NAME;
    private static final String TAG = "UserRepository";
    public static Integer ID;
    public static Double MONEY;

    private final RemoteUserDataSource mRemoteUserDataSource;
    private final LocalUserDataSource mLocalUserDataSource;
    private final LocalPurchaseDataSource mLocalPurchaseDataSource;

    public UserRepository(AppDatabase appDatabase) {
        mRemoteUserDataSource = new RemoteUserDataSource();
        mLocalUserDataSource = new LocalUserDataSource(appDatabase);
        mLocalPurchaseDataSource = new LocalPurchaseDataSource(appDatabase);
    }

    @Override
    public void login(Context context, LoginCallback callback, String username, String password) {
        Log.d(TAG, "login: ");
        mRemoteUserDataSource.login(context, new LoginCallback() {
            @Override
            public void onSuccess(User user) {
                callback.onSuccess(user);
                ID = user.id;
                MONEY = user.money;
                getBought(context, new GetBoughtCallback() {
                    @Override
                    public void onSuccess(List<Integer> list) {
                    }

                    @Override
                    public void onFailed(String errorMessage) {
                    }
                }, ID);
            }

            @Override
            public void onFailed(String errorMessage) {
                callback.onFailed(errorMessage);
            }
        }, username, password);
    }

    @Override
    public void signUp(Context context, SignUpCallback callback, String username, String password) {
        Log.d(TAG, "signUp: ");
        mRemoteUserDataSource.signUp(context, new SignUpCallback() {
            @Override
            public void onSuccess(Integer integer) {
                callback.onSuccess(integer);
                ID = integer;
                MONEY = 0.0;
            }

            @Override
            public void onFailed(String errorMessage) {
                callback.onFailed(errorMessage);
            }
        }, username, password);
    }


    @Override
    public void didIBought(Context context, DidIBoughtCallback callback, int uid, int cid) {
        mLocalUserDataSource.didIBought(context, new DidIBoughtCallback() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                callback.onSuccess(aBoolean);
            }

            @Override
            public void onFailed(String errorMessage) {
                callback.onFailed(errorMessage);
            }
        }, uid, cid);
    }

    @Override
    public void getBought(Context context, GetBoughtCallback callback, int uid) {
        Log.d(TAG, "getBought: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            mRemoteUserDataSource.getBought(context, new GetBoughtCallback() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(List<Integer> list) {
                    callback.onSuccess(list);
                    mLocalPurchaseDataSource.insert(list, uid);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, uid);
        } else {
            mLocalUserDataSource.getBought(context, new GetBoughtCallback() {
                @Override
                public void onSuccess(List<Integer> list) {
                    callback.onSuccess(list);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, uid);
        }
    }

    @Override
    public void recharge(Context context, RechargeCallback callback, int uid, Double money) {
        Log.d(TAG, "recharge: ");
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            mRemoteUserDataSource.recharge(context, new RechargeCallback() {
                @Override
                public void onSuccess(Double aDouble) {
                    UserRepository.MONEY = aDouble;
                    callback.onSuccess(aDouble);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            }, uid, money);
        }
    }
}
