package cn.cash360.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import cn.cash360.java_design.R;
import cn.cash360.ui.fragment.AdvancedFragment;
import cn.cash360.ui.fragment.JavaFragment;
import cn.cash360.ui.fragment.PrimerFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {


    private BottomNavigationView mBottomNavigationView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view);
        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.one:
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.two:
                mViewPager.setCurrentItem(1,false);
                break;
            case R.id.three:
                mViewPager.setCurrentItem(2,false);
                break;
            case R.id.four:
                break;
            case R.id.five:
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        MenuItem item = mBottomNavigationView.getMenu().getItem(i);
        item.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    //当页面不可见时，该Fragment就会被销毁，只保留Fragment的状态。
    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        Fragment[] mFragments = new Fragment[]{PrimerFragment.newInstance(), AdvancedFragment.newInstance(), JavaFragment.newInstance()};


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments[i];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

    //该类内的每一个生成的 Fragment 都将保存在内存之中 它都不会被销毁
    class ViewPpager2 extends FragmentPagerAdapter {

        public ViewPpager2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
