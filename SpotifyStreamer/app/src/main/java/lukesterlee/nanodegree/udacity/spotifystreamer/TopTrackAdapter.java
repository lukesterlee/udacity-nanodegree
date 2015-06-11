package lukesterlee.nanodegree.udacity.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Luke on 6/10/2015.
 */
public class TopTrackAdapter extends BaseAdapter {

    private Context mContext;
    private List<TopTrackItem> topTrackList;
    private int mResourceLayout;

    public TopTrackAdapter(Context context, int resource, List<TopTrackItem> topTrackList) {
        this.mContext = context;
        this.topTrackList = topTrackList;
        this.mResourceLayout = resource;
    }


    @Override
    public int getCount() {
        return topTrackList.size();
    }

    @Override
    public TopTrackItem getItem(int position) {
        return topTrackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(mResourceLayout, parent, false);



        ImageView  albumCover = (ImageView) row.findViewById(R.id.imageView);
        TextView track = (TextView) row.findViewById(R.id.textView_song);
        TextView album = (TextView) row.findViewById(R.id.textView_album);

        track.setText(getItem(position).getTrack());
        album.setText(getItem(position).getAlbum());

        Picasso.with(mContext).load(getItem(position).getThumbnailUrl()).resize(200,200).centerCrop().into(albumCover);

        return row;
    }
}
