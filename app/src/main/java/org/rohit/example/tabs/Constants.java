package org.rohit.example.tabs;

import android.support.v4.app.Fragment;

/**
 * Constant class for providing all the constant fields
 */

public class Constants {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Titles of the individual pages (displayed in tabs)
    public static final String[] PAGE_TITLES = new String[] {
            "Page 1",
            "Page 2",
            "Page 3"
    };

    // The fragments that are used as the individual pages
    public static final Fragment[] PAGES = new Fragment[] {
            new Page1Fragment(),
            new Page2Fragment(),
            new Page3Fragment()
    };
}
