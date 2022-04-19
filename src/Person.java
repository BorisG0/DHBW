import java.io.Serializable;

public class Person implements Serializable {

    static final long serialVersionUID = 1L;

    private String name;
    private String familyName;
    private String fullName;

    public Person(String name, String familyName){
        this.name = name;
        this.familyName  = familyName;
        setFullName();
    }

    private void setFullName(){
        this.fullName = this.familyName + ", " + this.name;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFullName() {
        return fullName;
    }
}
