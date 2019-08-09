package app.manaper.view.mandop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MandopMyTripTabFragment extends Fragment {


    public MandopMyTripTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mandop_my_trip_tab, container, false);
    }

}
