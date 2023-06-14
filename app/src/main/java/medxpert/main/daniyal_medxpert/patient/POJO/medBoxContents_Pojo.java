package medxpert.main.daniyal_medxpert.patient.POJO;

import java.io.Serializable;

public class medBoxContents_Pojo implements Serializable {

    private String medicinename;
    private int quantity;

    public medBoxContents_Pojo(){

    }
    public medBoxContents_Pojo(String Medicinename, int Quantity)
    {
        this.medicinename=Medicinename;
        this.quantity=Quantity;

    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    public String getMedicinename() {
        return medicinename;
    }


}
