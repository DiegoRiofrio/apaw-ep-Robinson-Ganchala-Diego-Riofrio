package es.upm.miw.apaw_contest.documents;

public class Jury {

    private Integer members;

    private String veredict;

    private String type;

    public Jury() {
        //For framework
    }

    public Jury(Integer members, String type) {
        this.members = members;
        this.type = type;
    }

    public Jury(Integer members, String veredict, String type) {
        this.members = members;
        this.veredict = veredict;
        this.type = type;
    }

    public String getVeredict() {
        return veredict;
    }


    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public void setVeredict(String veredict) {
        this.veredict = veredict;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Jury{" +
                "members=" + members +
                ", veredict='" + veredict + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
