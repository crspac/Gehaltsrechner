package com.example.gehaltsrechner;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class FragmentHome extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Fragment_Home";

    private Calendar calendar = Calendar.getInstance();
    private Button btnCalculate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnCalculate = (Button) view.findViewById(R.id.btnCalculate);

        final Spinner spinner = (Spinner) view.findViewById(R.id.dropdownfreibetrag);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Kinderfreibetrag, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        Spinner ddKlasse = (Spinner) view.findViewById(R.id.dropdownsklasse);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.Steuerklasse, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(this);


        Spinner ddBundesland = (Spinner) view.findViewById(R.id.dropdownarbeitsstelle);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Arbeitsstelle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        Spinner ddRente = (Spinner) view.findViewById(R.id.spinnerRente);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.Rentenversicherung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        spinner.setSelection(0);
        ddKlasse.setSelection(0);
        ddBundesland.setSelection(0);
        ddRente.setSelection(0);



        final EditText geburtstag = view.findViewById(R.id.geburtstag);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd.MM.YYYY"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                geburtstag.setText(sdf.format(calendar.getTime()));
            }
        };
        geburtstag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final RadioButton rButtonKinderYes = view.findViewById(R.id.rBtnKinderYes);
        RadioButton rButtonKircheYes = (RadioButton) view.findViewById(R.id.rBtnKircheJa);
        final EditText lohnsteuer = view.findViewById(R.id.Lohnsteuer);
        final EditText bruttolohn = view.findViewById(R.id.bruttolohn);
        final TextView KifreiBetr = (TextView) view.findViewById(R.id.kinderfreibetrag);
        final EditText Kirchensteuer1 = (EditText) view.findViewById(R.id.kirchensteuer2);
        KifreiBetr.setVisibility(View.INVISIBLE);
        final EditText Rente = view.findViewById(R.id.editTextRentenbeitrag);



        rButtonKinderYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   // KifreiBetr.setEnabled(true);
                 //  KifreiBetr.setVisibility(View.VISIBLE);
                   //spinner.setVisibility(View.VISIBLE);
                    KifreiBetr.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);

                }else{
                  // KifreiBetr.setVisibility(View.INVISIBLE);
                  // spinner.setVisibility(View.INVISIBLE);
                    KifreiBetr.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);

                }
            }
        });


        rButtonKircheYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Kirchensteuer1.setVisibility(View.VISIBLE);

                }else{
                    Kirchensteuer1.setVisibility(View.GONE);
                }
            }
        });


        ddRente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnervalue = parent.getSelectedItem().toString();
                Log.i("spinnervalue", ".." + spinnervalue);

                if (parent.getItemAtPosition(position).equals("Privatversichert")){

                    Rente.setVisibility(View.VISIBLE);
                } else {
                    Rente.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //String dateTime = geburtstag.getText().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD.MM.YYYY");
        //final LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);
        //final double lohnsteuerBetrag = Double.parseDouble(lohnsteuer.getText().toString());
        //final double kinderfreiBetrag = Double.parseDouble(spinner.getSelectedItem().toString());
        //final String bundesland = ddBundesland.getSelectedItem().toString();
        //final double bruttolohnBetrag = Double.parseDouble(bruttolohn.getText().toString());
        //final int rentenversicherungs;

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(getActivity(), "FragmentHome" , Toast.LENGTH_SHORT).show();
                //CalculationSteuer steuer = new CalculationSteuer(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), lohnsteuerBetrag,rButtonKinderYes.isChecked(), kinderfreiBetrag, bundesland, bruttolohnBetrag);

            }
        });

        return view;
    }




    public Button getBtnCalculate() {
        return btnCalculate;
    }

    public void setBtnCalculate(Button btnCalculate) {
        this.btnCalculate = btnCalculate;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
