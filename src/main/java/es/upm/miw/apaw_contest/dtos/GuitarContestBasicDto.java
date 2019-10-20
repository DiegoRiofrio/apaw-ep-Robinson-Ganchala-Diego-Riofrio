package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.GuitarContest;

public class GuitarContestBasicDto {

    private String id;

    private String address;

    private String country;

    public GuitarContestBasicDto() {
        //For framework
    }

    public GuitarContestBasicDto(String id, String address, String country) {
        this.id = id;
        this.address = address;
        this.country = country;
    }

    public GuitarContestBasicDto(GuitarContest guitarContest) {
        this.id = guitarContest.getId();
        this.address = guitarContest.getAddress();
        this.country = guitarContest.getCountry();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "GuitarContestBasicDto{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
