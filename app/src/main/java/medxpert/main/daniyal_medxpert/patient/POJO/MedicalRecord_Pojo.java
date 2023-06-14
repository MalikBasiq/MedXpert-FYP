package medxpert.main.daniyal_medxpert.patient.POJO;

public class MedicalRecord_Pojo {
    String name;
    String date;
    String specialization;

    public MedicalRecord_Pojo(String name, String date, String specialization){
        this.name=name;
        this.date=date;
        this.specialization=specialization;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
