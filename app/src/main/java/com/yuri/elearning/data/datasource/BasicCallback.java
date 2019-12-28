package com.yuri.elearning.data.datasource;

public interface BasicCallback<T> {
    void onSuccess(T t);
    void onFailed(String errorMessage);
}
