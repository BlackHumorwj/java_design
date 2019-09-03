package cn.cash360.ui.activity.advanced.mvvm.demo3.module;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import cn.cash360.ui.activity.advanced.mvvm.demo2.Lcee;
import cn.cash360.ui.activity.advanced.mvvm.demo3.data.Projects;
import cn.cash360.ui.activity.advanced.mvvm.demo3.module.local.LocalProjectsDataSource;
import cn.cash360.ui.activity.advanced.mvvm.demo3.module.remote.RemoteProjectsDataSource;
import cn.cash360.util.NetworkUtils;

/**
 * @time 2019/9/3 14:02
 * @desc
 */
public class ProjectsRepository {

    private static final ProjectsRepository instance = new ProjectsRepository();
    private Context mContext;

    private ProjectsRepository() {

    }

    public static ProjectsRepository getInstance() {
        return instance;
    }


    public void init(Context context) {
        mContext = context.getApplicationContext();
    }


    public LiveData<Lcee<Projects>> getProjects(int pages) {

        if (NetworkUtils.isConnected(mContext)) {
            return RemoteProjectsDataSource.getInstance().queryProjects(pages);
        } else {
            return LocalProjectsDataSource.getInstance().queryProjects(pages);

        }


    }


}
