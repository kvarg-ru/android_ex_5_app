package spb.academy.android.ex_5_app;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView status_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        Button search_button = findViewById(R.id.search_button);
        final EditText search_edit_text = findViewById(R.id.search_edit_text);
        status_tv = findViewById(R.id.status_text_view);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideKeyboard();

                status_tv.setText(R.string.status_searching);

                GiphyApi apiService = ((App) getApplication()).getGiphyApi();

                String query = search_edit_text.getText().toString();

                apiService.getSearch(query).enqueue(new Callback<GiphySearchAnswer>() {
                    @Override
                    public void onResponse(Call<GiphySearchAnswer> call, Response<GiphySearchAnswer> response) {

                        if (response.code() == 200) {

                            showContent(response.body());

                        }

                        else {

                            status_tv.setText(R.string.status_error);

                        }
                    }

                    @Override
                    public void onFailure(Call<GiphySearchAnswer> call, Throwable t) {

                        status_tv.setText(R.string.status_error);

                        Log.d(LOG_TAG, "Failure request!: ");

                    }
                });
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

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    private void showContent(GiphySearchAnswer giphySearchAnswer) {

        Log.d(LOG_TAG, "Success: " + giphySearchAnswer.getMeta().getStatus());

        if (giphySearchAnswer.getPagination().getCount() > 0) {

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

            status_tv.setText(R.string.status_found);

        } else {

            status_tv.setText(R.string.status_not_found);

        }
    }
}
