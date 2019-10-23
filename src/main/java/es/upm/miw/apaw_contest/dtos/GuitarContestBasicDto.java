package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.GuitarContest;

import java.util.List;

public class GuitarContestBasicDto {

    private String id;

    private String address;

    private String country;

    private List<String> guitarPlayerIds;

    public GuitarContestBasicDto() {
        //For framework
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

    public List<String> getGuitarPlayerIds() {
        return guitarPlayerIds;
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
