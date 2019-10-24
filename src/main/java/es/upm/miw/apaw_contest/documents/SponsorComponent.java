package es.upm.miw.apaw_contest.documents;

public interface SponsorComponent {
    String id();

    String name();

    boolean isComposite();

    void add(SponsorComponent sponsorComponent);

    void remove(SponsorComponent sponsorComponent);

}
