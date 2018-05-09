package spb.academy.android.ex_5_app;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spb.academy.android.ex_5_app.net.NetworkModule;
import spb.academy.android.ex_5_app.net.giphy.Constants;
import spb.academy.android.ex_5_app.net.giphy.api.GiphyApi;
import spb.academy.android.ex_5_app.net.giphy.pojo.search.Datum;
import spb.academy.android.ex_5_app.net.giphy.pojo.search.GiphySearchAnswer;
import spb.academy.android.ex_5_app.net.giphy.pojo.search.T480wStill;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = "MainActivity";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        GiphyApi apiService = new NetworkModule(getApplicationContext()).getGiphyApi();
        apiService.getSearch("kitty").enqueue(new Callback<GiphySearchAnswer>() {
            @Override
            public void onResponse(Call<GiphySearchAnswer> call, Response<GiphySearchAnswer> response) {

                GiphySearchAnswer giphySearchAnswer = response.body();
                Log.d(LOG_TAG, "Success: " + giphySearchAnswer.getMeta().getStatus());

                List<Picture> pictureList = new ArrayList<>();

                List<Datum> datumList = giphySearchAnswer.getData();
                for (Datum data:
                     datumList) {

                    Picture picture = new Picture(data.getImages().get480wStill().getUrl(), data.getUsername(), "twitter");

                    pictureList.add(picture);

                    Log.d(LOG_TAG, picture.getUrl());

                }

                PictureAdapter pictureAdapter = (PictureAdapter) recyclerView.getAdapter();
                pictureAdapter.updateAllData(pictureList);

            }

            @Override
            public void onFailure(Call<GiphySearchAnswer> call, Throwable t) {

                Log.d(LOG_TAG, "Failure request!: ");

            }
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.pictures_recycler_view);

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
