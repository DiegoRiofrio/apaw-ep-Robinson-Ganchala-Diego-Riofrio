package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

import javax.validation.constraints.NotNull;

public class GuitarPlayerCreationDto {

    private String id;

    private String name;

    private String surname;

    private Boolean hasOwnGuitar;

    private Integer phone;

    public GuitarPlayerCreationDto() {
        // Empty for framework
    }

    public GuitarPlayerCreationDto(String name, String surname, Boolean hasOwnGuitar, Integer phone) {
        this.name = name;
        this.surname = surname;
        this.hasOwnGuitar = hasOwnGuitar;
        this.phone = phone;
    }

    public GuitarPlayerCreationDto(GuitarPlayer guitarPlayer) {
        this.id = guitarPlayer.getId();
        this.name = guitarPlayer.getName();
        this.surname = guitarPlayer.getSurname();
        this.hasOwnGuitar = guitarPlayer.getHasOwnGuitar();
        this.phone = guitarPlayer.getPhone();
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

    public String getSurname() {
        return surname;
    }

    @NotNull
    public Boolean getHasOwnGuitar() {

        return hasOwnGuitar;
    }

    public Integer getPhone() {
        return phone;
    }

    public void validate() {
        if (this.name == null || this.name.isEmpty() || this.surname == null || this.surname.isEmpty() || this.hasOwnGuitar == null || this.phone == null) {
            throw new BadRequestException("Incomplete GuitarPlayerCreationDto");
        }
    }

    @Override
    public String toString() {
        return "GuitarPlayerCreationDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", hasOwnGuitar=" + hasOwnGuitar +
                ", phone=" + phone +
                '}';
    }
}
