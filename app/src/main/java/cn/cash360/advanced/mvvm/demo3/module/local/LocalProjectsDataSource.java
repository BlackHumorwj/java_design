package cn.cash360.advanced.mvvm.demo3.module.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo3.data.Projects;
import cn.cash360.advanced.mvvm.demo3.module.ProjectDataSource;
import cn.cash360.advanced.mvvm.demo3.module.local.service.ProjectsService;
import cn.cash360.advanced.mvvm.demo3.module.local.service.ProjectsServiceImpl;

/**
 * @time 2019/9/3 11:51
 * @desc
 */
public class LocalProjectsDataSource implements ProjectDataSource {


    private  ProjectsService service = new ProjectsServiceImpl();
    private static final LocalProjectsDataSource instance = new LocalProjectsDataSource();

    private LocalProjectsDataSource() {
    }

    public static LocalProjectsDataSource getInstance() {
        return instance;
    }



    @Override
    public LiveData<Lcee<Projects>> queryProjects(int page) {

        final LiveData<Projects> liveData = service.queryProjects(page);

        final MediatorLiveData<Lcee<Projects>> mediatorLiveData = new MediatorLiveData<>();


        mediatorLiveData.setValue(Lcee.<Projects>loading());



        mediatorLiveData.addSource(liveData, new Observer<Projects>() {
            @Override
            public void onChanged(@Nullable Projects projects) {
                if (projects==null){
                    mediatorLiveData.setValue(Lcee.<Projects>empty());
                }else {
                    projects.itemsFromJson();
                    mediatorLiveData.setValue(Lcee.<Projects>content(projects));
                }
            }
        });

        return mediatorLiveData;
    }

    public void add(Projects projects) {
        service.add(projects);
    }
}
