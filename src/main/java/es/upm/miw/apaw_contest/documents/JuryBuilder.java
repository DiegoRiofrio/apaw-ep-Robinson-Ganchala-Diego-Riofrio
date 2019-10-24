package es.upm.miw.apaw_contest.documents;

public class JuryBuilder {

    private static int reference = 1;

    private Jury jury;

    public JuryBuilder(int members, String type) {
        this.jury = new Jury(members, type);
    }

    public JuryBuilder() {
        this(reference, "type" + reference);
    }

    public JuryBuilder members(int members) {
        this.jury.setMembers(members);
        return this;
    }

    public JuryBuilder veredict(String veredict) {
        this.jury.setVeredict(veredict);
        return this;
    }

    public JuryBuilder type(String type) {
        this.jury.setType(type);
        return this;
    }

    public JuryBuilder byDefault() {
        JuryBuilder juryBuilder = new JuryBuilder();
        return juryBuilder.members(reference).veredict("veredict" + reference).type("type" + reference);
    }

    public Jury build() {
        reference++;
        return this.jury;
    }
}

