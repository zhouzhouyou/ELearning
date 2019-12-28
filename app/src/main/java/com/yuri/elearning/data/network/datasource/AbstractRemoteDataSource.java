package com.yuri.elearning.data.network.datasource;

import com.yuri.elearning.data.network.ApiInterface;
import com.yuri.elearning.data.network.NetTool;

public abstract class AbstractRemoteDataSource {
    protected final String TAG = getClass().getSimpleName();
    protected final ApiInterface mApiInterface = NetTool.getNetTool().create(ApiInterface.class);

}
