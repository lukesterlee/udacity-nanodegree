package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;


public class MainActivity extends ActionBarActivity {

//    private static final String CLIENT_ID = "39e7fc61588a407bb2fb2f92e045ccb1";
//    private static final String CLIENT_SECRET_ID = "5d27fd83b77e4438b332119203a8f89b";

    EditText mEditText;
    ListView mListView;

    ArtistSearchAdapter adapter;

    String artist = "";

    // https://api.spotify.com/v1/search?q=Beyonce&type=artist
    private static final String ENDPOINT_SEARCH = "https://api.spotify.com/v1/search?q=";
    private static final String SEARCH_SUFFIX = "&type=artist";

    // https://api.spotify.com/v1/artists/6vWDO969PvNqNYHIOW5v0m/top-tracks?country=US
    private static final String ENDPOINT_TOP_TRACKS = "https://api.spotify.com/v1/artists/";
    private static final String TOP_TRACKS_SUFFIX = "/top-tracks?country=US";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();



        Button button = (Button) findViewById(R.id.button_top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                artist = mEditText.getText().toString();
                new AsyncLoading().execute();
            }
        });


    }

    private void initializeViews() {
        mEditText = (EditText) findViewById(R.id.editText_search_artist);
        mListView = (ListView) findViewById(R.id.listView_search_result);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String artistId = adapter.getArtistId(position);
                Intent intent = new Intent(MainActivity.this, TopTracksActivity.class);
                intent.putExtra("artist", artistId);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class AsyncLoading extends AsyncTask<Void, Void, List<Artist>> {

        @Override
        protected List<Artist> doInBackground(Void... params) {
            try {
                return new ResultGetter(artist).getArtistSearchList();
            } catch (JSONException e) {
                Log.e("JSON", "couldn't get Json image.");
            } catch (IOException e) {
                Log.e("JSON", "couldn't get Json string.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Artist> artistSearchList) {
            if (artistSearchList != null) {
                adapter = new ArtistSearchAdapter(MainActivity.this, R.layout.list_item_artist_search, artistSearchList);
                mListView.setAdapter(adapter);
            }
        }
    }
}
