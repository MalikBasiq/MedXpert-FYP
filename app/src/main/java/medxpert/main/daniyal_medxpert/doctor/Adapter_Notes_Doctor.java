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

public class Adapter_Notes_Doctor extends RecyclerView.Adapter<Adapter_Notes_Doctor.viewHolder> {
    List<Model_Notes_Doctor> list;
    Context context;

    public Adapter_Notes_Doctor(List<Model_Notes_Doctor> list, Context context) {
        this.list = list;
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public Adapter_Notes_Doctor.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_doctor_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Notes_Doctor.viewHolder holder, int position) {
        Model_Notes_Doctor model_notes_doctor = list.get(position);

        holder.headingNotes.setText(model_notes_doctor.getHeadingNotes());
        holder.descriptionNotes.setText(model_notes_doctor.getDescriptionNotes());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView headingNotes, descriptionNotes;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            headingNotes = itemView.findViewById(R.id.HeadingNotesdoc);
            descriptionNotes = itemView.findViewById(R.id.descriptionNotesdoc);
        }
    }
}
