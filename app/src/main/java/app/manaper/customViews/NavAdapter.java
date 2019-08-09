package app.manaper.customViews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.manaper.R;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.databinding.NavItemBinding;
import app.manaper.view.AboutFragment;
import app.manaper.view.BaseActivity;
import app.manaper.view.ContactFragment;
import app.manaper.view.HomeFragment;
import app.manaper.view.NotificationsFragment;
import app.manaper.view.SignUpFragment;
import app.manaper.view.SuggestionFragment;
import app.manaper.view.VideoFragment;
import app.manaper.view.company.LastTripsFragment;
import app.manaper.view.company.TripDetailsFragment;
import app.manaper.view.company.addTrip.AddTripMainFragment;
import app.manaper.view.mandop.DelHomeFragment;
import app.manaper.view.mandop.MandopMyTripFragment;
import app.manaper.view.mandop.MandopUrgentTripsFragment;

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ViewHolder> {
    Context context;
    List<NavItems> navItemsList;
    MutableLiveData<Void> closeDrawbleListner;
    MutableLiveData<String> titleToolbar;

    public NavAdapter() {
        navItemsList = new ArrayList<>();
        closeDrawbleListner = new MutableLiveData<>();
        titleToolbar = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    public MutableLiveData<Void> getCloseDrawbleListner() {
        return closeDrawbleListner;
    }

    public MutableLiveData<String> getTitleToolbar() {
        return titleToolbar;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavItems details = navItemsList.get(position);
        final NavItemViewModel itemViewModel = new NavItemViewModel(details);
        itemViewModel.getItemsOperationsLiveListenerl().observe((LifecycleOwner) context, new Observer<NavItems>() {
            @Override
            public void onChanged(NavItems navItems) {
                closeDrawbleListner.setValue(null);
                Resources resources = context.getResources();
                if (UserPreferenceHelper.getInstance(context).getUserData().getActiveStatus() == 1) {
                    if (details.getId() == 1) {
                        if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getUsersType() == 1)
                            MovementManager.addHomeFragment(context, new HomeFragment(), "");
                        else
                            MovementManager.addHomeFragment(context, new DelHomeFragment(), "");

                        titleToolbar.setValue(resources.getString(R.string.menu_home));
                    } else if (details.getId() == 2) {
                        MovementManager.replaceHomeFragment(context, new LastTripsFragment(), "");
                        titleToolbar.setValue(resources.getString(R.string.menuLastTrip));

                    } else if (details.getId() == 3) {
                        MovementManager.replaceHomeFragment(context, new AddTripMainFragment(), "");
                        titleToolbar.setValue(resources.getString(R.string.menuAddTrip));

                    } else if (details.getId() == 4) {
                        MovementManager.replaceHomeFragment(context, new MandopUrgentTripsFragment(), "");
                        titleToolbar.setValue(resources.getString(R.string.menuUrgetTrip));

                    } else if (details.getId() == 5) {
                        MovementManager.replaceHomeFragment(context, new MandopMyTripFragment(), "");
                        titleToolbar.setValue(resources.getString(R.string.menuMyTrips));

                    }
                } else
                    Toast.makeText(context, "" + context.getResources().getString(R.string.activeStatus), Toast.LENGTH_LONG).show();
                if (details.getId() == 6) {
                    MovementManager.replaceHomeFragment(context, new NotificationsFragment(), "");
                    titleToolbar.setValue(resources.getString(R.string.menuNotfication));

                } else if (details.getId() == 7) {
                    MovementManager.replaceHomeFragment(context, new VideoFragment(), "");
                    titleToolbar.setValue(resources.getString(R.string.menuVideo));

                } else if (details.getId() == 8) {
                    MovementManager.replaceHomeFragment(context, new SuggestionFragment(), "");
                    titleToolbar.setValue(resources.getString(R.string.menuSuggestion));

                } else if (details.getId() == 9) {
                    MovementManager.replaceHomeFragment(context, new ContactFragment(), "");
                    titleToolbar.setValue(resources.getString(R.string.menuContact));

                } else if (details.getId() == 10) {
                    MovementManager.replaceHomeFragment(context, new AboutFragment(), "");
                    titleToolbar.setValue(resources.getString(R.string.menuAbout));

                } else if (details.getId() == 11) {
//                    titleToolbar.setValue(resources.getString(R.string.menuLogOut));
                    languageChange();
                } else if (details.getId() == 12) {
                    titleToolbar.setValue(resources.getString(R.string.menuLogOut));

                }

            }
        });
        holder.setViewModel(itemViewModel);
    }

    @Override
    public int getItemCount() {
        return navItemsList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<NavItems> navItemsList) {
        this.navItemsList.clear();
        this.navItemsList.addAll(navItemsList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        NavItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
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
                itemBinding.unbind(); // Don't forget to unbind
            }
        }

        void setViewModel(NavItemViewModel viewModel) {
            if (itemBinding != null) {
                itemBinding.setNavItemViewModel(viewModel);
            }
        }
    }

    public void languageChange() {
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.language_item);
        Button arButton = dialog.findViewById(R.id.arBtn);
        Button enButton = dialog.findViewById(R.id.enBtn);
        arButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLan("ar");
                dialog.dismiss();
            }
        });
        enButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLan("en");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void changeLan(String lang) {
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        UserPreferenceHelper.getInstance(context).setLanguage(context, lang);
        MovementManager.restart(context);

    }
}
