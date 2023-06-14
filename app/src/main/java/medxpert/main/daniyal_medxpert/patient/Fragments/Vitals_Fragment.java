package medxpert.main.daniyal_medxpert.patient.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Vitals_Doctor;
import medxpert.main.daniyal_medxpert.patient.Adapters.Adapter_Vitals;
import medxpert.main.daniyal_medxpert.patient.ModelVitals.VitalsModel;
import medxpert.main.daniyal_medxpert.R;

import java.util.ArrayList;
import java.util.List;


public class Vitals_Fragment extends Fragment {

    RecyclerView recyclerView;

    List<Model_Vitals_Doctor> list;

    public Vitals_Fragment(List<Model_Vitals_Doctor> incomingList){
        list=incomingList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vitals, container, false);

        recyclerView = view.findViewById(R.id.recyclerviewVitals);


        Adapter_Vitals adapter = new Adapter_Vitals(list, getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}