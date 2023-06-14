package medxpert.main.daniyal_medxpert.doctor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import medxpert.main.daniyal_medxpert.doctor.Model_Notes_Doctor;


import medxpert.main.daniyal_medxpert.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Notes extends RecyclerView.Adapter<Adapter_Notes.ViewHolder> {
    private List<Model_Notes_Doctor> notesList;
    private Context context;

    public Adapter_Notes(List<Model_Notes_Doctor> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model_Notes_Doctor note = notesList.get(position);

        holder.heading.setText(note.getHeadingNotes());
        holder.descriptionforNotes.setText(note.getDescriptionNotes());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading, descriptionforNotes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.HeadingNotes);
            descriptionforNotes = itemView.findViewById(R.id.descriptionNotes);
        }
    }
}