package medxpert.main.daniyal_medxpert.doctor.POJO;

import java.util.List;

import medxpert.main.daniyal_medxpert.doctor.MedicineModel_doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Notes_Doctor;
import medxpert.main.daniyal_medxpert.doctor.Model_Vitals_Doctor;

public class Prescription_Model {
    private String patientCNIC;
    private String doctorCNIC;
    private String date;
    private String doctorName;
    private String designation;
    private List<MedicineModel_doctor> Medicines;
    private List<Model_Vitals_Doctor> Vitals;
    private List<Model_Notes_Doctor> Notes;

    // Getter and setter methods


    public Prescription_Model(String patientCNIC, String doctorCNIC, String date, String doctorName, String designation, List<MedicineModel_doctor> medicines, List<Model_Vitals_Doctor> vitals, List<Model_Notes_Doctor> notes) {
        this.patientCNIC = patientCNIC;
        this.doctorCNIC = doctorCNIC;
        this.date = date;
        this.doctorName = doctorName;
        this.designation = designation;
        Medicines = medicines;
        Vitals = vitals;
        Notes = notes;
    }

    public Prescription_Model(){

    }

    public String getPatientCNIC() {
        return patientCNIC;
    }

    public void setPatientCNIC(String patientCNIC) {
        this.patientCNIC = patientCNIC;
    }

    public String getDoctorCNIC() {
        return doctorCNIC;
    }

    public void setDoctorCNIC(String doctorCNIC) {
        this.doctorCNIC = doctorCNIC;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<MedicineModel_doctor> getMedicines() {
        return Medicines;
    }

    public void setMedicines(List<MedicineModel_doctor> medicines) {
        Medicines = medicines;
    }

    public List<Model_Vitals_Doctor> getVitals() {
        return Vitals;
    }

    public void setVitals(List<Model_Vitals_Doctor> vitals) {
        Vitals = vitals;
    }

    public List<Model_Notes_Doctor> getNotes() {
        return Notes;
    }

    public void setNotes(List<Model_Notes_Doctor> notes) {
        Notes = notes;
    }
}