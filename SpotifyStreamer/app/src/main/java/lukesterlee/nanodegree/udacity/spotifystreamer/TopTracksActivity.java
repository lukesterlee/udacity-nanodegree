package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Track;

public class TopTracksActivity extends ActionBarActivity {

    private String mArtistId;
    private TopTrackAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toptracks);

        mArtistId = getIntent().getExtras().getString("artist");
        mListView = (ListView) findViewById(R.id.listView_top_tracks);
        new AsyncLoading().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpListener(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setUpListener(false);
    }

    private void setUpListener(boolean isResumed) {
        if (!isResumed) {
            mListView.setOnItemClickListener(null);
        } else {
           mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                   Track selectedTrack = mAdapter.getItem(position);
                   Bundle bundle = new Bundle();
                   String title = selectedTrack.name;
                   AlbumSimple album = selectedTrack.album;
                   String albumName = album.name;
                   String thumbnailUrl = album.images.get(0).url;
                   String artistName = selectedTrack.artists.get(0).name;
                   bundle.putString("title", title);
                   bundle.putString("album", albumName);
                   bundle.putString("url", thumbnailUrl);
                   bundle.putString("artist", artistName);
                   Intent intent = new Intent(TopTracksActivity.this, PlayerActivity.class);
                   intent.putExtra("selected_track", bundle);
                   startActivity(intent);
               }
           });
        }
    }

    private class AsyncLoading extends AsyncTask<Void, Void, List<Track>> {
        @Override
        protected List<Track> doInBackground(Void... params) {
            try {
                return new ResultGetter(mArtistId).getTopTracksList();
            } catch (JSONException e) {
                Log.e("JSON", "couldn't get Json image.");
            } catch (IOException e) {
                Log.e("JSON", "couldn't get Json string.");
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Track> topTrackList) {
            if (topTrackList != null) {
                if (topTrackList.size() == 0) {
                    Toast.makeText(TopTracksActivity.this, "There is no track found.", Toast.LENGTH_SHORT);
                }
                mAdapter = new TopTrackAdapter(getApplicationContext(), R.layout.list_item_top_tracks, topTrackList);
                mListView.setAdapter(mAdapter);
            }
        }
    }
}
