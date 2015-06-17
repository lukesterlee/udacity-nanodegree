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

import kaaes.spotify.webapi.android.models.AlbumSimple;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by Luke on 6/10/2015.
 */
public class TopTrackAdapter extends BaseAdapter {

    private Context mContext;
    private List<Track> topTrackList;
    private int mResourceLayout;

    public TopTrackAdapter(Context context, int resource, List<Track> topTrackList) {
        this.mContext = context;
        this.topTrackList = topTrackList;
        this.mResourceLayout = resource;
    }


    @Override
    public int getCount() {
        return topTrackList.size();
    }

    @Override
    public Track getItem(int position) {
        return topTrackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResourceLayout, parent, false);
        }

        Track item = getItem(position);

        ImageView  albumCover = (ImageView) convertView.findViewById(R.id.imageView);
        TextView track = (TextView) convertView.findViewById(R.id.textView_song);
        TextView album = (TextView) convertView.findViewById(R.id.textView_album);

        AlbumSimple albumSimple = item.album;
        List<Image> images = albumSimple.images;
        Image image = images.get(images.size()-1);

        track.setText(item.name);
        album.setText(albumSimple.name);

        Picasso.with(mContext).load(image.url).resize(200,200).centerCrop().into(albumCover);

        return convertView;
    }
}
