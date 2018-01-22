package com.ruanorz.marvelapp.comic_list.adapter;

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
import com.ruanorz.marvelapp.comic_detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by ruano on 22/01/2018.
 */

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {
    private List<Result> characters;
    static private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_character_name;

        public ViewHolder(View v) {
            super(v);

            tv_character_name = (TextView) v.findViewById(R.id.tv_character_name);

//            Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/ochobits.ttf");
//            tv_horoscope_name.setTypeface(tf);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CharacterListAdapter(List<Result> characters, Context mContext) {
        this.characters = characters;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v;

        v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_characterlist, parent, false);


        // set the view's size, margins, paddings and layout parameters

        return new CharacterListAdapter.ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CharacterListAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_character_name.setText(characters.get(position).getName());

//        holder.rl_parent.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext, DetailActivity.class);
//                intent.putExtra("ID_COMIC_CLICKED", comics.get(position).getId());
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation((Activity)mContext, holder.iv_comic_image, "comicImage");
//                mContext.startActivity(intent, options.toBundle());
////                mContext.startActivity(intent);
//
//            }
//        });




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return characters.size();
    }
}