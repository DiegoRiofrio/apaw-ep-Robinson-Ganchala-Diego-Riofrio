package es.upm.miw.apaw_contest.documents;

public class Jury {

    private Integer members;

    private String veredict;

    private String type;

    public Jury(Integer members, String veredict, String type) {
        this.members = members;
        this.veredict = veredict;
        this.type = type;
    }

    public Jury() {
        //For framework
    }

    public String getVeredict() {
        return veredict;
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
