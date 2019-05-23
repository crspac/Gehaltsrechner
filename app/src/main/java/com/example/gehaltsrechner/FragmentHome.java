package com.example.gehaltsrechner;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import java.util.Calendar;

public class FragmentHome extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Fragment_Home";

    private Calendar calendar = Calendar.getInstance();
    private Button btnCalculate;
    static CalculationSteuer steuer;
    FragmentOutput frg = new FragmentOutput();

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


        final Spinner ddBundesland = (Spinner) view.findViewById(R.id.dropdownarbeitsstelle);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Arbeitsstelle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        final Spinner ddRente = (Spinner) view.findViewById(R.id.spinnerRente);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.Rentenversicherung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        final Spinner ddAlosen = (Spinner) view.findViewById(R.id.dropdownarbeitsstelle);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getContext(), R.array.Alosenversicherung, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        final Spinner ddKranken = (Spinner) view.findViewById(R.id.dropdownkranken);


        spinner.setSelection(0);
        ddKlasse.setSelection(0);
        ddBundesland.setSelection(0);
        ddRente.setSelection(0);
        ddAlosen.setSelection(0);



        final EditText geburtstag = view.findViewById(R.id.Geburtstag);
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
        final RadioButton rButtonKircheYes = (RadioButton) view.findViewById(R.id.rBtnKircheJa);
        final EditText lohnsteuer = view.findViewById(R.id.Lohnsteuer);
        final EditText bruttolohn = view.findViewById(R.id.Bruttolohn);
        final TextView kifreibetr = (TextView) view.findViewById(R.id.kinderfreibetrag);
        final EditText kirchensteuer = (EditText) view.findViewById(R.id.kirchensteuer2);
        final EditText rente = view.findViewById(R.id.editTextRentenbeitrag);
        final EditText zusatzversicherung = view.findViewById(R.id.Zusatzbeitrag);
        final EditText rentenzusatz = view.findViewById(R.id.editTextRentenbeitrag);



        rButtonKinderYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    kifreibetr.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);

                }else{
                    kifreibetr.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);

                }
            }
        });


        rButtonKircheYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    kirchensteuer.setVisibility(View.VISIBLE);

                }else{
                    kirchensteuer.setVisibility(View.GONE);
                }
            }
        });


        ddRente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnervalue = parent.getSelectedItem().toString();
                Log.i("spinnervalue", ".." + spinnervalue);

                if (parent.getItemAtPosition(position).equals("Privatversichert")){

                    rente.setVisibility(View.VISIBLE);
                } else {
                    rente.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String temporarystring;
                EditText[] array = {bruttolohn, zusatzversicherung, geburtstag,lohnsteuer};
                double rentenzusatzB = 0;
                double lohnsteuerBetrag = 0;
                double kinderfreiBetrag = 0;
                double bruttolohnBetrag = 0;
                double zusatzBetrag = 0;

                final String rentenversicherung = ddRente.getSelectedItem().toString();
                final String krankenversicherung = ddKranken.getSelectedItem().toString();
                final String bundesland = ddBundesland.getSelectedItem().toString();
                final String arbeitslosenversicherung = ddAlosen.getSelectedItem().toString();

                if(checkFieldEmpty(array)){

                }else{
                    temporarystring = lohnsteuer.getText().toString();
                    lohnsteuerBetrag = Double.parseDouble(temporarystring);
                    temporarystring = spinner.getSelectedItem().toString();
                    kinderfreiBetrag = Double.parseDouble(temporarystring);
                    temporarystring = bruttolohn.getText().toString();
                    bruttolohnBetrag = Double.parseDouble(temporarystring);
                    temporarystring = zusatzversicherung.getText().toString();
                    zusatzBetrag = Double.parseDouble(temporarystring);
                    if(rentenzusatz.getVisibility() == View.VISIBLE){
                        temporarystring = rentenzusatz.getText().toString();
                        rentenzusatzB = Double.parseDouble(temporarystring);
                    }
                    steuer = new CalculationSteuer(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), lohnsteuerBetrag,rButtonKinderYes.isChecked(),
                            kinderfreiBetrag, bundesland, bruttolohnBetrag, rentenversicherung, krankenversicherung, zusatzBetrag, rentenzusatzB ,rButtonKircheYes.isChecked(),arbeitslosenversicherung );
                    steuer.calculateSteuer();
                    frg.updateTextFields();
                    Toast.makeText(getActivity(), Double.toString(steuer.calculateSteuer()), Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).setViewPager(1);

                }


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

    public void showErrorMessage(String title, String msg){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setMessage(msg).setCancelable(false).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public boolean checkFieldEmpty(EditText[] array){
        for(int i = 0; i < array.length; i++){
            if(array[i].getText().toString().matches("")){
                int name = array[i].getId();
                showErrorMessage("Leeres Feld", array[i].getResources().getResourceEntryName(name) +" darf nicht leer sein!");
                return true;
            }
        }
        return false;
    }

}
