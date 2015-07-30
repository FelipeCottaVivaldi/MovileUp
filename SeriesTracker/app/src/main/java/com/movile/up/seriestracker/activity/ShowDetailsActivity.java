package com.movile.up.seriestracker.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.ShowDetailsAdapter;
import com.movile.up.seriestracker.database.manual.dao.FavoriteDAO;
import com.movile.up.seriestracker.interfaces.OnFavoriteClickListener;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements OnFavoriteClickListener {

    private Fragment mCurrentFragment;
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_SCREENSHOT = "screenshot";
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_FIRSTAIRED = "firstaired";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_LANGUAGE = "language";
    private String mShow;
    private String mScreenshot;
    private Double mRating;
    private String mTitle;
    private String mOverview;
    private String mStatus;
    private String mFirstAired;
    private String mCountry;
    private String mLanguage;
    Favorite favorite;
    FavoriteDAO favoriteDAO = new FavoriteDAO(this);
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        extractInformationsFromExtra();
        getSupportActionBar().setTitle(mTitle);
        fab = (FloatingActionButton) findViewById(R.id.show_details_favorite);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick();
            }
        });

        displayInformation();

        favorite = favoriteDAO.query(mShow);
        if (favorite == null) {
            fab.setImageResource(R.drawable.show_details_favorite_off);
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.default_textColor_third));
        } else {
            fab.setImageResource(R.drawable.show_details_favorite_on);
            fab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
        }
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(new ShowDetailsAdapter(getSupportFragmentManager(), this, mShow, mOverview, mStatus, mFirstAired, mCountry, mLanguage));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);
    }

    private void displayInformation() {
        Glide
                .with(this)
                .load(mScreenshot)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.show_details_screenshot));
        ((TextView) findViewById(R.id.show_details_rating)).setText(mRating.toString());

    }

    private void extractInformationsFromExtra() {
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mTitle = extras.getString(EXTRA_TITLE);
        mRating = extras.getDouble(EXTRA_RATING);
        mScreenshot = extras.getString(EXTRA_SCREENSHOT);
        mOverview = extras.getString(EXTRA_OVERVIEW);
        mStatus = extras.getString(EXTRA_STATUS);
        mFirstAired = extras.getString(EXTRA_FIRSTAIRED);
        mCountry = extras.getString(EXTRA_COUNTRY);
        mLanguage = extras.getString(EXTRA_LANGUAGE);
    }

    @Override
    public void onFavoriteClick() {

        final FloatingActionButton button = (FloatingActionButton) findViewById(R.id.show_details_favorite);


        ObjectAnimator animationX = ObjectAnimator.ofFloat(button, "scaleX", 1, 0);
        animationX.setDuration(200);
        animationX.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {

                if (favorite == null) {
                    fab.setImageResource(R.drawable.show_details_favorite_on);
                    fab.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_second));
                    favorite = new Favorite(mShow, mTitle);
                    favoriteDAO.save(favorite);
                } else {
                    fab.setImageResource(R.drawable.show_details_favorite_off);
                    fab.setBackgroundTintList(getResources().getColorStateList(R.color.default_textColor_third));
                    favorite = null;
                    favoriteDAO.delete(mShow);
                }

                ObjectAnimator animationY = ObjectAnimator.ofFloat(button, "scaleX", 0, 1);
                animationY.setDuration(200);
                animationY.start();
            }
        });
        animationX.start();


    }
}
