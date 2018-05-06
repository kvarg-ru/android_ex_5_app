package spb.academy.android.ex_5_app;

/**
 * Created by User on 03.05.2018.
 */

public class Picture {

    private String url;
    private String username;
    private String twitter;

    public Picture(String url, String username, String twitter) {
        this.url = url;
        this.username = username;
        this.twitter = twitter;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getTwitter() {
        return twitter;
    }

}
