package cn.cash360.advanced.mvvm.demo3.module.remote;

import cn.cash360.advanced.mvvm.demo3.data.Projects;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @time 2019/9/3 13:44
 * @desc
 */
public interface ProjectApi {

    @GET("/search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
    Call<Projects> queryProjects(@Query("page") int page);

}
