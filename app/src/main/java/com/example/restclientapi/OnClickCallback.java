package com.example.restclientapi;

/**
 * Created by Ashish Virani on 11/14/2017.
 * ashishvirani29@gmail.com
 */

public interface OnClickCallback<T, P, A,V> {
    void onClickCallBack(T t, P p, A a, V v);
}
