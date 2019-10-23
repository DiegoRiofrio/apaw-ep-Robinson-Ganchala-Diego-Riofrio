package es.upm.miw.apaw_contest.documents;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document

public class GuitarContest {
    @Id
    private String id;

    private LocalDateTime date;

    private String address;

    private String country;

    private Jury jury;

    public GuitarContest(LocalDateTime date, String address, String country, Jury jury) {
        this.date = date;
        this.address = address;
        this.country = country;
        this.jury = jury;
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

    public LocalDateTime getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public Jury getJury() {
        return jury;
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
