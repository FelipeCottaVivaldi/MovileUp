package com.movile.up.seriestracker.interfaces;

import com.movile.up.seriestracker.model.Episode;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public interface SeasonDetailsView {
    void displayEpisodes(List<Episode> episode);
}
