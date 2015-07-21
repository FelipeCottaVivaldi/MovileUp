package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class EpisodesAdapter extends ArrayAdapter<Episode> {
    private List<Episode> episodeList;
    private OnContentClickListener mClickListener;

    public EpisodesAdapter(Context context, OnContentClickListener clickListener) {
        super(context, R.layout.episode_item);
        mClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return episodeList == null ? 0 : episodeList.size();
    }

    @Override
    public Episode getItem(int position) {
        return episodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.episode_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, final int position) {
        holder.getNumber().setText("E" + episodeList.get(position).number().toString());
        holder.getTitle().setText(episodeList.get(position).title().toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onEpisodeClick(episodeList.get(position));
            }
        });

    }

    public void updateEpisodes(List<Episode> episodes){
        episodeList = episodes;
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        private View mView;
        private TextView number;
        private TextView title;
        public ViewHolder(View root) {
            mView = root;
            number = (TextView) root.findViewById(R.id.episode_number);
            title = (TextView) root.findViewById(R.id.episode_title);
        }
        public View view() {
            return mView;
        }

        public TextView getNumber(){
            return number;
        }

        public TextView getTitle(){
            return title;
        }
    }
}
