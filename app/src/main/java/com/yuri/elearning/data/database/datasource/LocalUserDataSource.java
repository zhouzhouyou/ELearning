package com.yuri.elearning.data.database.datasource;

import android.content.Context;
import android.os.AsyncTask;

import com.yuri.elearning.data.database.AppDatabase;
import com.yuri.elearning.data.database.dao.PurchaseDao;
import com.yuri.elearning.data.datasource.UserDataSource;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LocalUserDataSource extends AbstractLocalDataSource implements UserDataSource {
    private final PurchaseDao mPurchaseDao;

    public LocalUserDataSource(AppDatabase appDatabase) {
        super(appDatabase);
        mPurchaseDao = appDatabase.purchaseDao();
    }

    @Override
    public void login(Context context, LoginCallback callback, String username, String password) {
        //ignore
    }

    @Override
    public void signUp(Context context, SignUpCallback callback, String username, String password) {
        //ignore
    }

    private static class queryByIdTask extends AsyncTask<Integer, Void, List<Integer>> {
        private final PurchaseDao mPurchaseDao;

        public queryByIdTask(PurchaseDao purchaseDao) {
            mPurchaseDao = purchaseDao;
        }

        @Override
        protected List<Integer> doInBackground(Integer... integers) {
            return mPurchaseDao.selectByUid(integers[0]);
        }
    }

    private static class didIBoughtTask extends AsyncTask<Integer, Void, Boolean> {
        private final PurchaseDao mPurchaseDao;

        public didIBoughtTask(PurchaseDao purchaseDao) {
            mPurchaseDao = purchaseDao;
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            return mPurchaseDao.didIBought(integers[0], integers[1]) != null;
        }
    }

    @Override
    public void didIBought(Context context, DidIBoughtCallback callback, int uid, int cid) {
        Boolean b;
        try {
            b = new didIBoughtTask(mPurchaseDao).execute(uid, cid).get();
            callback.onSuccess(b);
        } catch (ExecutionException | InterruptedException e) {
            callback.onFailed(e.toString());
        }
    }

    private static class getBoughtTask extends AsyncTask<Integer, Void, List<Integer>> {
        private final PurchaseDao mPurchaseDao;

        public getBoughtTask(PurchaseDao purchaseDao) {
            mPurchaseDao = purchaseDao;
        }

        @Override
        protected List<Integer> doInBackground(Integer... integers) {
            return mPurchaseDao.selectByUid(integers[0]);
        }
    }

    @Override
    public void getBought(Context context, GetBoughtCallback callback, int uid) {
        List<Integer> list;
        try {
            list = new getBoughtTask(mPurchaseDao).execute(uid).get();
            callback.onSuccess(list);
        } catch (ExecutionException | InterruptedException e) {
            callback.onFailed(e.toString());
        }
    }

    @Override
    public void recharge(Context context, RechargeCallback callback, int uid, Double money) {
        //ignore
    }
}
