package app.manaper.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.R;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.DelTripsItemBinding;
import app.manaper.databinding.TripsItemBinding;
import app.manaper.models.allTrips.AllTrips;
import app.manaper.models.delegate.DelTripData;
import app.manaper.viewModels.HomeItemViewModels;
import app.manaper.viewModels.delegate.DelegateHomeItemViewModels;

public class DelHomeAdapter extends RecyclerView.Adapter<DelHomeAdapter.ViewHolder> {
    public List<DelTripData> tripsList;
    Context context;

    public DelHomeAdapter() {
        tripsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.del_trips_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DelTripData dataModel = tripsList.get(position);
        DelegateHomeItemViewModels homeItemViewModels = new DelegateHomeItemViewModels(dataModel, position);
        homeItemViewModels.getItemsOperationsLiveListener().observe((LifecycleOwner) context, new Observer<DelTripData>() {
            @Override
            public void onChanged(DelTripData delTripData) {
                String arrType = dataModel.getTripArrDepsType() + "";
                String secType = dataModel.getTripFirSecsType() + "";
                if (dataModel.getTripArrDepsType() != null) {
                    if (arrType.equals("0")) {
                        MovementManager.startActivity(context, Codes.DELEGATE_TRIP_ARRIVAL);
                    } else if (arrType.equals("1")) {
                        MovementManager.startActivity(context, Codes.DELEGATE_TRIP_DEP);
                    }
                    UserPreferenceHelper.getInstance(context).addArrDepsId(arrType + "");
                    UserPreferenceHelper.getInstance(context).addTripId(dataModel.getTripArrDepsId() + "");

                } else if (dataModel.getTripFirSecsType() != null) {
                    if (secType.equals("0")) {
                        MovementManager.startActivity(context, Codes.DELEGATE_TRIP_FIR);
                    } else if (secType.equals("1")) {
                        MovementManager.startActivity(context, Codes.DELEGATE_TRIP_SEC);
                    }
                    UserPreferenceHelper.getInstance(context).addTripId(dataModel.getTripFirSecsId() + "");
                    UserPreferenceHelper.getInstance(context).addSecDepsId(secType);
                }
                Common.visiable = 0;
                notifyItemChanged(position);
            }
        });
        holder.setViewModel(homeItemViewModels);
    }

    @Override
    public int getItemCount() {
        return this.tripsList.size();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<DelTripData> data) {

        this.tripsList.clear();

        this.tripsList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DelTripsItemBinding itemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemBinding == null) {
                itemBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemBinding != null) {
                itemBinding.unbind();
            }
        }

        void setViewModel(DelegateHomeItemViewModels itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setHomeItemViewModels(itemViewModels);
            }
        }
    }
}
