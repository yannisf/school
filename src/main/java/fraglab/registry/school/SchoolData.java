package fraglab.registry.school;

import java.io.Serializable;

public class SchoolData implements Serializable {

    private String id;
    private String school;
    private String classroom;
    private String term;

    public SchoolData() {
    }

    public SchoolData(String id, String school, String classroom, String term) {
        this.id = id;
        this.school = school;
        this.classroom = classroom;
        this.term = term;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
