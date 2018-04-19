package jxh.zl.com.data.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 2018/4/19.
 */

public class BaseHttpApi {
    private static final int TIME_OUT=30;
    public volatile static BaseHttpApi api;
    private volatile static Retrofit retrofit;

    public static BaseHttpApi getApi() {
        if (api == null) {
            synchronized (BaseHttpApi.class) {
                if (api == null) {
                    api = new BaseHttpApi();
                }
            }
        }
        return api;
    }


    private BaseHttpApi() {

        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);


        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpConfig.BASE_URL)
                .build();
    }


}
