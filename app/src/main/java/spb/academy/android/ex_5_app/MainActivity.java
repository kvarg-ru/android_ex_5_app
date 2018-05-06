package spb.academy.android.ex_5_app;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spb.academy.android.ex_5_app.net.NetworkModule;
import spb.academy.android.ex_5_app.net.giphy.Constants;
import spb.academy.android.ex_5_app.net.giphy.api.GiphyApi;
import spb.academy.android.ex_5_app.net.giphy.pojo.search.GiphySearchAnswer;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        GiphyApi apiService = new NetworkModule(getApplicationContext()).getGiphyApi();
        apiService.getSearch("kitty", Constants.API_KEY).enqueue(new Callback<GiphySearchAnswer>() {
            @Override
            public void onResponse(Call<GiphySearchAnswer> call, Response<GiphySearchAnswer> response) {

                GiphySearchAnswer giphySearchAnswer = response.body();
                Log.d(LOG_TAG, "Success: " + giphySearchAnswer.getMeta().getStatus());
            }

            @Override
            public void onFailure(Call<GiphySearchAnswer> call, Throwable t) {

                Log.d(LOG_TAG, "Failure request!: ");

            }
        });

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.pictures_recycler_view);

        RecyclerView.LayoutManager layoutManager;

        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this,2);
        } else {
            layoutManager = new GridLayoutManager(this,3);
        }

        recyclerView.setLayoutManager(layoutManager);

        PictureAdapter pictureAdapter = new PictureAdapter(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onClick(Picture picture, int position) {
                return;
            }
        });

        recyclerView.setAdapter(pictureAdapter);
    }
}
