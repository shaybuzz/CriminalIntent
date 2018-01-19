package com.example.shaybuzz.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by shaybuzz on 20/01/2018.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.fragment_crime_list,
                        container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void updateUI() {
        if(crimeAdapter == null || true) {
            List<Crime> crimes = CrimeLab.get(getActivity()).getCrimes();
            crimeAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(new CrimeAdapter(crimes));
        }else{
            crimeAdapter.notifyDataSetChanged();
            mCrimeRecyclerView.invalidate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView crimeTitle, crimeDate;
        private ImageView crimeSolved;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            crimeDate = itemView.findViewById(R.id.crime_date);
            crimeTitle = itemView.findViewById(R.id.crime_title);
            crimeSolved = itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
        }

        public void bind(Crime crime) {
            this.crime = crime;
            String formatDate = DateFormat.getInstance().format(crime.getDate());
            crimeDate.setText(formatDate);
            crimeTitle.setText(crime.getTitle());
            crimeSolved.setVisibility(crime.isSolved()? View.VISIBLE: View.GONE);
        }

        @Override
        public void onClick(View v) {
            startActivity(CrimePagerActivity.newIntent(getActivity(), crime.getId()));
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CrimeHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            holder.bind(crimes.get(position));
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}
