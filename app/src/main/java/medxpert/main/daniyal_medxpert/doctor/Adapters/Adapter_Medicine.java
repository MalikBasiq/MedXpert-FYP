package medxpert.main.daniyal_medxpert.doctor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import medxpert.main.daniyal_medxpert.R;
import java.util.ArrayList;
import java.util.List;

public class Adapter_Medicine extends RecyclerView.Adapter<Adapter_Medicine.viewHolder>{
    List<MedicineModel_doctor> list;
    Context context;
    ArrayList<MedicineModel_doctor> selectedItems;  // ArrayList to store selected items


    public Adapter_Medicine(List<MedicineModel_doctor> list, Context context) {
        this.list = list;
        this.context = context;
        selectedItems = new ArrayList<MedicineModel_doctor>();  // Initialize the selectedItems ArrayList
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.medicine_layout_doctor, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
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
    public ArrayList<MedicineModel_doctor> getSelectedItems() {
        return selectedItems;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView medicineName, morningQuantity, eveningQuantity, nightQuantity, duration, direction;
        CheckBox checkBox;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.nameofmedicine);
            morningQuantity = itemView.findViewById(R.id.quantitymorning);
            eveningQuantity = itemView.findViewById(R.id.quantityevening);
            nightQuantity = itemView.findViewById(R.id.quantitynight);
            duration = itemView.findViewById(R.id.duration);
            direction = itemView.findViewById(R.id.direction);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

}
