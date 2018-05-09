package spb.academy.android.ex_5_app;

import android.app.Application;

import spb.academy.android.ex_5_app.net.NetworkModule;
import spb.academy.android.ex_5_app.net.giphy.api.GiphyApi;

/**
 * Created by User on 09.05.2018.
 */

public class App extends Application {

    GiphyApi giphyApi;

    @Override
    public void onCreate() {
        super.onCreate();

        giphyApi = new NetworkModule(this).getGiphyApi();

    }

    public GiphyApi getGiphyApi() {

        return giphyApi;

    }
}
