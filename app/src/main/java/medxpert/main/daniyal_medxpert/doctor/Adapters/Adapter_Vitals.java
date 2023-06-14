package medxpert.main.daniyal_medxpert.doctor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import medxpert.main.daniyal_medxpert.doctor.Model_Vitals_Doctor;

import medxpert.main.daniyal_medxpert.R;


import java.util.ArrayList;
import java.util.List;

public class Adapter_Vitals extends RecyclerView.Adapter<Adapter_Vitals.ViewHolder> {
    private List<Model_Vitals_Doctor> vitalsList;
    private Context context;

    public Adapter_Vitals(List<Model_Vitals_Doctor> vitalsList, Context context) {
        this.vitalsList = vitalsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vitals_item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model_Vitals_Doctor vitals = vitalsList.get(position);

        holder.nameOfVitals.setText(vitals.getNameOfvitals());
        holder.valueOfVitals.setText(vitals.getValueOfVitals());

    }

    @Override
    public int getItemCount() {
        return vitalsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfVitals, valueOfVitals;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfVitals = itemView.findViewById(R.id.nameofvital);
            valueOfVitals = itemView.findViewById(R.id.valueofvital);
        }
    }
}