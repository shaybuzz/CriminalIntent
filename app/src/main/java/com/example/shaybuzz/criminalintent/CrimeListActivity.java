package com.example.shaybuzz.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by shaybuzz on 20/01/2018.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
