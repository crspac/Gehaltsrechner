package com.example.gehaltsrechner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FragmentOutput extends Fragment {


    boolean mUserVisibleHint = true;
    static TextView Brutto;
    static TextView Lohnsteuer;
    static TextView Kirchensteuer;
    static TextView Soli;
    static TextView Rente;
    static TextView Kranken;
    static TextView Alosen;
    static TextView Pflege;
    static TextView Netto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gehaltsrechner_output, container,false);
        setUserVisibleHint(false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Brutto = view.findViewById(R.id.textviewBrutto);
        Lohnsteuer = view.findViewById(R.id.textviewLst);
        Kirchensteuer = view.findViewById(R.id.textviewkirche);
        Soli = view.findViewById(R.id.textviewSoli);
        Rente = view.findViewById(R.id.textviewRente);
        Kranken = view.findViewById(R.id.textviewkranken);
        Alosen = view.findViewById(R.id.textviewAlosen2);
        Pflege = view.findViewById(R.id.textviewPflege);
        Netto = view.findViewById(R.id.textviewNetto);


    }

    public static void updateTextFields(){
        DecimalFormat numberFormat = new DecimalFormat("0.00");
        Brutto.setText(Double.toString(FragmentHome.steuer.getBruttolohn()));
        Lohnsteuer.setText(numberFormat.format(FragmentHome.steuer.getLohnsteuerbetrag()));
        Kirchensteuer.setText(numberFormat.format(FragmentHome.steuer.getKirchensteuerBetrag()));
        Soli.setText(numberFormat.format(FragmentHome.steuer.getSoliBetrag()));
        Rente.setText(numberFormat.format(FragmentHome.steuer.getRentenversicherungsBetrag()));
        Kranken.setText(numberFormat.format(FragmentHome.steuer.getKrankenversicherungsBetrag()));
        Alosen.setText(numberFormat.format(FragmentHome.steuer.getArbeitslosenversicherungsBetrag()));
        Pflege.setText(numberFormat.format(FragmentHome.steuer.getPflegeversicherungsBetrag()));
        Netto.setText(numberFormat.format(FragmentHome.steuer.getEndBetrag()));
    }

    public void onBackPressed()
    {
        ((MainActivity)getActivity()).setViewPager(2);
    }
}
