package medxpert.main.daniyal_medxpert.doctor;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import medxpert.main.daniyal_medxpert.patient.ModelMedicine.MedicineModel;

public class MedicineModel_doctor implements Serializable {
    String medicineName, morningQuantity, eveningQuantity, nightQuantity, duration, direction;
    private boolean isSelected;

    public MedicineModel_doctor(String medicineName, String morningQuantity, String eveningQuantity, String nightQuantity, String duration, String direction) {
        this.medicineName = medicineName;
        this.morningQuantity = morningQuantity;
        this.eveningQuantity = eveningQuantity;
        this.nightQuantity = nightQuantity;
        this.duration = duration;
        this.direction = direction;
    }

    public MedicineModel_doctor() {

    }

    protected MedicineModel_doctor(Parcel in) {
        medicineName = in.readString();
        morningQuantity = in.readString();
        eveningQuantity = in.readString();
        nightQuantity = in.readString();
        duration = in.readString();
        direction = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MedicineModel_doctor> CREATOR = new Parcelable.Creator<MedicineModel_doctor>() {
        @Override
        public MedicineModel_doctor createFromParcel(Parcel in) {
            return new MedicineModel_doctor(in);
        }

        @Override
        public MedicineModel_doctor[] newArray(int size) {
            return new MedicineModel_doctor[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMorningQuantity() {
        return morningQuantity;
    }

    public void setMorningQuantity(String morningQuantity) {
        this.morningQuantity = morningQuantity;
    }

    public String getEveningQuantity() {
        return eveningQuantity;
    }

    public void setEveningQuantity(String eveningQuantity) {
        this.eveningQuantity = eveningQuantity;
    }

    public String getNightQuantity() {
        return nightQuantity;
    }

    public void setNightQuantity(String nightQuantity) {
        this.nightQuantity = nightQuantity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int describeContents() {
        return 0;
    }

}