package com.example.aac_mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.aac_mvvm.base.BaseVMActivity;
import com.example.aac_mvvm.bean.UserBean;
import com.example.aac_mvvm.lifecycle.ActivityPresenter;
import com.example.aac_mvvm.liveDataVM.UserViewModel;
import com.example.aac_mvvm.room.Order;
import com.example.aac_mvvm.room.dao.OrderDao;
import com.example.aac_mvvm.room.db.DBManager;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

public class MainActivity extends BaseVMActivity<UserViewModel> implements View.OnClickListener {

    private ActivityPresenter mPresenter;
    private TextView mTvName;
    private OrderDao mOrderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBindingUtil.setContentView(this, R.layout.activity_main);


        mTvName = findViewById(R.id.tv_name);
        findViewById(R.id.btn_lv).setOnClickListener(this);
        findViewById(R.id.btn_room_add).setOnClickListener(this);
        findViewById(R.id.btn_room_query).setOnClickListener(this);
        findViewById(R.id.btn_room_delete).setOnClickListener(this);


        mPresenter = new ActivityPresenter();

        //添加生命周期的观察者
        getLifecycle().addObserver(mPresenter);

        initLiveDData();


        mOrderDao = DBManager.getInstance().getOrderDao();

    }


    private void initLiveDData() {
        mVM.getLiveData().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                if (userBean != null) {
                    mTvName.setText(userBean.toString());
                }
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除监听
        getLifecycle().removeObserver(mPresenter);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btn_lv:
                //修改数据
                mVM.changeData();
                break;

            case R.id.btn_room_add:
                mOrderDao.insertAll(Order.newInstance());
                break;
            case R.id.btn_room_delete:
                mOrderDao.deleteOrder(Order.newInstance());
                break;
            case R.id.btn_room_query:
                // final List<Order> orders = mOrderDao.queryOrderById(new long[]{1000});
                final List<Order> orderList = mOrderDao.loadAllOrders();
                if (orderList != null) {
                    StringBuilder sb = new StringBuilder();

                    for (Order order : orderList) {
                        sb.append(order.orderId).append(",");
                    }

                    mTvName.setText(sb.toString());
                }
                break;
        }


    }
}
