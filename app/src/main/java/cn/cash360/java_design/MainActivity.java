package cn.cash360.java_design;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BDialogFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_click).setOnClickListener(this);
        mFragment = BDialogFragment.newInstance();
    }


    @Override
    public void onClick(View v) {
        mFragment.show(getSupportFragmentManager(), "");
    }
}
