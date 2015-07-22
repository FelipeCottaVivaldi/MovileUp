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
import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class ShowSeasonsRecyclerAdapter extends RecyclerView.Adapter<ShowSeasonsRecyclerAdapter.ViewHolder>{

    private Context mContext;
    private List<Season> mSeasons;
    private OnSeasonsClickListener mListener;

    public ShowSeasonsRecyclerAdapter(Context context, OnSeasonsClickListener clickListener) {
        mContext = context;
        mListener = clickListener;
    }

    public void updateSeasons(List<Season> seasons) {
        mSeasons = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_season_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Season season = mSeasons.get(position);

        holder.episodeCount().setText(season.episodeCount().toString() +  "episodes");
        holder.number().setText("Season " + season.number().toString());

        Glide
                .with(mContext)
                .load(season.images().poster().get("full"))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into((ImageView) holder.image());

        holder.mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSeasonClick(season);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mEpisodeCount;
        private TextView mNumber;
        private ImageView mScreenshot;

        public ViewHolder(View view) {
            super(view);
            mRoot = view;
            mEpisodeCount = (TextView) mRoot.findViewById(R.id.show_seasons_episodes_count);
            mNumber = (TextView) mRoot.findViewById(R.id.show_seasons_number);
            mScreenshot = (ImageView) mRoot.findViewById(R.id.show_seasons_screenshot);
        }

        public View root() {
            return mRoot;
        }

        public TextView episodeCount() {
            return mEpisodeCount;
        }

        public TextView number() {
            return mNumber;
        }

        public ImageView image() {
            return mScreenshot;
        }

    }

}
