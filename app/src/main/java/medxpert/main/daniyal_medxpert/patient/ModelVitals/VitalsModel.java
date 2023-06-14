package medxpert.main.daniyal_medxpert.patient.ModelVitals;

public class VitalsModel {
    String nameOfVitals, valueOfVitals;

    public VitalsModel(String nameOfVitals, String valueOfVitals) {
        this.nameOfVitals = nameOfVitals;
        this.valueOfVitals = valueOfVitals;
    }

    public String getNameOfVitals() {
        return nameOfVitals;
    }

    public void setNameOfVitals(String nameOfVitals) {
        this.nameOfVitals = nameOfVitals;
    }

    public String getValueOfVitals() {
        return valueOfVitals;
    }

    public void setValueOfVitals(String valueOfVitals) {
        this.valueOfVitals = valueOfVitals;
    }
}
