package spb.academy.android.ex_5_app;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
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
