package medxpert.main.daniyal_medxpert.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import medxpert.main.daniyal_medxpert.R;


public class Adapter_Medicine_Doctor extends RecyclerView.Adapter<Adapter_Medicine_Doctor.viewHolder>{
    List<MedicineModel_doctor> list;
    Context context;

    public Adapter_Medicine_Doctor(List<MedicineModel_doctor> list, Context context) {
        this.list = list;
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public Adapter_Medicine_Doctor.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicine_doctor_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Medicine_Doctor.viewHolder holder, int position) {
        MedicineModel_doctor medicineModel = list.get(position);

        holder.medicineName.setText(medicineModel.getMedicineName());
        holder.morningQuantity.setText(medicineModel.getMorningQuantity());
        holder.eveningQuantity.setText(medicineModel.getEveningQuantity());
        holder.nightQuantity.setText(medicineModel.getNightQuantity());
        holder.duration.setText(medicineModel.getDuration());
        holder.direction.setText(medicineModel.getDirection());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView medicineName, morningQuantity, eveningQuantity, nightQuantity, duration, direction;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.Med);
            morningQuantity = itemView.findViewById(R.id.quantitymorningdocMedicineLayout);
            eveningQuantity = itemView.findViewById(R.id.quantityeveningdocMedicineLayout);
            nightQuantity = itemView.findViewById(R.id.quantitynightdocMedicineLayout);
            duration = itemView.findViewById(R.id.durationdocMedicineLayout);
            direction = itemView.findViewById(R.id.directiondocMedicineLayout);
        }
    }

}
