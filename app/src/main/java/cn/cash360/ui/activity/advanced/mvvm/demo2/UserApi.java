package cn.cash360.ui.activity.advanced.mvvm.demo2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @time 2019/9/2 11:16
 * @desc
 */
public interface UserApi {


    @GET("/user/{username}")
    Call<User> queryUserByUserName(@Path("username") String username);


}
