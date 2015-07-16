package com.movile.up.seriestracker.interfaces;
import com.movile.up.seriestracker.model.Episode;

/**
 * Created by android on 7/16/15.
 */
public interface OnOperationListener<X> {
    public void onOperationSucess(X x);
}
