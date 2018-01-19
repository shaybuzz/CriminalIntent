package com.example.shaybuzz.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by shaybuzz on 20/01/2018.
 */

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.example.shaybuzz.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        mViewPager = findViewById(R.id.crimes_pager);
        PagerAdapter adapter = new CrimePagerAdapter(CrimeLab.get(this).getCrimes());
        mViewPager.setAdapter(adapter);
        if (getIntent() != null) {
            UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
            int index = CrimeLab.get(this).getIndex(crimeId);
            if (index >= 0) {
                mViewPager.setCurrentItem(index, false);
            }

        }
    }

    private class CrimePagerAdapter extends FragmentStatePagerAdapter {

        private List<Crime> crimes;

        public CrimePagerAdapter(List<Crime> crimes) {
            super(getSupportFragmentManager());
            this.crimes = crimes;
        }

        @Override
        public int getCount() {
            return crimes.size();
        }


        @Override
        public Fragment getItem(int position) {
            return CrimeFragment.newInstance(crimes.get(position).getId());
        }

    }
}
