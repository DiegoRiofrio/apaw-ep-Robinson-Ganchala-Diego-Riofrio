package es.upm.miw.apaw_contest.documents;

import java.util.ArrayList;
import java.util.List;

public class SponsorComposite implements SponsorComponent {

    private String id;

    private String name;

    private List<SponsorComponent> sponsorComponentList;

    public SponsorComposite(String id, String name) {
        this.id = id;
        this.name = name;
        this.sponsorComponentList = new ArrayList<>();
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(SponsorComponent sponsorComponent) {
        sponsorComponentList.add(sponsorComponent);
    }

    @Override
    public void remove(SponsorComponent sponsorComponent) {
        sponsorComponentList.remove(sponsorComponent);
    }
}
