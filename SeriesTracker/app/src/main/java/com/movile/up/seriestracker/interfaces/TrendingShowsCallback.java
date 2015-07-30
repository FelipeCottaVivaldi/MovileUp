package com.movile.up.seriestracker.interfaces;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public interface TrendingShowsCallback<X> {
    public void OnShowsSuccess(List<X> x);
}
