package spb.academy.android.ex_5_app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 03.05.2018.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ImageHolder> {

    private List<Picture> picturesList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;


    PictureAdapter (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    View.OnClickListener innerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Picture picture = (Picture) v.getTag();
            int position = picturesList.indexOf(picture);
            onItemClickListener.onClick(picture, position);
        }
    };

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        return new ImageHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        Picture picture = picturesList.get(position);
        holder.image.setTag(picture);
        Picasso.get().load(picture.getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return picturesList.size();
    }

    public void updateAllData(List<Picture> picturesList) {
        this.picturesList.addAll(picturesList);
        notifyDataSetChanged();
    }

    interface OnItemClickListener {
        public void onClick(Picture picture, int position);
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        public ImageView image;

        public ImageHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            image.setOnClickListener(innerOnClickListener);
        }

    }
}
