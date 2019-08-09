package app.manaper.view;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.databinding.FragmentHomeBinding;
import app.manaper.viewModels.HomeViewModels;

public class HomeFragment extends BaseFragment {
    FragmentHomeBinding homeBinding;
    HomeViewModels homeViewModels;
    int type;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        if (UserPreferenceHelper.getInstance(getActivity()).getUserData().getUsersType() == 0)
            type = 3;
        else
            type = 1;
        homeViewModels = new HomeViewModels(type);
        homeBinding.setHomeViewModel(homeViewModels);
        liveDataListeners();
        if (type == 1)
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(homeBinding.recHome);

        return homeBinding.getRoot();
    }

    private void liveDataListeners() {
        homeViewModels.getClicksMutableLiveData().observe(this, result -> {
            if (result == View.VISIBLE || result == View.GONE) {
                accessLoadingBar(result);
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            deleteItemtrip(viewHolder.getAdapterPosition());
        }
    };

    public void deleteItemtrip(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        builder.setMessage(getActivity().getString(R.string.itemDelete))
                .setCancelable(false)
                .setPositiveButton(getActivity().getString(R.string.yesText),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                homeViewModels.deleteOneTrip(position);
                                homeViewModels.notifyChange();
                            }
                        })
                .setNegativeButton(getActivity().getString(R.string.noText), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homeViewModels.notifyChange();
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
