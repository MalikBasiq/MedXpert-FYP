package medxpert.main.daniyal_medxpert.doctor;

import java.io.Serializable;

public class Model_Notes_Doctor implements Serializable {

    String headingNotes, descriptionNotes;

    public Model_Notes_Doctor(String headingNotes, String descriptionNotes) {
        this.headingNotes = headingNotes;
        this.descriptionNotes = descriptionNotes;
    }

    public Model_Notes_Doctor() {

    }

    public String getHeadingNotes() {
        return headingNotes;
    }

    public void setHeadingNotes(String headingNotes) {
        this.headingNotes = headingNotes;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
    }

    public void setDescriptionNotes(String descriptionNotes) {
        this.descriptionNotes = descriptionNotes;
    }
}
