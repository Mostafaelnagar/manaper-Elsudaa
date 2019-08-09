package app.manaper.adapters;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import app.manaper.R;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.common.Common;
import app.manaper.databinding.TripsItemBinding;
import app.manaper.models.allTrips.AllTrips;
import app.manaper.view.company.TripDetailsFragment;
import app.manaper.viewModels.HomeItemViewModels;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public List<AllTrips> tripsList;
    Context context;

    public HomeAdapter() {
        tripsList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_item,
                parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AllTrips dataModel = tripsList.get(position);
        HomeItemViewModels homeItemViewModels = new HomeItemViewModels(dataModel, position);
        homeItemViewModels.getItemsOperationsLiveListener().observeForever(new Observer<AllTrips>() {
            @Override
            public void onChanged(AllTrips allTrips) {
                Log.e("onChanged", "onChanged: " + dataModel.getFKTripsId());
                UserPreferenceHelper.getInstance(context).addTripId(dataModel.getFKTripsId());
                MovementManager.startActivity(context, Codes.TRIP_DETAILS);
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

    public void updateData(@Nullable List<AllTrips> data) {

        this.tripsList.clear();

        this.tripsList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TripsItemBinding itemBinding;

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

        void setViewModel(HomeItemViewModels itemViewModels) {
            if (itemBinding != null) {
                itemBinding.setHomeItemViewModels(itemViewModels);
            }
        }
    }
}
