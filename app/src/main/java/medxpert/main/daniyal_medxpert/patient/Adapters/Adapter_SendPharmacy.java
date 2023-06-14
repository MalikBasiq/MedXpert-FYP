package medxpert.main.daniyal_medxpert.patient.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.patient.ModelMedicine.MedicineModel;
import medxpert.main.daniyal_medxpert.R;

public class Adapter_SendPharmacy extends RecyclerView.Adapter<Adapter_SendPharmacy.viewHolder>{


    private Context context;

    private List<MedicineModel_doctor> arrayList;

    public Adapter_SendPharmacy(Context context, List<MedicineModel_doctor> medlist)
    {
        this.context=context;
        this.arrayList=medlist;
    }
    @NonNull
    @Override
    public Adapter_SendPharmacy.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.send_pharmacy_item_design,parent,false);
        return new Adapter_SendPharmacy.viewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_SendPharmacy.viewHolder holder, int position) {

        MedicineModel_doctor medicineModel = arrayList.get(position);

        holder.medicineName.setText(medicineModel.getMedicineName());

        int morningQuantity = Integer.parseInt(medicineModel.getMorningQuantity());
        int afternoonQuantity = Integer.parseInt(medicineModel.getNightQuantity());
        int eveningQuantity = Integer.parseInt(medicineModel.getEveningQuantity());
        int duration = Integer.parseInt(medicineModel.getDuration().replaceAll("\\D+", ""));

        int totalQuantity = (morningQuantity + afternoonQuantity + eveningQuantity) * duration;

        holder.Quantity.setText(String.valueOf(totalQuantity));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView medicineName, Quantity;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.nameofmedicine);
            Quantity=itemView.findViewById(R.id.qty);
//            morningQuantity = itemView.findViewById(R.id.quantitymorning);
//            eveningQuantity = itemView.findViewById(R.id.quantityevening);
//            nightQuantity = itemView.findViewById(R.id.quantitynight);
//            duration = itemView.findViewById(R.id.duration);
//            direction = itemView.findViewById(R.id.direction);

        }
    }
}
