package spb.academy.android.ex_5_app.net;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import spb.academy.android.ex_5_app.net.giphy.Constants;
import spb.academy.android.ex_5_app.net.giphy.api.GiphyApi;

/**
 * Created by User on 06.05.2018.
 */

public class NetworkModule {

    private final GiphyApi giphyApi;

    public NetworkModule(Context context) {

        Retrofit retrofit = provideRetrofit();
        giphyApi = provideGiphyApi(retrofit);

    }

    public GiphyApi getGiphyApi() {
        return giphyApi;
    }

    private Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(Constants.API_BASE_URL)
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .build();
    }

    private GiphyApi provideGiphyApi(Retrofit retrofit) {
        return retrofit.create(GiphyApi.class);
    }
}
