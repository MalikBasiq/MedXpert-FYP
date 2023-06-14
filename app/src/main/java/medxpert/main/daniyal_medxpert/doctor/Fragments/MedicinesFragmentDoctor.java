package medxpert.main.daniyal_medxpert.doctor.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Adapter_Medicine_Doctor;
import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;

public class MedicinesFragmentDoctor extends Fragment {
    RecyclerView recyclerView;
    List<MedicineModel_doctor> list; // Define list as a member variable
    Adapter_Medicine_Doctor adapter;



    public MedicinesFragmentDoctor(){
        list = new ArrayList<>();
    }

    public void setList(List<MedicineModel_doctor> list) {
        this.list = list;
    }

    public List<MedicineModel_doctor> getList() {
        return list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicines_doctor, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNotesDoc);


        adapter = new Adapter_Medicine_Doctor(list, getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButtondocmedicines);
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
        View dialogView = inflater.inflate(R.layout.dialog_medicine_doctor, null);
        builder.setView(dialogView);

        final EditText nameOfMedicine = dialogView.findViewById(R.id.Med);
        final EditText quantityMorning = dialogView.findViewById(R.id.quantitymorningdocMedicineLayout);
        final EditText quantityEvening = dialogView.findViewById(R.id.quantityeveningdocMedicineLayout);
        final EditText quantityNight = dialogView.findViewById(R.id.quantitynightdocMedicineLayout);
        final EditText duration = dialogView.findViewById(R.id.durationdocMedicineLayout);
        final EditText direction = dialogView.findViewById(R.id.directiondocMedicineLayout);

        builder.setTitle("Enter Details");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nameMedicine = nameOfMedicine.getText().toString();
                String qtyMorning = quantityMorning.getText().toString();
                String qtyEvening = quantityEvening.getText().toString();
                String qtyNight = quantityNight.getText().toString();
                String durationValue = duration.getText().toString();
                String directionValue = direction.getText().toString();

                list.add(new MedicineModel_doctor(nameMedicine, qtyMorning, qtyEvening, qtyNight, durationValue, directionValue));
                Toast.makeText(getContext(), String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
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
