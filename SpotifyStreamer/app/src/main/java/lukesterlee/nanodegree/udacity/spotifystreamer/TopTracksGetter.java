package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 6/10/2015.
 */
public class TopTracksGetter {

    public static final String TAG = "TopTracksGetter";

    public static final String SPOTIFY_JSON_API = "https://api.spotify.com/v1/artists/6vWDO969PvNqNYHIOW5v0m/top-tracks?country=US";

    public String getJsonString() throws IOException {
        URL jsonUrl = new URL(SPOTIFY_JSON_API);
        HttpURLConnection connection = (HttpURLConnection) jsonUrl.openConnection();
        connection.setConnectTimeout(0);
        connection.setReadTimeout(0);
        try {
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            return builder.toString();
        } catch (IOException ioe) {
            Log.e(TAG, "failed to get Json string");
        }
        return null;
    }

    public List<TopTrackItem> getTopTracksList() throws JSONException, IOException {
        List<TopTrackItem> topTrackList = new ArrayList<TopTrackItem>();

        String jsonString = getJsonString();
        if (jsonString != null) {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("tracks");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject tracks = jsonArray.getJSONObject(i);
                JSONObject album = tracks.getJSONObject("album");
                //JSONObject name = tracks.getJSONObject("name");
                JSONArray images = album.getJSONArray("images");

                JSONObject small = images.getJSONObject(1);

                String albumName = album.getString("name");
                String trackName = tracks.getString("name");
                String thumbnailSmallUrl = small.getString("url");

                TopTrackItem item = new TopTrackItem(trackName, albumName, thumbnailSmallUrl);
                topTrackList.add(item);
            }
        }
        return topTrackList;
    }
}