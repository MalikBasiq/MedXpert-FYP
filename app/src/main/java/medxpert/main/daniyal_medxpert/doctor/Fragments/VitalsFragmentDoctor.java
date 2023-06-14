package medxpert.main.daniyal_medxpert.doctor.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Adapter_Vitals_Doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Vitals_Doctor;


public class VitalsFragmentDoctor extends Fragment {
    RecyclerView recyclerViewvitals;
    List<Model_Vitals_Doctor> list;
    Adapter_Vitals_Doctor adapter;




    public VitalsFragmentDoctor(){
        list = new ArrayList<>(); // Initialize the list

    }

    public void setList(List<Model_Vitals_Doctor> list) {
        this.list = list;
    }

    public List<Model_Vitals_Doctor> getList() {
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vitals_doctor, container, false);

        recyclerViewvitals = view.findViewById(R.id.recyclerViewVitalsDoc);


        adapter = new Adapter_Vitals_Doctor(list, getContext());
        recyclerViewvitals.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewvitals.setLayoutManager(linearLayoutManager);


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButtondocvitals);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
        return view;
}



    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_vitals_doctor, null);
        builder.setView(dialogView);

        final EditText nameOfVital = dialogView.findViewById(R.id.nameofvitaldoc);
        final EditText valueOfVital = dialogView.findViewById(R.id.valueofvitaldoc);


        builder.setTitle("Enter Details");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nameVital = nameOfVital.getText().toString();
                String valueVital = valueOfVital.getText().toString();

                list.add(new Model_Vitals_Doctor(nameVital, valueVital));
                adapter.notifyDataSetChanged();


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}