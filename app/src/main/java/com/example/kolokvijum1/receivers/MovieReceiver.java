package com.example.kolokvijum1.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MovieReceiver extends BroadcastReceiver {

    private static float najvisaOcena = 0;
    private static String najboljiFilm = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("DODAT_FILM".equals(intent.getAction())) {
            String naziv = intent.getStringExtra("naziv");
            float ocena = intent.getFloatExtra("ocena", 0f);

            if (ocena > najvisaOcena) {
                najvisaOcena = ocena;
                najboljiFilm = naziv;
            }

            Toast.makeText(context, "Film sa najvećom ocenom: " + najboljiFilm + " (" + najvisaOcena + ")", Toast.LENGTH_LONG).show();
        }
    }

}
