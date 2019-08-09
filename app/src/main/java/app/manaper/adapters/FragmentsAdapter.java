package app.manaper.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import app.manaper.view.company.ArrivalFragment;
import app.manaper.view.company.DepartureFragment;
import app.manaper.view.company.FirstCityFragment;
import app.manaper.view.company.SecondCityFragment;
import app.manaper.view.mandop.MandopMyTripTabFragment;
import app.manaper.view.mandop.MandopTodayTripsFragment;
import app.manaper.view.mandop.MandopTomorrowTripsFragment;

public class FragmentsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    /*
    0 =>trip details
    1 =>mandop urgent trip details
    2 =>mandop Mytrip details
    */
    int type;

    public FragmentsAdapter(FragmentManager fm, int NoofTabs, int type) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        if (mNumOfTabs == 2) {
            if (type == 1) {
                switch (position) {
                    case 0:
                        MandopTodayTripsFragment todayTripsFragment = new MandopTodayTripsFragment();
                        return todayTripsFragment;
                    case 1:
                        MandopTomorrowTripsFragment tomorrowTripsFragment = new MandopTomorrowTripsFragment();
                        return tomorrowTripsFragment;
                    default:
                        return null;
                }
            } else {
                switch (position) {
                    case 0:
                        MandopMyTripTabFragment myTripTabFragment = new MandopMyTripTabFragment();
                        return myTripTabFragment;
                    case 1:
                        MandopTomorrowTripsFragment tomorrowTripsFragment = new MandopTomorrowTripsFragment();
                        return tomorrowTripsFragment;
                    default:
                        return null;
                }
            }
        } else {
            switch (position) {
                case 0:
                    return new ArrivalFragment();
                case 1:
                    return new FirstCityFragment();
                case 2:
                    return new SecondCityFragment();
                case 3:
                    return new DepartureFragment();
                default:
                    return null;
            }
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
