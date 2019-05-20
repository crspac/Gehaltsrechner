package com.example.gehaltsrechner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentHome extends Fragment {
    private static final String TAG = "Fragment_Home";

    private Button btnCalculate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //btnCalculate = (Button) view.findViewById(R.id.btn_Calculate);

//        btnCalculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "FragmentHome" , Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    public Button getBtnCalculate() {
        return btnCalculate;
    }

    public void setBtnCalculate(Button btnCalculate) {
        this.btnCalculate = btnCalculate;
    }
}
