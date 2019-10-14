package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.exceptions.BadRequestException;

import javax.validation.constraints.NotNull;

public class GuitarPlayerCreationDto {

    private String name;

    private String surname;

    private Boolean hasOwnerGuitar;

    private Integer phone;

    public GuitarPlayerCreationDto() {
        // Empty for framework
    }

    public GuitarPlayerCreationDto(String name, String surname, Boolean hasOwnerGuitar, Integer phone) {
        this.name = name;
        this.surname = surname;
        this.hasOwnerGuitar = hasOwnerGuitar;
        this.phone = phone;
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

    @NotNull
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

    public void validate() {
        if (this.name == null || this.name.isEmpty() || this.surname == null || this.surname.isEmpty() || this.hasOwnerGuitar == null || this.phone == null) {
            throw new BadRequestException("Incomplete GuitarPlayerCreationDto, haOwnerGuitar must be true or false");
        }
    }

    @Override
    public String toString() {
        return "GuitarPlayerCreationDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", hasOwnerGuitar=" + hasOwnerGuitar +
                ", phone=" + phone +
                '}';
    }
}
