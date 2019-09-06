package cn.cash360.advanced.mvvm.demo3.module.local.service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import cn.cash360.advanced.mvvm.demo3.data.Projects;
import cn.cash360.advanced.mvvm.demo3.module.local.dao.ProjectsDao;
import cn.cash360.advanced.mvvm.demo3.module.local.db.DBHelper;

/**
 * @time 2019/9/3 11:46
 * @desc
 */
public class ProjectsServiceImpl implements ProjectsService {


    final ProjectsDao projectsDao = DBHelper.getInstance().getDB().getProjectsDao();


    @SuppressLint("StaticFieldLeak")
    @Override
    public LiveData<Long> add(final Projects projects) {

        final MutableLiveData<Long> longMutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return projectsDao.add(projects);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                longMutableLiveData.setValue(aLong);
            }
        }.execute();
        return longMutableLiveData;
    }

    @Override
    public LiveData<Projects> queryProjects(int page) {
        return projectsDao.queryProjects(page);
    }
}
