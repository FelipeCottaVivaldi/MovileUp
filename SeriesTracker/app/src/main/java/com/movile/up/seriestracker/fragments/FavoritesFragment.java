package com.movile.up.seriestracker.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.adapter.FavoritesAdapter;
import com.movile.up.seriestracker.interfaces.OnFavoritesClick;
import com.movile.up.seriestracker.interfaces.OnFavoritesLoaderListener;
import com.movile.up.seriestracker.loader.FavoritesLoaderCallback;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesFragment extends Fragment implements OnFavoritesLoaderListener, OnFavoritesClick {

    FavoritesAdapter adapter;
    public static final String EXTRA_SHOW = "show";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorites_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listView = (ListView) getActivity().findViewById(R.id.favorites_list_view);
        adapter = new FavoritesAdapter(this.getActivity(), null, 0, this);
        listView.setAdapter(adapter);
        getLoaderManager().initLoader(0, null, new FavoritesLoaderCallback(this.getActivity(), this)).forceLoad();
    }

    @Override
    public void onFavoritesSuccess(Cursor cursor) {
        if (cursor.equals(null)) {
            Glide
                    .with(this)
                    .load(R.drawable.favorites_header_tv_unhappy)
                    .fitCenter()
                    .into((ImageView) this.getActivity().findViewById(R.id.favorites_tv));
            adapter.swapCursor(cursor);
        } else {
            Glide
                    .with(this)
                    .load(R.drawable.favorites_header_tv_happy)
                    .fitCenter()
                    .into((ImageView) this.getActivity().findViewById(R.id.favorites_tv));
            adapter.swapCursor(cursor);
        }
    }

    @Override
    public void onFavoritesClick(String slug) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, slug);
        startActivity(intent);
    }
}
