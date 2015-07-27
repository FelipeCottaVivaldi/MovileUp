package com.movile.up.seriestracker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.util.FormatUtil;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsInfoFragment extends Fragment {
    private View mView;
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_FIRSTAIRED = "firstaired";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_LANGUAGE = "language";

    private String mOverview;
    private String mStatus;
    private String mFirstAired;
    private String mCountry;
    private String mLanguage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_details_info_fragment, container, false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle b = this.getArguments();
        mOverview = b.getString(EXTRA_OVERVIEW);
        mStatus = b.getString(EXTRA_STATUS);
        mFirstAired = b.getString(EXTRA_FIRSTAIRED);
        mCountry = b.getString(EXTRA_COUNTRY);
        mLanguage = b.getString(EXTRA_LANGUAGE);

        displayInfo();
    }

    private void displayInfo() {
        ((TextView) mView.findViewById(R.id.show_details_overview)).setText(mOverview);
        ((TextView) mView.findViewById(R.id.show_details_informations)).setText("Status: " + mStatus + "\nFirst Aired: " + FormatUtil.formatDate(FormatUtil.formatDate(mFirstAired))
                + "\nCountry: " + mCountry + "\nLanguage: " + mLanguage);
    }
}
