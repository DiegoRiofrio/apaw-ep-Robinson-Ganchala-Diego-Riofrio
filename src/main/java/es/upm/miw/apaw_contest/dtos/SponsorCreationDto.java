package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.Sponsor;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

public class SponsorCreationDto {

    private String id;

    private String name;

    private double donatedAmount;

    private String sponsorType;

    public SponsorCreationDto() {
        // For framework
    }

    public SponsorCreationDto(String name, double donatedAmount, String sponsorType) {
        this.name = name;
        this.donatedAmount = donatedAmount;
        this.sponsorType = sponsorType;
    }

    public SponsorCreationDto(Sponsor sponsor) {
        this.id = sponsor.getId();
        this.name = sponsor.getName();
        this.sponsorType = sponsor.getSponsorType();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDonatedAmount() {
        return donatedAmount;
    }

    public String getSponsorType() {
        return sponsorType;
    }

    public void setSponsorType(String sponsorType) {
        this.sponsorType = sponsorType;
    }

    public void validate() {
        if (this.name == null || this.name.isEmpty() || this.donatedAmount < 0
                || this.sponsorType == null || this.sponsorType.isEmpty()) {
            throw new BadRequestException("Incomplete SponsorCreationDto");
        }
    }

    public void validateType() {
        if (this.sponsorType == null || this.sponsorType.isEmpty()) {
            throw new BadRequestException("Incomplete, lost sponsorType");
        }
    }

    @Override
    public String toString() {
        return "SponsorCreationDto{" +
                "name='" + name + '\'' +
                ", donatedAmount='" + donatedAmount + '\'' +
                ", sponsorType='" + sponsorType + '\'' +
                '}';
    }
}
