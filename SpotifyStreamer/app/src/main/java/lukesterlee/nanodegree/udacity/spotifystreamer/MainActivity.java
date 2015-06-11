package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.LinkedTrack;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;


public class MainActivity extends ActionBarActivity {

//    private static final String CLIENT_ID = "39e7fc61588a407bb2fb2f92e045ccb1";
//    private static final String CLIENT_SECRET_ID = "5d27fd83b77e4438b332119203a8f89b";

    private static final String ENDPOINT = "https://api.spotify.com/v1/artists/";
    private static final String TOP_TRACKS = "/top-tracks";

    String bey = "https://api.spotify.com/v1/search?q=Beyonce&type=artist";

    String key = "https://api.spotify.com/v1/artists/6vWDO969PvNqNYHIOW5v0m/top-tracks?country=US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpotifyApi api = new SpotifyApi();

        Button button = (Button) findViewById(R.id.button_top);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TopTracksActivity.class);
                startActivity(intent);
            }
        });


        //Picasso.with(getApplicationContext()).load("htt").into();



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
}
