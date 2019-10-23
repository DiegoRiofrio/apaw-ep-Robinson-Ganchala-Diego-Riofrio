package es.upm.miw.apaw_contest.dtos;


import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.documents.Jury;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

import java.time.LocalDateTime;

public class GuitarContestCreationDto {

    private String id;

    private LocalDateTime date;

    private String address;

    private String country;

    private Jury jury;

    public GuitarContestCreationDto() {
        // For framework
    }

    public GuitarContestCreationDto(LocalDateTime date, String address, String country, Jury jury) {
        this.date = date;
        this.address = address;
        this.country = country;
        this.jury = jury;
    }

    public GuitarContestCreationDto(GuitarContest guitarContest) {
        this.id = guitarContest.getId();
        this.date = guitarContest.getDate();
        this.address = guitarContest.getAddress();
        this.country = guitarContest.getCountry();
        this.jury = guitarContest.getJury();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }


    public String getCountry() {
        return country;
    }

    public Jury getJury() {
        return jury;
    }

    public void validate() {
        if (this.date == null || this.address == null || this.address.isEmpty() || this.country == null || this.country.isEmpty()) {
            throw new BadRequestException("Incomplete GuitarContestCreationDto");
        }
    }

    @Override
    public String toString() {
        return "GuitarContestCreationDto{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", jury=" + jury +
                '}';
    }
}
