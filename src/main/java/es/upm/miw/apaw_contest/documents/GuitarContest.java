package es.upm.miw.apaw_contest.documents;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document

public class GuitarContest {
    @Id
    private String id;

    private LocalDateTime date;

    private String address;

    private String country;

    private List<GuitarPlayer> guitarPlayerList;

    public GuitarContest(LocalDateTime date, String address, String country) {
        this.date = date;
        this.address = address;
        this.country = country;
        this.guitarPlayerList = new ArrayList<>();
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


    public String getCountry() {
        return country;
    }

    public List<GuitarPlayer> getGuitarPlayerList() {
        return guitarPlayerList;
    }

    @Override
    public String toString() {
        return "GuitarContest{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
