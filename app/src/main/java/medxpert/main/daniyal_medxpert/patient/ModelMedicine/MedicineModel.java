package medxpert.main.daniyal_medxpert.patient.ModelMedicine;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MedicineModel implements Serializable {
    String medicineName, morningQuantity, eveningQuantity, nightQuantity, duration, direction;
    private boolean isSelected;

    public MedicineModel(String medicineName, String morningQuantity, String eveningQuantity, String nightQuantity, String duration, String direction) {
        this.medicineName = medicineName;
        this.morningQuantity = morningQuantity;
        this.eveningQuantity = eveningQuantity;
        this.nightQuantity = nightQuantity;
        this.duration = duration;
        this.direction = direction;
    }
    protected MedicineModel(Parcel in) {
        medicineName = in.readString();
        morningQuantity = in.readString();
        eveningQuantity = in.readString();
        nightQuantity = in.readString();
        duration = in.readString();
        direction = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MedicineModel> CREATOR = new Parcelable.Creator<MedicineModel>() {
        @Override
        public MedicineModel createFromParcel(Parcel in) {
            return new MedicineModel(in);
        }

        @Override
        public MedicineModel[] newArray(int size) {
            return new MedicineModel[size];
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


//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(medicineName);
//        dest.writeString(morningQuantity);
//        dest.writeString(eveningQuantity);
//        dest.writeString(nightQuantity);
//        dest.writeString(duration);
//        dest.writeString(direction);
//        dest.writeByte((byte) (isSelected ? 1 : 0));
//    }


    public int describeContents() {
        return 0;
    }
}
