package medxpert.main.daniyal_medxpert.patient;

public class medicalrecordlayout1model {
    String Date, description, name, designation;

    public medicalrecordlayout1model(String date, String description, String name, String designation) {
        Date = date;
        this.description = description;
        this.name = name;
        this.designation = designation;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}
