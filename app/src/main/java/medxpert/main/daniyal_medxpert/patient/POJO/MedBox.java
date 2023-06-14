package medxpert.main.daniyal_medxpert.patient.POJO;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedBox implements Serializable {

    private String name;

    public String medboxID="";

    public String CNIC="";

    public List<medBoxContents_Pojo> medBoxList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToList(medBoxContents_Pojo p){

//        if (medBoxList == null) {
//            medBoxList = new ArrayList<>();
//        }
        medBoxList.add(p);


    }

    public void removeToList(medBoxContents_Pojo p){
        medBoxList.remove(p);
    }


    //todo if i comment this function the field of list is not getting added to firebase
//    public int getListSize(){
//        return this.medBoxList.size();
//    }

    public List<medBoxContents_Pojo> getMedBoxList() {
        return this.medBoxList;
    }

    public void setMedBoxList(List<medBoxContents_Pojo> medBoxList) {
        this.medBoxList = medBoxList;
    }
}
