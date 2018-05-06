package spb.academy.android.ex_5_app.net.giphy.pojo.search;

/**
 * Created by User on 04.05.2018.
 */

public class Images {

    @SerializedName("preview_gif")
    @Expose
    private PreviewGif previewGif;
    @SerializedName("480w_still")
    @Expose
    private T480wStill _480wStill;

    public PreviewGif getPreviewGif() {
        return previewGif;
    }

    public void setPreviewGif(PreviewGif previewGif) {
        this.previewGif = previewGif;
    }

    public T480wStill get480wStill() {
        return _480wStill;
    }

    public void set480wStill(T480wStill _480wStill) {
        this._480wStill = _480wStill;
    }

}
