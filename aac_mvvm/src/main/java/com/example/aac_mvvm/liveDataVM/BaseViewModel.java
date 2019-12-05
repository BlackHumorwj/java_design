package com.example.aac_mvvm.liveDataVM;

/**
 * @time 2019/12/2 19:50
 * @desc
 */
public interface BaseViewModel<T> {

    <T> T loadData();


    void clearData();


}
