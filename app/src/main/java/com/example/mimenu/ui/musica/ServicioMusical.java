package com.example.mimenu.ui.musica;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.mimenu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ServicioMusical extends Service {
    private final List<MediaPlayer> canciones = new ArrayList<>();
    private ListIterator<MediaPlayer> cancionesItr;
    private MediaPlayer cancionActual = null;
    private boolean primeraEjecucion = false;

    @Override
    public void onCreate() {
        super.onCreate();
        canciones.add(MediaPlayer.create(this, R.raw.theclashwhiteriot));
        canciones.add(MediaPlayer.create(this, R.raw.you_gave_your_love_to_me_softly));

        cancionesItr = canciones.listIterator();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (!primeraEjecucion) {
            validarCancionActual();
            primeraEjecucion = true;
        }

        if (action != null && action.equals("PAUSE")) {
            cancionActual.pause();
        }

        if (action != null && action.equals("PLAY")) {
            cancionActual.start();
        }

        if (action != null && action.equals("NEXT")) {
            next(cancionActual);
        }

        return START_STICKY;
    }

    public void validarCancionActual() {
        if (!cancionesItr.hasPrevious()) {
            cancionActual = cancionesItr.next();
        }
    }

    public void next(MediaPlayer cancion) {
        if (cancionesItr.hasNext()) {
            //STOP cancion actual
            cancion.stop();
            //reproducir siguiente
            cancionActual = cancionesItr.next();
        } else {
            cancion.stop();
            // Volver al principio de la lista y reproducir la primera canción
            cancionesItr = canciones.listIterator();
            cancionActual = cancionesItr.next();
        }

        cancionActual.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
