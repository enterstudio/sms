package com.sms.transport;

/**
 * Created by cchiappini on 02/03/2015.
 */
public interface RequestCallback<T> {
    void onRetrieved(T data);

    void onError(String s);
}
