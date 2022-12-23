// Generated by view binder compiler. Do not edit!
package com.example.rickandmorty.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.rickandmorty.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentLocationsListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rvCardLocation;

  @NonNull
  public final Button searchLocationButton;

  @NonNull
  public final EditText searchLocationDimension;

  @NonNull
  public final EditText searchLocationType;

  @NonNull
  public final EditText searchLocatonName;

  @NonNull
  public final Button undoSearchLocationButton;

  private FragmentLocationsListBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView rvCardLocation, @NonNull Button searchLocationButton,
      @NonNull EditText searchLocationDimension, @NonNull EditText searchLocationType,
      @NonNull EditText searchLocatonName, @NonNull Button undoSearchLocationButton) {
    this.rootView = rootView;
    this.rvCardLocation = rvCardLocation;
    this.searchLocationButton = searchLocationButton;
    this.searchLocationDimension = searchLocationDimension;
    this.searchLocationType = searchLocationType;
    this.searchLocatonName = searchLocatonName;
    this.undoSearchLocationButton = undoSearchLocationButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentLocationsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentLocationsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_locations_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentLocationsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rv_card_location;
      RecyclerView rvCardLocation = ViewBindings.findChildViewById(rootView, id);
      if (rvCardLocation == null) {
        break missingId;
      }

      id = R.id.search_location_button;
      Button searchLocationButton = ViewBindings.findChildViewById(rootView, id);
      if (searchLocationButton == null) {
        break missingId;
      }

      id = R.id.search_location_dimension;
      EditText searchLocationDimension = ViewBindings.findChildViewById(rootView, id);
      if (searchLocationDimension == null) {
        break missingId;
      }

      id = R.id.search_location_type;
      EditText searchLocationType = ViewBindings.findChildViewById(rootView, id);
      if (searchLocationType == null) {
        break missingId;
      }

      id = R.id.search_locaton_name;
      EditText searchLocatonName = ViewBindings.findChildViewById(rootView, id);
      if (searchLocatonName == null) {
        break missingId;
      }

      id = R.id.undo_search_location_button;
      Button undoSearchLocationButton = ViewBindings.findChildViewById(rootView, id);
      if (undoSearchLocationButton == null) {
        break missingId;
      }

      return new FragmentLocationsListBinding((ConstraintLayout) rootView, rvCardLocation,
          searchLocationButton, searchLocationDimension, searchLocationType, searchLocatonName,
          undoSearchLocationButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}