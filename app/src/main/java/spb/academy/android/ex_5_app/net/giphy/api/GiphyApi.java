package spb.academy.android.ex_5_app.net.giphy.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import spb.academy.android.ex_5_app.net.giphy.pojo.search.GiphySearchAnswer;

/**
 * Created by User on 06.05.2018.
 */

public interface GiphyApi {

    @GET("search")
    Call<GiphySearchAnswer> getSearch(@Query("q") String Query,
                                      @Query("api_key") String ApiKey);

}
