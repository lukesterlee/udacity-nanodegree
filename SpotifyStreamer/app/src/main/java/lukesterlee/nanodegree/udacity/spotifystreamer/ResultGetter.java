package lukesterlee.nanodegree.udacity.spotifystreamer;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;

public class ResultGetter {
    private static final String TAG = "ResultGetter";
    private static final String COUNTRY_KEY = "country";
    private static final String USA_CODE = "us";
    private String input;

    public ResultGetter(String input) {
        this.input = input;
    }

    public List<Artist> getArtistSearchList() throws JSONException, IOException {
        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();
        ArtistsPager pager = service.searchArtists(input);
        Pager<Artist> artists = pager.artists;
        return artists.items;
    }

    public ArrayList<MyTrack> getTopTracksList() throws JSONException, IOException {
        ArrayList<MyTrack> topTracksList = new ArrayList<>();
        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();
        Map<String, Object> countryMap = new HashMap<>();
        countryMap.put(COUNTRY_KEY, USA_CODE);
        Tracks tracks = service.getArtistTopTrack(input, countryMap);
        List<Track> list = tracks.tracks;
        for (Track track : list) {
            MyTrack newTrack = new MyTrack();
            newTrack.setTrack(track.name);
            AlbumSimple albumSimple = track.album;
            newTrack.setAlbum(albumSimple.name);
            List<Image> images = albumSimple.images;
            Image image = images.get(images.size() - 1);
            newTrack.setThumbnailUrl(image.url);
            newTrack.setArtist(track.artists.get(0).name);
            topTracksList.add(newTrack);
        }
        return topTracksList;
    }
}