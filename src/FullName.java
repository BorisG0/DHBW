import java.util.Objects;

public class FullName implements Comparable<FullName>{
    private String name;
    private String familyName;

    public FullName(String name, String familyName) {
        this.name = name;
        this.familyName = familyName;
    }

    public String toString(){
        return familyName + ", " + name;
    }

    @Override
    public int compareTo(FullName o) {

        if(!familyName.equals(o.familyName)){
            return familyName.compareTo(o.familyName);
        }

        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;

        FullName nameToCompare = (FullName) o;

        if((!familyName.equals(nameToCompare.familyName)) || (!name.equals(nameToCompare.name))) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, familyName);
    }
}
