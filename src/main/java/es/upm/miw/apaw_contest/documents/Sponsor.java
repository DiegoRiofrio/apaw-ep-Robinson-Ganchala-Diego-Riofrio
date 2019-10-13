package es.upm.miw.apaw_contest.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sponsor {

    @Id
    private String id;

    private String name;

    private double donatedAmount;

    private String sponsorType;

    public Sponsor(String name, double donatedAmount, String sponsorType) {
        this.name = name;
        this.donatedAmount = donatedAmount;
        this.sponsorType = sponsorType;
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

    public void setDonatedAmount(double donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public String getSponsorType() {
        return sponsorType;
    }
}