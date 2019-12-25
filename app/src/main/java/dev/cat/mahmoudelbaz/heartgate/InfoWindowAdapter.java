package dev.cat.mahmoudelbaz.heartgate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import dev.cat.mahmoudelbaz.heartgate.myAccount.ModelMyConnections;

/**
 * Created by mahmoudelbaz on 10/26/17.
 */

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    //    private final View myContentsView;
    private final Context context;
    String name, title, pic;
    private ModelMyConnections item;

    InfoWindowAdapter(Context cx, String name, String title, String pic) {
        this.context = cx;
        this.name = name;
        this.title = title;
        this.pic = pic;
        this.item = item;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myContentsView = li.inflate(R.layout.custom_info_contents, null);
        TextView tvTitle = myContentsView.findViewById(R.id.title);
        TextView tvSnippet = myContentsView.findViewById(R.id.snippet);
        ImageView ivIcon = myContentsView.findViewById(R.id.icon);
        tvTitle.setText(marker.getTitle());
        tvSnippet.setText(marker.getSnippet());


        Picasso.with(context).load(pic).placeholder(R.drawable.profile).error(R.drawable.profile).into(ivIcon);
        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

   /* private void loadMarkerIcon(final Marker marker) {
        String burlImg = "Url_imagePath;
        Glide.with(this).load(burlImg)
                .asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {

                if(bitmap!=null){
                    //  Bitmap circularBitmap = getRoundedCornerBitmap(bitmap, 150);
                    Bitmap mBitmap = getCircularBitmap(bitmap);
                    mBitmap = addBorderToCircularBitmap(mBitmap, 2, Color.WHITE,squareBitmapWidth);
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(mBitmap);
                    marker.setIcon(icon);
                }

            }
        });

    }*/

}