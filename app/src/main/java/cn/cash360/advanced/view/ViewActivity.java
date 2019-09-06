package cn.cash360.advanced.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;
import cn.cash360.util.ToastUtil;

/**
 * @time 2019/8/12 19:29
 * @desc
 */
public class ViewActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvDesc;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ViewActivity.class);
        return intent;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_view;
    }

    @Override
    protected void initView() {
        mTvDesc = (TextView) findViewById(R.id.tv_desc);
        MoveView tvMoveView = (MoveView) findViewById(R.id.move_view);
        tvMoveView.setOnClickListener(this);
        View view = findViewById(R.id.my_view);



    }

    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        switch (vId){
            case R.id.move_view:
                ToastUtil.toast("点击");
                break;
        }
    }

}
