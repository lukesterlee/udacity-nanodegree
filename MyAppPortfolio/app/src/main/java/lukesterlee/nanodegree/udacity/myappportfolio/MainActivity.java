package lukesterlee.nanodegree.udacity.myappportfolio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Button buttonSpotify;
    Button buttonScores;
    Button buttonLibrary;
    Button buttonBuild;
    Button buttonReader;
    Button buttonCapstone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> buttonArrayList = new ArrayList<Button>();

        buttonSpotify = (Button) findViewById(R.id.button_spotify);
        buttonScores = (Button) findViewById(R.id.button_scores);
        buttonLibrary = (Button) findViewById(R.id.button_library);
        buttonBuild = (Button) findViewById(R.id.button_build);
        buttonReader = (Button) findViewById(R.id.button_reader);
        buttonCapstone = (Button) findViewById(R.id.button_capstone);

        buttonArrayList.add(buttonSpotify);
        buttonArrayList.add(buttonScores);
        buttonArrayList.add(buttonLibrary);
        buttonArrayList.add(buttonBuild);
        buttonArrayList.add(buttonReader);
        buttonArrayList.add(buttonCapstone);

        ButtonClickListener listener = new ButtonClickListener();

        for (Button button : buttonArrayList) {
            button.setOnClickListener(listener);
        }


    }

    private class ButtonClickListener implements View.OnClickListener {



        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_spotify:
                    Toast.makeText(getApplicationContext(), "This button will launch my Spotify Streamer App!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_scores:
                    Toast.makeText(getApplicationContext(), "This button will launch my Football Scores App!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_library:
                    Toast.makeText(getApplicationContext(), "This button will launch my Library App!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_build:
                    Toast.makeText(getApplicationContext(), "This button will launch my Build It Bigger App!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_reader:
                    Toast.makeText(getApplicationContext(), "This button will launch my XYZ Reader App!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_capstone:
                    Toast.makeText(getApplicationContext(), "This button will launch my Capstone App!", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
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
