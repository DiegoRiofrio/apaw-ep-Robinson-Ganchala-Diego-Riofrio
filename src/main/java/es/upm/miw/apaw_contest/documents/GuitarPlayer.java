package es.upm.miw.apaw_contest.documents;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GuitarPlayer {
    @Id
    private String id;

    private String name;

    private String surname;

    private Boolean hasOwnGuitar;

    private Integer phone;

    public GuitarPlayer(String name, String surname, Boolean hasOwnGuitar, Integer phone) {
        this.name = name;
        this.surname = surname;
        this.hasOwnGuitar = hasOwnGuitar;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getHasOwnGuitar() {
        return hasOwnGuitar;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setHasOwnGuitar(Boolean hasOwnGuitar) {
        this.hasOwnGuitar = hasOwnGuitar;
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
                ", hasOwnGuitar=" + hasOwnGuitar +
                ", phone=" + phone +
                '}';
    }


}
