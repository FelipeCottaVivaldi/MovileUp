package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.OnShowListener;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class PopularShowsAdapter extends ArrayAdapter<Show> {
    private List<Show> showList;
    private OnShowListener mClickListener;
    private Context mContext;

    public PopularShowsAdapter(Context context, OnShowListener clickListener) {
        super(context, R.layout.popular_shows_item);
        mContext = context;
        mClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return showList == null ? 0 : showList.size();
    }

    @Override
    public Show getItem(int position) {
        return showList.get(position);
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
                    .inflate(R.layout.popular_shows_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, final int position) {
        Glide
                .with(mContext)
                .load(showList.get(position).images().poster().get("full"))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.thumb());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onShowClick(showList.get(position));
            }
        });

    }

    public void updateShows(List<Show> shows){
        showList = shows;
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        private View mView;
        private ImageView mThumb;
        public ViewHolder(View root) {
            mView = root;
            mThumb = (ImageView) root.findViewById(R.id.popular_shows_thumb);
        }
        public View view() {
            return mView;
        }

        public ImageView thumb(){ return mThumb;}
    }
}
