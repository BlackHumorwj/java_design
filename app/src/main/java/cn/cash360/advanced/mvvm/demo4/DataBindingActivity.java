package cn.cash360.advanced.mvvm.demo4;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.cash360.java_design.R;
import cn.cash360.java_design.databinding.MvvmDataBindingLayoutBinding;

/**
 * @time 2019/9/3 15:03
 * @desc
 */
public class DataBindingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MvvmDataBindingLayoutBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.mvvm_data_binding_layout);


    }
}
