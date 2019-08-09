package app.manaper.customViews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.BaseFragment;
import app.manaper.base.MovementManager;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.filesutils.FileOperations;
import app.manaper.base.volleyutils.MyApplication;
import app.manaper.databinding.CustomeNavigationDrawerBinding;
import app.manaper.view.HomeFragment;
import app.manaper.view.VideoFragment;
import app.manaper.view.mandop.DelHomeFragment;

public class NavView extends RelativeLayout {
    public CustomeNavigationDrawerBinding drawerBinding;
    NavViewModel navViewModel;
    Toolbar toolbar;
    RecyclerView recyclerViewNavItems;
    ActionBarDrawerToggle mDrawerToggle;
    List<NavItems> navItemsList = new ArrayList<>();

    public NavView(Context context) {
        super(context);
        onCreateView();
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreateView();
    }

    public NavView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onCreateView();
    }


    public void onCreateView() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        drawerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.custome_navigation_drawer, this, true);
        navViewModel = new NavViewModel();
        drawerBinding.setVavViewModel(navViewModel);
        initViews();
        setupToolbar();
        fillNavitems();
        setupDrawerToggle();


        navViewModel.getNavAdapter().getCloseDrawbleListner().observeForever(new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                drawerBinding.customDrawer.closeDrawers();
            }
        });
        navViewModel.getNavAdapter().getTitleToolbar().observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals(getActivity().getResources().getString(R.string.menuLogOut))) {
                    loggout();
                } else {
                    drawerBinding.toolbarTitle.setText(s);

                }
            }
        });
        liveDataListeners();

    }

    private void initViews() {
        recyclerViewNavItems = drawerBinding.recNavItems;
        recyclerViewNavItems.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNavItems.setHasFixedSize(true);
    }

    private void fillNavitems() {
        Resources resources = getActivity().getResources();
        navItemsList.add(new NavItems(resources.getString(R.string.menu_home), 1, R.drawable.ic_home));
        if (UserPreferenceHelper.getInstance(getActivity()).getUserData().getUsersType() == 1) {
            navItemsList.add(new NavItems(resources.getString(R.string.menuLastTrip), 2, R.drawable.ic_last_trip));
            navItemsList.add(new NavItems(resources.getString(R.string.menuAddTrip), 3, R.drawable.ic_add_trip));
        }
        if (UserPreferenceHelper.getInstance(getActivity()).getUserData().getUsersType() == 0) {
            navItemsList.add(new NavItems(resources.getString(R.string.menuUrgetTrip), 4, R.drawable.ic_last_trip));
            navItemsList.add(new NavItems(resources.getString(R.string.menuMyTrips), 5, R.drawable.ic_add_trip));
        }
        navItemsList.add(new NavItems(resources.getString(R.string.menuNotfication), 6, R.drawable.ic_notifications));
        navItemsList.add(new NavItems(resources.getString(R.string.menuVideo), 7, R.drawable.ic_video));
        navItemsList.add(new NavItems(resources.getString(R.string.menuSuggestion), 8, R.drawable.ic_suggestion));
        navItemsList.add(new NavItems(resources.getString(R.string.menuContact), 9, R.drawable.ic_contact));
        navItemsList.add(new NavItems(resources.getString(R.string.menuAbout), 10, R.drawable.ic_about));
        navItemsList.add(new NavItems(resources.getString(R.string.languageChangeText), 11, R.drawable.ic_lang));
        navItemsList.add(new NavItems(resources.getString(R.string.menuLogOut), 12, R.drawable.ic_sign_out));
        navViewModel.setNavItems(navItemsList);
    }

    public NavViewModel getNavViewModel() {
        return navViewModel;
    }

    void setupToolbar() {
        toolbar = (Toolbar) drawerBinding.customToolbar;
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle((AppCompatActivity) getActivity(), drawerBinding.customDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerBinding.customDrawer.openDrawer(GravityCompat.START);
            }
        });
        mDrawerToggle.syncState();
    }

    private void liveDataListeners() {
        navViewModel.getClicksMutableLiveData().observeForever(result -> {
            if (result == Codes.PROFILE) {
                MovementManager.startActivity(getActivity(), Codes.PROFILE);
            } else if (result == Codes.GRAND_INFO) {
                showGrandDialog();
            }

        });
    }

    private void showGrandDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.grand_dialog);
        ImageView dialogClose = dialog.findViewById(R.id.dialogClose);
        TextView whatsAppClick = dialog.findViewById(R.id.whatsApp);
        TextView grandPhone = dialog.findViewById(R.id.grandPhone);
        TextView grandCall = dialog.findViewById(R.id.grandCall);
        TextView grandSite = dialog.findViewById(R.id.grandSite);
        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        grandCall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MovementManager.startCall(getActivity(), grandPhone.getText().toString());
            }
        });
        whatsAppClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MovementManager.startWhatsApp(getActivity(), grandPhone.getText().toString());
            }
        });
        grandSite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MovementManager.startWebPage(getActivity(), grandSite.getText().toString());
                } catch (Exception e) {
                    Log.i("onClick", "onClick: " + e.getMessage());
                }
            }
        });

        dialog.show();
    }

    public void loggout() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog_MinWidth);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.logout_item);
        TextView negative_Button = dialog.findViewById(R.id.reject);
        TextView postive_Button = dialog.findViewById(R.id.accept);
        negative_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        postive_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovementManager.startBaseActivity(getActivity(), Codes.LOGIN_SCREEN);
                UserPreferenceHelper.getInstance(getActivity()).loggout();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    Context getActivity() {
        return getContext();
    }


}
