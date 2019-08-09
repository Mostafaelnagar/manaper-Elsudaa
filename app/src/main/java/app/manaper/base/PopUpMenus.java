package app.manaper.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;


import java.util.ArrayList;
import java.util.List;

import app.manaper.R;
import app.manaper.base.constantsutils.Codes;
import app.manaper.models.cities.Cities;
import app.manaper.models.company.RelatedPlacesItem;
import app.manaper.models.delegate.firstDetails.DelegatePlaces;

public class PopUpMenus {

    public static PopupMenu showCitiesPopUp(Context context, View view, List<Cities> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        for (int i = 0; i < types.size(); i++) {
            typesPopUps.getMenu().add(i, i, i, types.get(i).getName());
        }
        typesPopUps.show();
        return typesPopUps;
    }

    public static PopupMenu showPlacesPopUp(Context context, View view, List<RelatedPlacesItem> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        for (int i = 0; i < types.size(); i++) {
            typesPopUps.getMenu().add(i, i, i, types.get(i).getPlacesName());
        }
        typesPopUps.show();
        return typesPopUps;
    }

    public static PopupMenu showDelegatePlacesPopUp(Context context, View view, List<DelegatePlaces> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        if (types != null) {
            for (int i = 0; i < types.size(); i++) {
                typesPopUps.getMenu().add(i, i, i, types.get(i).getPlacesName());
            }

            typesPopUps.show();
        }
        return typesPopUps;
    }

    public static PopupMenu showTransPopUp(Context context, View view, List<PopUpItem> types) {
        PopupMenu typesPopUps;
        typesPopUps = new PopupMenu(context, view);
        for (int i = 0; i < types.size(); i++) {
            typesPopUps.getMenu().add(i, i, i, types.get(i).getName());
        }
        typesPopUps.show();
        return typesPopUps;
    }

    public static ArrayList<PopUpItem> getTransportationTypes(Context context) {
        ArrayList<PopUpItem> types = new ArrayList<>();
        types.add(new PopUpItem(Codes.MANAPER_TRANSPORTATION, context.getResources().getStringArray(R.array.transportation)[0]));
        types.add(new PopUpItem(Codes.OTHER, context.getResources().getStringArray(R.array.transportation)[1]));
        return types;
    }

//    public static List<RelatedPlacesItem> newPlacesItemView(View view, Context context) {
//
//        List<RelatedPlacesItem> relatedPlacesItems = new ArrayList<>();
////        LinearLayout parentLinearLayout = (LinearLayout) view.findViewById(R.id.parent_linear_layout);
////        view.findViewById(R.id.newPlaces).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////                final View rowView = inflater.inflate(R.layout.attractive_item, null);
////
////                // Add the new row before the add field button.
////                parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
////
////            }
////        });
//        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = vi.inflate(R.layout.attractive_item, null);
//
//
//        // insert into main view
//        ViewGroup insertPoint = (ViewGroup) view.findViewById(R.id.parent_linear_layout);
//        insertPoint.findViewById(R.id.newPlaces).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            }
//        });
//
//
//    }
}
