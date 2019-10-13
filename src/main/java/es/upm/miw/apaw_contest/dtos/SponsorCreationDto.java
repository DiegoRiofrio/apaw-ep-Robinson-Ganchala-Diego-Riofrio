package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.exceptions.BadRequestException;

public class SponsorCreationDto {

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

    public String getName() {
        return name;
    }

    public double getDonatedAmount() {
        return donatedAmount;
    }

    public String getSponsorType() {
        return sponsorType;
    }

    public void validate() {
        if (this.name == null || this.name.isEmpty() || this.donatedAmount < 0
                || this.sponsorType == null || this.sponsorType.isEmpty()) {
            throw new BadRequestException("Incomplete SponsorCreationDto");
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
