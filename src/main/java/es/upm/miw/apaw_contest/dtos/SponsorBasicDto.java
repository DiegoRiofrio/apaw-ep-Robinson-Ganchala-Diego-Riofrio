package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.Sponsor;

public class SponsorBasicDto {

    private String id;

    private String name;

    public SponsorBasicDto() {
        // For framework
    }

    public SponsorBasicDto(Sponsor sponsor) {
        this.id = sponsor.getId();
        this.name = sponsor.getName();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "SponsorBasicDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
