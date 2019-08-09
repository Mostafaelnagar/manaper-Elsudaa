package app.manaper.view.company;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manaper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotDirectDepartureFragment extends Fragment {


    public NotDirectDepartureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_not_direct_departure, container, false);
    }

}
