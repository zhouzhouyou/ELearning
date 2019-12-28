package com.yuri.elearning.data.datasource;

import android.content.Context;

public interface PurchaseDataSource {
    void purchase(Context context, PurchaseCallback callback, int cid, int uid);

    interface PurchaseCallback extends BasicCallback<Double>{}
}
