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

import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Followers;
import kaaes.spotify.webapi.android.models.Image;

/**
 * Created by Luke on 6/11/2015.
 */
public class ArtistSearchAdapter extends BaseAdapter {

    private Context mContext;
    private List<Artist> artistSearchList;
    private int mResourceLayout;


    public ArtistSearchAdapter(Context mContext, int mResourceLayout, List<Artist> artistSearchList) {
        this.mContext = mContext;
        this.mResourceLayout = mResourceLayout;
        this.artistSearchList = artistSearchList;
    }

    @Override
    public int getCount() {
        return artistSearchList.size();
    }

    @Override
    public Artist getItem(int position) {
        return artistSearchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public String getArtistId(int position) {
        return getItem(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_artist_search, parent, false);
        }

        Artist item = getItem(position);

        ImageView artistCover = (ImageView) convertView.findViewById(R.id.imageView_artist);
        TextView artistName = (TextView) convertView.findViewById(R.id.textView_artist);


        List<Image> images = item.images;
        if (images.size() != 0) {
            Image image = images.get(images.size()-1);
            Picasso.with(mContext).load(image.url).resize(200,200).centerCrop().into(artistCover);
        } else {
            Picasso.with(mContext).load(R.drawable.artist).resize(200,200).centerCrop().into(artistCover);
        }

        artistName.setText(item.name);

        return convertView;
    }
}
