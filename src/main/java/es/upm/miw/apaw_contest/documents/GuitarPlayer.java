package es.upm.miw.apaw_contest.documents;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GuitarPlayer {
    @Id
    private String id;

    private String name;

    private String surname;

    private Boolean hasOwnerGuitar;

    private Integer phone;

    public GuitarPlayer(String name, String surname, Boolean hasOwnerGuitar, Integer phone) {
        this.name = name;
        this.surname = surname;
        this.hasOwnerGuitar = hasOwnerGuitar;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GuitarPlayer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", hasOwnerGuitar=" + hasOwnerGuitar +
                ", phone=" + phone +
                '}';
    }
}
