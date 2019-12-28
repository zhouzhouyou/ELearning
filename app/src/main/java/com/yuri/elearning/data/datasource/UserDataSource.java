package com.yuri.elearning.data.datasource;

import android.content.Context;

import com.yuri.elearning.model.User;

import java.util.List;

public interface UserDataSource {
    void login(Context context, LoginCallback callback, String username, String password);

    void signUp(Context context,SignUpCallback callback, String username, String password);

    void didIBought(Context context, DidIBoughtCallback callback, int uid, int cid);

    void getBought(Context context, GetBoughtCallback callback, int uid);

    void recharge(Context context, RechargeCallback callback, int uid, Double money);

    interface LoginCallback extends BasicCallback<User> {}

    interface SignUpCallback extends BasicCallback<Integer> {}

    interface GetBoughtCallback extends BasicCallback<List<Integer>> {}

    interface DidIBoughtCallback extends BasicCallback<Boolean>{}

    interface RechargeCallback extends BasicCallback<Double> {}
}
