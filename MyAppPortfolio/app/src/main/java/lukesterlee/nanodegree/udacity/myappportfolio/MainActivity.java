package lukesterlee.nanodegree.udacity.myappportfolio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


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

        buttonSpotify = (Button) findViewById(R.id.button_spotify);

        buttonScores = (Button) findViewById(R.id.button_scores);

        buttonLibrary = (Button) findViewById(R.id.button_library);

        buttonBuild = (Button) findViewById(R.id.button_build);

        buttonReader = (Button) findViewById(R.id.button_reader);

        buttonCapstone = (Button) findViewById(R.id.button_capstone);
    }

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_spotify:
                    break;
                case R.id.button_scores:
                    break;
                case R.id.button_library:
                    break;
                case R.id.button_build:
                    break;
                case R.id.button_reader:
                    break;
                case R.id.button_capstone:
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
