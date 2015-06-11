package lukesterlee.nanodegree.udacity.spotifystreamer;

/**
 * Created by Luke on 6/10/2015.
 */
public class TopTrackItem {
    private String track;
    private String album;
    private String thumbnailUrl;

    public TopTrackItem(String track, String album, String thumbnailUrl) {
        this.track = track;
        this.album = album;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
