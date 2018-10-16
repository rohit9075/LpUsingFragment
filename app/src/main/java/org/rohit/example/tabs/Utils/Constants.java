package org.rohit.example.tabs.Utils;

import android.support.v4.app.Fragment;

import org.rohit.example.tabs.activities.MainActivity;
import org.rohit.example.tabs.fragments.Page1Fragment;
import org.rohit.example.tabs.fragments.Page2Fragment;
import org.rohit.example.tabs.fragments.Page3Fragment;

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
