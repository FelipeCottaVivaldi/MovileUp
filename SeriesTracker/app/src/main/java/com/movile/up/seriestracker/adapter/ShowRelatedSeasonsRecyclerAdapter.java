package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.OnSeasonsClickListener;
import com.movile.up.seriestracker.interfaces.OnShowListener;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class ShowRelatedSeasonsRecyclerAdapter extends RecyclerView.Adapter<ShowRelatedSeasonsRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Show> mShows;
    private OnShowListener mListener;

    public ShowRelatedSeasonsRecyclerAdapter(Context context, OnShowListener clickListener) {
        mContext = context;
        mListener = clickListener;
    }

    public void updateShows(List<Show> shows) {
        mShows = shows;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_shows_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide
                .with(mContext)
                .load(mShows.get(position).images().poster().get("full"))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.thumb());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShowClick(mShows.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShows != null ? mShows.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private ImageView mThumb;

        public ViewHolder(View root) {
            super(root);
            mView = root;
            mThumb = (ImageView) root.findViewById(R.id.popular_shows_thumb);
        }
        public View view() {
            return mView;
        }

        public ImageView thumb(){ return mThumb;}
    }
}
