package org.rohit.example.tabs.Utils;

import android.support.v4.app.Fragment;

import org.rohit.example.tabs.activities.MainActivity;
import org.rohit.example.tabs.fragments.CandidateLoginFragment;
import org.rohit.example.tabs.fragments.Candidate_Register_Fragment;

/**
 * Constant class for providing all the constant fields
 */

public class Constants {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Titles of the individual pages (displayed in tabs)
    public static final String[] PAGE_TITLES = new String[] {
            "Login",
            "Register",
    };

    // The fragments that are used as the individual pages
    public static final Fragment[] PAGES = new Fragment[] {
            new CandidateLoginFragment(),
            new Candidate_Register_Fragment(),
    };
}
