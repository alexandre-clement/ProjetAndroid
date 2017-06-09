package fr.unice.polytech.tobeortohave.fragment.home.traffic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.unice.polytech.tobeortohave.R;

/**
 * @author Alexandre Clement
 *         Created the 12/05/2017.
 */

public class TrafficOverview extends Fragment
{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.traffic_overview, container, false);
        final SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(new MapCallback());
        return view;
    }

    private class MapCallback implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            googleMap.setTrafficEnabled(true);
            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            double latitude = 43.61691600000005;
            double longitude = 7.069540999999958;
            LatLng latLng = new LatLng(latitude, longitude);
            MarkerOptions marker = new MarkerOptions().position(latLng).title("Cap Sophia");
            googleMap.setOnMapClickListener(new ClickListener());
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
            googleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private class ClickListener implements GoogleMap.OnMapClickListener
    {
        @Override
        public void onMapClick(LatLng latLng)
        {
            view.callOnClick();
        }
    }
}
