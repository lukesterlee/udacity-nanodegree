package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Luke on 6/10/2015.
 */
public class TopTracksActivity extends ActionBarActivity {


    String key = "https://api.spotify.com/v1/artists/6vWDO969PvNqNYHIOW5v0m/top-tracks?country=US";

    TopTrackAdapter adapter;

    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toptracks);

        mListView = (ListView) findViewById(R.id.listView_top_tracks);

        new AsyncLoading().execute();


    }

    private class AsyncLoading extends AsyncTask<Void, Void, List<TopTrackItem>> {

        @Override
        protected List<TopTrackItem> doInBackground(Void... params) {
            try {
                return new TopTracksGetter().getTopTracksList();
            } catch (JSONException e) {
                Log.e("JSON", "couldn't get Json image.");
            } catch (IOException e) {
                Log.e("JSON", "couldn't get Json string.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<TopTrackItem> topTrackList) {
            if (topTrackList != null) {
                adapter = new TopTrackAdapter(getApplicationContext(), R.layout.list_item_top_tracks, topTrackList);
                mListView.setAdapter(adapter);
            }
        }
    }
}
