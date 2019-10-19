package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.documents.GuitarPlayer;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GuitarPlayerBasicDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
