package cn.cash360.java_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @time 2019/3/18 20:15
 * @desc
 */

public class RetrofitDemo extends AppCompatActivity {


    public interface ApiService {
        @GET("data/Android" + "/{page}")
        Call<String> getUserInfo(@Path("key") int value);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrofit retrofit = new Retrofit.Builder()
        //.addCallAdapterFactory()
        //.addConverterFactory()
        //.baseUrl()
        //.client()
        //       .build();


        //动态代理 获取到我们需要的实现类
        // ApiService apiService = retrofit.create(ApiService.class);

        //  Call<String> userInfo = apiService.getUserInfo(1);
        //同步
        //  Request request = userInfo.request();
        //        userInfo.enqueue(new Callback<String>() {
        //            @Override
        //            public void onResponse(Call<String> call, Response<String> response) {
        //
        //            }
        //            @Override
        //            public void onFailure(Call<String> call, Throwable t) {
        //
        //            }
        //        });

    }


}
