package medxpert.main.daniyal_medxpert.patient.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.POJO.Prescription_Model;
import medxpert.main.daniyal_medxpert.patient.medicalrecordLayout2;

public class Adapter_medicalrecordlayout1 extends RecyclerView.Adapter<Adapter_medicalrecordlayout1.viewHolder> {

    List<Prescription_Model> list;
    Context context;

    public Adapter_medicalrecordlayout1(List<Prescription_Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicalrecordlayout1, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Prescription_Model model = list.get(position);

//        holder.description.setText(model.getDescription());
        holder.name.setText(model.getDoctorName());
        holder.designation.setText(model.getDesignation());
        holder.date.setText(model.getDate());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, medicalrecordLayout2.class);
                intent.putExtra("Name", model.getDoctorName());
                intent.putExtra("Designation", model.getDesignation());
                intent.putExtra("Date", model.getDate());
                intent.putExtra("Medicines", (Serializable) model.getMedicines());
                intent.putExtra("Vitals", (Serializable) model.getVitals());
                intent.putExtra("Notes", (Serializable) model.getNotes());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
         TextView description, name, designation, date;
        Button btn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
//            description = itemView.findViewById(R.id.description);
            name = itemView.findViewById(R.id.doctorName_prescription);
            designation = itemView.findViewById(R.id.doctorSpecialization_prescription);
            date = itemView.findViewById(R.id.date_prescription);
            btn = itemView.findViewById(R.id.button); // Update the ID here to match your layout
        }
    }
}