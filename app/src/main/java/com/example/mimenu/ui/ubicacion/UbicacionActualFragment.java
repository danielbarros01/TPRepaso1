package com.example.mimenu.ui.ubicacion;

import androidx.annotation.NonNull;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mimenu.R;
import com.example.mimenu.databinding.FragmentUbicacionActualBinding;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class UbicacionActualFragment extends Fragment {

    private FragmentUbicacionActualBinding binding;
    private UbicacionActualViewModel mViewModel;

    public static UbicacionActualFragment newInstance() {
        return new UbicacionActualFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ubicacion_actual, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);//objeto que se encarga de gestionar el ciclo de vida del fragment
        smf.getMapAsync(new MapaActual()); //traeme el mapa cuando lo tengas
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UbicacionActualViewModel.class);
        // TODO: Use the ViewModel
    }

    private class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) { //me devuelve el mapa
            //Localizacion
            FusedLocationProviderClient fused;
            fused = LocationServices.getFusedLocationProviderClient(getActivity());
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Task<Location> tarea = fused.getLastLocation();

            tarea.addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) { //despues de la espera y me devuelve la promesa, una location:
                    if (location != null) {
                        LatLng ubi = new LatLng(location.getLatitude(), location.getLongitude());

                        //Lo de mapa de google
                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //Tipo de mapa

                        googleMap.addMarker(new MarkerOptions().position(ubi).title("Donde estoy")); //marcador

                        //Hacer mas dinamico el mapa
                        CameraPosition camPos =
                                new CameraPosition.Builder()
                                        .target(ubi)
                                        .zoom(18)
                                        .bearing(0) //ángulo de rotación de la cámara alrededor de su eje vertical en sentido horario, medida en grados.
                                        .tilt(0) //se establece en 70, lo que representa el ángulo de inclinación de la cámara en relación con la superficie del mapa.
                                        .build();

                        CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);

                        //Hace la animacion
                        googleMap.animateCamera(update);
                        //----
                    }
                }
            });


        }
    }

}