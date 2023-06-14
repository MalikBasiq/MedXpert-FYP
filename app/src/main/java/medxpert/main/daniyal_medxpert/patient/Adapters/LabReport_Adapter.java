package medxpert.main.daniyal_medxpert.patient.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import medxpert.main.daniyal_medxpert.R;
import medxpert.main.daniyal_medxpert.patient.POJO.Report;

public class LabReport_Adapter extends RecyclerView.Adapter<LabReport_Adapter.ReportViewHolder> {
    private List<Report> reportList;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public LabReport_Adapter(List<Report> reportList,Context context) {
        this.reportList = reportList;
        this.context=context;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ReportViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report = reportList.get(position );
        holder.nameEditText.setText(report.getName());
        holder.dateEditText.setText(report.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.lab_image_dialog_layout, null);

                ImageView imageView=dialogView.findViewById(R.id.imageViewLabReport);

                // Set the bitmap for the ImageView
                String encodedBitmap =report.getImage(); // Your encoded Bitmap string
                byte[] byteArray = Base64.decode(encodedBitmap, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


                imageView.setImageBitmap(bitmap);

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        private TextView nameEditText;
        private TextView dateEditText;


        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            nameEditText = itemView.findViewById(R.id.card_NameEditText);
            dateEditText = itemView.findViewById(R.id.card_DateEditText);

        }


    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
