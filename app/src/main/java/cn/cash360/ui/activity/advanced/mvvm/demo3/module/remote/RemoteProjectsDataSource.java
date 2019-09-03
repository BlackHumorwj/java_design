package cn.cash360.ui.activity.advanced.mvvm.demo3.module.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.orhanobut.logger.Logger;

import cn.cash360.ui.activity.advanced.mvvm.demo2.Lcee;
import cn.cash360.ui.activity.advanced.mvvm.demo2.RetrofitFactory;
import cn.cash360.ui.activity.advanced.mvvm.demo3.data.Projects;
import cn.cash360.ui.activity.advanced.mvvm.demo3.module.ProjectDataSource;
import cn.cash360.ui.activity.advanced.mvvm.demo3.module.local.LocalProjectsDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @time 2019/9/3 13:49
 * @desc
 */
public class RemoteProjectsDataSource implements ProjectDataSource {


    private static final RemoteProjectsDataSource instance = new RemoteProjectsDataSource();

    private RemoteProjectsDataSource() {

    }

    public static RemoteProjectsDataSource getInstance() {
        return instance;
    }


    final ProjectApi mProjectApi = RetrofitFactory.getInstance().create(ProjectApi.class);


    @Override
    public LiveData<Lcee<Projects>> queryProjects(final int page) {
        final MutableLiveData<Lcee<Projects>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(Lcee.<Projects>loading());
        mProjectApi.queryProjects(page).enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {

                final Projects body = response.body();

                Logger.i(response.message());


                if (body == null) {
                    mutableLiveData.setValue(Lcee.<Projects>empty());
                    return;
                }



                mutableLiveData.setValue(Lcee.content(body));

                body.setPage(page);
                //update cache
                LocalProjectsDataSource.getInstance().add(body);

            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                mutableLiveData.setValue(Lcee.<Projects>error(t));
            }
        });


        return mutableLiveData;
    }
}
