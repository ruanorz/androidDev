package com.ruanorz.marvelapp.views.comic_list.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruanorz.marvelapp.R;
import com.ruanorz.marvelapp.Result;
import com.ruanorz.marvelapp.views.comic_detail.DetailActivity;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

/**
 * Created by ruano on 20/01/2018.
 */

public class ComicListAdapter  extends RecyclerView.Adapter<ComicListAdapter.ViewHolder> {
    private RealmResults<Result> comics;
    static private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_comic_name;
        public ImageView iv_comic_image;
        public RelativeLayout rl_parent;

        public ViewHolder(View v) {
            super(v);

            tv_comic_name = (TextView) v.findViewById(R.id.tv_comic_name);
            iv_comic_image = (ImageView) v.findViewById(R.id.iv_comic_image);
            rl_parent = (RelativeLayout) v.findViewById(R.id.rl_parent);
//            Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/ochobits.ttf");
//            tv_horoscope_name.setTypeface(tf);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ComicListAdapter(RealmResults<Result> comics, Context mContext) {
        this.comics = comics;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ComicListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v;
        switch (viewType) {
            case 0:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_comiclist, parent, false);
                break;
            case 1:

                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_comiclist_right, parent, false);

                break;
            default:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_comiclist, parent, false);
                break;
        }

        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_comic_name.setText(comics.get(position).getTitle());

        holder.rl_parent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("ID_COMIC_CLICKED", comics.get(position).getId());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation((Activity)mContext, holder.iv_comic_image, "comicImage");
                mContext.startActivity(intent, options.toBundle());
//                mContext.startActivity(intent);

            }
        });

        String comic_photo_url = comics.get(position).getThumbnail().getPath() +"."+ comics.get(position).getThumbnail().getExtension().toLowerCase();
        if (comic_photo_url.contains("image_not_available")){
            if (position%2==0) {
                holder.iv_comic_image.setImageDrawable(mContext.getDrawable(R.drawable.defaultbackground));
            }else {
                holder.iv_comic_image.setImageDrawable(mContext.getDrawable(R.drawable.defaultbackground));
            }
        }else {
            Picasso.with(mContext)
                    .load(comic_photo_url)
                    .fit()
                    .centerCrop()
                    .into(holder.iv_comic_image);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return comics.size();
    }
}