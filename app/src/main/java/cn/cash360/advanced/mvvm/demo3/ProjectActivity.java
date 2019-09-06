package cn.cash360.advanced.mvvm.demo3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import cn.cash360.java_design.R;
import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo2.Status;
import cn.cash360.advanced.mvvm.demo3.data.ListStatus;
import cn.cash360.advanced.mvvm.demo3.data.Projects;
import cn.cash360.advanced.mvvm.demo3.module.ProjectsRepository;
import cn.cash360.advanced.mvvm.demo3.module.local.db.DBHelper;
import cn.cash360.advanced.mvvm.demo3.vm.ProjectsViewModel;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/9/3 10:32
 * @desc
 */
public class ProjectActivity extends BaseActivity {

    private View vContent, vError, vLoading, vEmpty;
    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private ProjectsAdapter projectsAdapter;
    private ProjectsViewModel mProjectsViewModel;
    private Status mStatus;
    private ListStatus mListStatus = ListStatus.Refreshing;

    public static Intent newInstance(Context context) {
       Intent intent = new Intent(context,ProjectActivity.class);
        return intent;
    }



    @Override
    protected int getLayoutResID() {
        return R.layout.mvvm_load_more_activity;
    }


    @Override
    protected void initView() {
        vContent = findViewById(R.id.v_content);
        vError = findViewById(R.id.v_error);
        vLoading = findViewById(R.id.v_loading);
        vEmpty = findViewById(R.id.v_empty);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        projectsAdapter = new ProjectsAdapter();
        rv.setAdapter(projectsAdapter);
    }

    @Override
    protected void initData() {
        ProjectsRepository.getInstance().init(mActivity);
        DBHelper.getInstance().init(mActivity);

        mProjectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel.class);
        mProjectsViewModel.getProjects().observe(this, new Observer<Lcee<Projects>>() {
            @Override
            public void onChanged(@Nullable Lcee<Projects> projectsLcee) {
                updateView(projectsLcee);
            }
        });

        reload();

    }

    @Override
    protected void initEvent() {
        View.OnClickListener reloadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        };
        vError.setOnClickListener(reloadClickListener);
        vEmpty.setOnClickListener(reloadClickListener);

        // refresh
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoading()) {
                    srl.setRefreshing(false);
                    return;
                }
                mListStatus = ListStatus.Refreshing;
                reload();
            }
        });

        //load more

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                final RecyclerView.Adapter recyclerViewAdapter = recyclerView.getAdapter();


                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }


                if (recyclerViewAdapter == null) {
                    return;
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerViewAdapter.getItemCount() == mLastVisibleItemPosition + 1) {
                    if (isLoading()) {
                        return;
                    }
                    mListStatus = ListStatus.LoadingMore;
                    loadMore();

                }
            }
        });


    }


    private void updateView(Lcee<Projects> projectsLcee) {
        mStatus = projectsLcee.status;
        switch (mStatus) {
            case Content: {
                updateContentView(projectsLcee.data);
                break;
            }
            case Empty: {
                updateEmptyView();
                break;
            }
            case Error: {
                updateErrorView();
                break;
            }
            case Loading: {
                updateLoadingView();
                break;
            }
        }
    }

    private void loadMore() {
        mProjectsViewModel.loadMore(projectsAdapter.getItemCount());
    }

    private boolean isLoading() {
        return mStatus == Status.Loading;
    }

    private void reload() {
        mProjectsViewModel.reload();
    }


    private void updateLoadingView() {
        switch (mListStatus) {
            case LoadingMore: {

                break;
            }
            case Refreshing: {

                break;
            }
            default: {
                showLoading();
                break;
            }
        }
    }

    private void updateErrorView() {
        switch (mListStatus) {
            case LoadingMore: {
                // todo load more error

                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                Toast.makeText(this, "Refresh failed", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                showError();
                break;
            }
        }
    }

    private void updateEmptyView() {
        switch (mListStatus) {
            case LoadingMore: {
                // todo no more data

                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                showEmpty();
                break;
            }
            default: {
                showEmpty();
                break;
            }
        }
    }

    private void updateContentView(Projects projects) {
        switch (mListStatus) {
            case LoadingMore: {
                projectsAdapter.addData(projects.getItems());
                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                showContent();
                projectsAdapter.setData(projects.getItems());
                break;
            }
            default: {
                showContent();
                projectsAdapter.setData(projects.getItems());
                break;
            }
        }
    }


    private void showContent() {
        vContent.setVisibility(View.VISIBLE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showEmpty() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.VISIBLE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showError() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.VISIBLE);
        vLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }


}
