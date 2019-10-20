package es.upm.miw.apaw_contest.dtos;


import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

import java.time.LocalDateTime;

public class GuitarContestCreationDto {

    private String id;

    private LocalDateTime date;

    private  String address;

    private  String country;

    public GuitarContestCreationDto() {
        // For framework
    }

    public GuitarContestCreationDto(LocalDateTime date, String address, String country) {
        this.date = date;
        this.address = address;
        this.country = country;
    }
//    public GuitarContestCreationDto(GuitarContest guitarContest) {
//        this.id = guitarContest.getId();
//        this.date = guitarContest.getDate();
//        this.address = guitarContest.getAddress();
//        this.country = guitarContest.getCountry();
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void validate(){
        if (this.date == null || this.address == null || this.address.isEmpty() || this.country == null || this.country.isEmpty())
        {
            throw new BadRequestException("Incomplete GuitarContestCreationDto");
        }
    }


    @Override
    public String toString() {
        return "GuitarContestCreationDto{" +
                "date=" + date +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
