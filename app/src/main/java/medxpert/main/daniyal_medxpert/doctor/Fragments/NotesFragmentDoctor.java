package medxpert.main.daniyal_medxpert.doctor.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.Adapter_Medicine_Doctor;

import medxpert.main.daniyal_medxpert.doctor.Adapter_Notes_Doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Notes_Doctor;



public class NotesFragmentDoctor extends Fragment {
    RecyclerView recyclerViewnotes;
    List<Model_Notes_Doctor> list;
    Adapter_Notes_Doctor adapter;

    public NotesFragmentDoctor() {
        list = new ArrayList<>();
    }

    public List<Model_Notes_Doctor> getList() {
        return list;
    }

    public void setList(List<Model_Notes_Doctor> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_doctor, container, false);

        recyclerViewnotes = view.findViewById(R.id.recyclerViewNotesDoc);


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButtondocnotes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        // Create an instance of Adapter_Notes_Doctor and set it on recyclerViewnotes
        adapter = new Adapter_Notes_Doctor(list, getContext());
        recyclerViewnotes.setAdapter(adapter);
        recyclerViewnotes.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_notes_doctor, null);
        builder.setView(dialogView);

        final EditText headingNotes = dialogView.findViewById(R.id.HeadingNotesdoc);
        final EditText descriptionNotes = dialogView.findViewById(R.id.descriptionNotesdoc);

        builder.setTitle("Enter Details");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String heading = headingNotes.getText().toString();
                String description = descriptionNotes.getText().toString();

                list.add(new Model_Notes_Doctor(heading, description));
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}