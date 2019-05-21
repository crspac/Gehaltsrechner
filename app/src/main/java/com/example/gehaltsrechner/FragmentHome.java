package com.example.gehaltsrechner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FragmentHome extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "Fragment_Home";

    private Button btnCalculate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //btnCalculate = (Button) view.findViewById(R.id.btn_Calculate);

    //    btnCalculate.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //
    //        public void onClick(View v) {
    //           Toast.makeText(getActivity(), "FragmentHome" , Toast.LENGTH_SHORT).show();
    //       }
    //   });

        Spinner spinner = (Spinner) view.findViewById(R.id.dropdownfreibetrag);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.Kinderfreibetrag, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 = (Spinner) view.findViewById(R.id.dropdownsklasse);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.Steuerklasse, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) view.findViewById(R.id.dropdownarbeitsstelle);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Arbeitsstelle, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        RadioButton rButton = (RadioButton) view.findViewById(R.id.rBtnKinderYes);
        RadioButton rButton1 = (RadioButton) view.findViewById(R.id.rBtnKircheJa);
        RadioButton rButton2 = (RadioButton) view.findViewById(R.id.rBtnRenteJa);
        


        rButton.isChecked()



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
