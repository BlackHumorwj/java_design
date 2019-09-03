package cn.cash360.ui.activity.advanced.mvvm.demo3.module.local.service;

import android.arch.lifecycle.LiveData;

import cn.cash360.ui.activity.advanced.mvvm.demo3.data.Projects;

/**
 * @time 2019/9/3 11:45
 * @desc
 */
public interface ProjectsService {

    LiveData<Long> add(Projects projects);


    LiveData<Projects> queryProjects(int page);





}
