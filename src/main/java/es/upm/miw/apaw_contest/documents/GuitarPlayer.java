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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getHasOwnerGuitar() {
        return hasOwnerGuitar;
    }

    public void setHasOwnerGuitar(Boolean hasOwnerGuitar) {
        this.hasOwnerGuitar = hasOwnerGuitar;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
