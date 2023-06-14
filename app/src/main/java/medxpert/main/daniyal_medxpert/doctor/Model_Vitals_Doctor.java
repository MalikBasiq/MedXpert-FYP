package medxpert.main.daniyal_medxpert.doctor;

import java.io.Serializable;

public class Model_Vitals_Doctor implements Serializable {
    String nameOfvitals, valueOfVitals;

    public Model_Vitals_Doctor(String nameOfvitals, String valueOfVitals) {
        this.nameOfvitals = nameOfvitals;
        this.valueOfVitals = valueOfVitals;
    }

    public Model_Vitals_Doctor() {

    }

    public String getNameOfvitals() {
        return nameOfvitals;
    }

    public void setNameOfvitals(String nameOfvitals) {
        this.nameOfvitals = nameOfvitals;
    }

    public String getValueOfVitals() {
        return valueOfVitals;
    }

    public void setValueOfVitals(String valueOfVitals) {
        this.valueOfVitals = valueOfVitals;
    }
}
