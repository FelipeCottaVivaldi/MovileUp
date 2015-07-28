package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity$Adapter;
import com.movile.up.seriestracker.interfaces.OnFavoritesClick;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    private Context mContext;
    private OnFavoritesClick mListener;

    public FavoritesAdapter(Context context, Cursor c, int flags, OnFavoritesClick listener) {
        super(context, c, flags);
        mListener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        FavoriteEntity$Adapter favoriteAdapter = new FavoriteEntity$Adapter();
        final FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteAdapter.loadFromCursor(cursor, favoriteEntity);

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.title().setText(favoriteEntity.title());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFavoritesClick(favoriteEntity.slug());
            }
        });

    }

    public static class ViewHolder{
        private View mView;
        private TextView mTitle;
        public ViewHolder(View root) {
            mView = root;
            mTitle = (TextView) root.findViewById(R.id.favorite_title);
        }
        public View view() {
            return mView;
        }

        public TextView title(){ return mTitle;}
    }
}
