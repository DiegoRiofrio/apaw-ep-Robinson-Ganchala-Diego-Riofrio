package es.upm.miw.apaw_contest.documents;

public class SponsorLeaf implements SponsorComponent {
    private Sponsor sponsor;

    public SponsorLeaf(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    @Override
    public String id() {
        return this.sponsor.getId();
    }

    @Override
    public String name() {
        return this.sponsor.getName();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(SponsorComponent sponsorComponent) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public void remove(SponsorComponent sponsorComponent) {
        //Nothing to do
    }
}
