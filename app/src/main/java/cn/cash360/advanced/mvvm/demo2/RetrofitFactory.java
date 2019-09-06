package cn.cash360.advanced.mvvm.demo2;

import java.util.concurrent.TimeUnit;

import cn.cash360.util.TrustManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @time 2019/9/2 11:11
 * @desc
 */
public class RetrofitFactory {
    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;

    private static String Base_Url = "https://api.github.com";

    static {

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TrustManager.getInstance().setTrust(builder);
        sOkHttpClient = builder.connectTimeout(9, TimeUnit.SECONDS).build();


        sRetrofit = new Retrofit.Builder().baseUrl(Base_Url).client(sOkHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

    }


    public static Retrofit getInstance(){
        return sRetrofit;
    }




}
