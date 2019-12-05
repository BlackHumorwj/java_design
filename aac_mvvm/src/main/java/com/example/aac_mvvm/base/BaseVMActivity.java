package com.example.aac_mvvm.base;

import android.os.Bundle;

import java.lang.reflect.ParameterizedType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * @time 2019/12/2 20:03
 * @desc
 */
public class BaseVMActivity<VM extends ViewModel> extends AppCompatActivity {

    protected VM mVM;
    protected Class<VM> mViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createViewModel();
    }

    private void createViewModel() {

        mViewModel = newClass(this,0);

        //添加对ViewModel的监听
        mVM = ViewModelProviders.of(this).get(mViewModel);
    }


    private <T> Class<T> newClass(Object object, int index) {
        ParameterizedType parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[index];
    }

    private <T> T _newClass(Object object, T type, int index) {
        ParameterizedType parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();
        Class<T> tClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        try {
            return tClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
