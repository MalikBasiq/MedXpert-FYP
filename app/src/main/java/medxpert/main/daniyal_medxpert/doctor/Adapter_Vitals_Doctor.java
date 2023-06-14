package medxpert.main.daniyal_medxpert.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;

public class Adapter_Vitals_Doctor extends RecyclerView.Adapter<Adapter_Vitals_Doctor.viewHolder>{
    List<Model_Vitals_Doctor> list;
    Context context;

    public Adapter_Vitals_Doctor(List<Model_Vitals_Doctor> list, Context context) {
        this.list = list;
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public Adapter_Vitals_Doctor.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vitals_doctor_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Vitals_Doctor.viewHolder holder, int position) {
        Model_Vitals_Doctor model_vitals_doctor = list.get(position);

        holder.NameVitals.setText(model_vitals_doctor.getNameOfvitals());
        holder.ValueVitals.setText(model_vitals_doctor.getValueOfVitals());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView NameVitals, ValueVitals;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            NameVitals = itemView.findViewById(R.id.nameofvitaldoc);
            ValueVitals = itemView.findViewById(R.id.valueofvitaldoc);
        }
    }
}

