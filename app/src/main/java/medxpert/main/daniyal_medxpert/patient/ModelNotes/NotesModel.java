package medxpert.main.daniyal_medxpert.patient.ModelNotes;

public class NotesModel {
    String heading, descriptionNotes;

    public NotesModel(String heading, String descriptionNotes) {
        this.heading = heading;
        this.descriptionNotes = descriptionNotes;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescriptionNotes() {
        return descriptionNotes;
    }

    public void setDescriptionNotes(String descriptionNotes) {
        this.descriptionNotes = descriptionNotes;
    }
}
