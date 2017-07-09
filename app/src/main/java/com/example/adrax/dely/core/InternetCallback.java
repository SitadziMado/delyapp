package com.example.adrax.dely.core;

/**
 * Created by Максим on 09.07.2017.
 */

public interface InternetCallback<TResult> {
    void call(TResult result);
}
