package com.example.kolokvijum1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.kolokvijum1.adapters.MovieAdapter;
import com.example.kolokvijum1.model.Movie;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;
    private Button btnDodaj, btnSnimaj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Povezujemo se sa layout-om fragmenta
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        // Inicijalizacija elemenata
        recyclerView = view.findViewById(R.id.recyclerView);
        btnDodaj = view.findViewById(R.id.dodajBtn);
        btnSnimaj = view.findViewById(R.id.snimajBtn);

        // Postavljanje RecyclerView-a
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Klik na dugme "Dodaj" otvara formu (AlertDialog)
        btnDodaj.setOnClickListener(v -> prikaziFormuZaDodavanje());

        return view;
    }

    private void prikaziFormuZaDodavanje() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_movie, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        EditText etNaziv = dialogView.findViewById(R.id.etNaziv);
        EditText etOcena = dialogView.findViewById(R.id.etOcena);
        CheckBox cbOdgledano = dialogView.findViewById(R.id.cbOdgledano);
        Button btnPotvrdi = dialogView.findViewById(R.id.btnPotvrdi);
        Button btnOdustani = dialogView.findViewById(R.id.btnOdustani);


        btnOdustani.setOnClickListener(v -> dialog.dismiss());


        btnPotvrdi.setOnClickListener(v -> {
            String naziv = etNaziv.getText().toString().trim();
            String ocenaStr = etOcena.getText().toString().trim();
            boolean odgledano = cbOdgledano.isChecked();

            if (!naziv.isEmpty() && !ocenaStr.isEmpty()) {
                float ocena = Float.parseFloat(ocenaStr);

                // 1. Dodajemo film u listu i osvežavamo adapter
                Movie noviFilm = new Movie(naziv, ocena, odgledano);
                movieList.add(noviFilm);
                adapter.notifyDataSetChanged();

                // 2. Šaljemo Broadcast za Korak 8 (BroadcastReceiver)
                Intent intent = new Intent("DODAT_FILM");
                intent.putExtra("naziv", naziv);
                intent.putExtra("ocena", ocena);
                requireActivity().sendBroadcast(intent);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

}