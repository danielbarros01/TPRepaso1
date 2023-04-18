package com.example.mimenu.ui.musica;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mimenu.R;

public class MusicaFragment extends Fragment {

    private MusicaViewModel mViewModel;
    private ImageButton btnPlay, btnNext;

    public static MusicaFragment newInstance() {
        return new MusicaFragment();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musica, container, false);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnNext = view.findViewById(R.id.btnNext);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                next();
            }
        });
    }

    public void pause() {
        btnPlay.setImageResource(android.R.drawable.ic_media_play);

        //Pausar la cancion
        Intent intent = new Intent(getActivity(), ServicioMusical.class);
        intent.setAction("PAUSE");
        getActivity().startService(intent);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }

    public void play() {
        btnPlay.setImageResource(android.R.drawable.ic_media_pause);

        //Darle que ande
        Intent intent = new Intent(getActivity(), ServicioMusical.class);
        intent.setAction("PLAY");

        getActivity().startService(intent);


        // Definir un nuevo OnClickListener para el botón
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });
    }

    public void next() {
        //Siguiente cancion
        Intent intent = new Intent(getActivity(), ServicioMusical.class);
        intent.setAction("NEXT");
        getActivity().startService(intent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MusicaViewModel.class);
        // TODO: Use the ViewModel
    }
}