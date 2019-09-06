package cn.cash360.advanced.mvvm.demo3.vm;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import cn.cash360.advanced.mvvm.demo2.Lcee;
import cn.cash360.advanced.mvvm.demo3.PageUtils;
import cn.cash360.advanced.mvvm.demo3.data.Projects;
import cn.cash360.advanced.mvvm.demo3.module.ProjectsRepository;

/**
 * @time 2019/9/3 14:08
 * @desc
 */
public class ProjectsViewModel extends ViewModel {


    private ProjectsRepository projectsRepository = ProjectsRepository.getInstance();
    private MutableLiveData<Integer> ldPage;
    private LiveData<Lcee<Projects>> ldProjects;

    public LiveData<Lcee<Projects>> getProjects() {
        if (null == ldProjects) {
            ldPage = new MutableLiveData<>();
            ldProjects = Transformations.switchMap(ldPage, new Function<Integer, LiveData<Lcee<Projects>>>() {
                @Override
                public LiveData<Lcee<Projects>> apply(Integer page) {
                    return projectsRepository.getProjects(page);
                }
            });
        }
        return ldProjects;
    }

    public void reload() {
        ldPage.setValue(1);
    }

    public void loadMore(int currentCount) {
        ldPage.setValue(PageUtils.getPage(currentCount));
    }




}
