package medxpert.main.daniyal_medxpert.patient.RecyclerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.POJO.medBoxContents_Pojo;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.viewHolder>{
    private Context context;

    private List<medBoxContents_Pojo> Medicinelist;

    private View.OnClickListener addClickListener;
    private View.OnClickListener minusClickListener;
    private View.OnClickListener deleteClickListener;

    public MedicineAdapter(Context context, List<medBoxContents_Pojo> medlist)
    {
        this.context=context;
        this.Medicinelist =medlist;

    }


    @NonNull
    @Override
    public MedicineAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rountine_intake_row,parent,false);
        return new viewHolder(view);
    }

    @Override
public void onBindViewHolder(@NonNull MedicineAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        medBoxContents_Pojo obj = Medicinelist.get(position);
    holder.MedicineName.setText(obj.getMedicinename());
    holder.Quantity.setText(String.valueOf(obj.getQuantity()));

//     TODO handling click events a success

        // Set click listeners
        holder.AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add button click
                // Update quantity and notify adapter
                int newQuantity = obj.getQuantity() + 1;
                obj.setQuantity(newQuantity);
                notifyItemChanged(position);
            }
        });

        holder.MinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle minus button click
                // Update quantity and notify adapter
                int newQuantity = obj.getQuantity() - 1;
                if (newQuantity >= 0) {
                    obj.setQuantity(newQuantity);
                    notifyItemChanged(position);
                }
            }
        });

        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                // Remove item from the list and notify adapter
                Medicinelist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, Medicinelist.size());
            }
        });
}

    @Override
    public int getItemCount() {
        return Medicinelist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView MedicineName, Quantity;
        ImageView AddButton,MinusButton,DeleteButton;
        public viewHolder(@NonNull View itemView)
        {
            super(itemView);
            MedicineName = itemView.findViewById(R.id.medName);
            Quantity = itemView.findViewById(R.id.med_quantity);
            AddButton =itemView.findViewById(R.id.incQnty);
            MinusButton =itemView.findViewById(R.id.decQnty);
            DeleteButton =itemView.findViewById(R.id.delItem);
            //Number = itemView.findViewById(R.id.Number);
        }
    }
}
