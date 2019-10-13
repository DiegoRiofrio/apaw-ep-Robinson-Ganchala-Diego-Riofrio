package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;

public class GuitarPlayerBasicDto {

    private String id;

    private String name;

    public GuitarPlayerBasicDto(GuitarPlayer guitarPlayer) {
        this.id = guitarPlayer.getId();
        this.name = guitarPlayer.getName();
    }

    public GuitarPlayerBasicDto() {
        // Empty for framework
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

    public void validateName() {
        if (this.name == null || this.name.isEmpty()) {
            throw new BadRequestException("Incomplete, lost name");
        }
    }

    @Override
    public String toString() {
        return "GuitarPlayerBasicDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
