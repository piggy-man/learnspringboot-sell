package learn.demo.objectdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonInfo {
    @Id
    @GeneratedValue
    private Integer personId;

    private String personName;

    private Integer personSex;

    public PersonInfo() {
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getPersonSex() {
        return personSex;
    }

    public void setPersonSex(Integer personSex) {
        this.personSex = personSex;
    }
}
