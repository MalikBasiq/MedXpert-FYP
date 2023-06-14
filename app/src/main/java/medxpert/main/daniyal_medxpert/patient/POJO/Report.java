package medxpert.main.daniyal_medxpert.patient.POJO;

import android.graphics.Bitmap;

public class Report {
    private String name;
    private String date;
    private String image;
    private String patientCNIC;

    public String getPatientCNIC() {
        return patientCNIC;
    }

    public void setPatientCNIC(String patientCNIC) {
        this.patientCNIC = patientCNIC;
    }

    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", image=" + image +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
