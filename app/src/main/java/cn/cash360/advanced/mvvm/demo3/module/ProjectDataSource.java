package cn.cash360.advanced.mvvm.demo3.module;


import androidx.lifecycle.LiveData;
import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo3.data.Projects;

/**
 * @time 2019/9/3 11:25
 * @desc
 */
public interface ProjectDataSource {
    LiveData<Lcee<Projects>> queryProjects(int page);
}
