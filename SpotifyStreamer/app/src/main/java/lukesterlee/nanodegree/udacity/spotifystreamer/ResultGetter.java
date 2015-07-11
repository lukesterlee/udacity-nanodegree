package lukesterlee.nanodegree.udacity.spotifystreamer;

import org.json.JSONException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
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

    public List<Track> getTopTracksList() throws JSONException, IOException {
        SpotifyApi api = new SpotifyApi();
        SpotifyService service = api.getService();

        Map<String, Object> countryMap = new HashMap<>();
        countryMap.put(COUNTRY_KEY, USA_CODE);
        Tracks tracks = service.getArtistTopTrack(input, countryMap);
        return tracks.tracks;
    }
}