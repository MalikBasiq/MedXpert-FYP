package medxpert.main.daniyal_medxpert.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.patient.ModelMedicine.MedicineModel;
import medxpert.main.daniyal_medxpert.patient.Adapters.Adapter_SendPharmacy;

public class SendPharmacy extends AppCompatActivity
{

    LinearLayoutManager linearLayout;
    RecyclerView SendPharmacyRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_send_pharmacy);

        Intent intent = getIntent();

        ArrayList<MedicineModel_doctor> SelectedItems = (ArrayList<MedicineModel_doctor>) intent.getSerializableExtra("SelectedItems");

        SendPharmacyRecycler=findViewById(R.id.recyclersendpharmacy);


        Adapter_SendPharmacy sendpharmacyadapterobj = new Adapter_SendPharmacy(this, SelectedItems ); //passing array list "routine_Intake_List" inside it

        SendPharmacyRecycler.setAdapter(sendpharmacyadapterobj);
        linearLayout = new LinearLayoutManager(this);
        SendPharmacyRecycler.setLayoutManager(linearLayout);



    }
}