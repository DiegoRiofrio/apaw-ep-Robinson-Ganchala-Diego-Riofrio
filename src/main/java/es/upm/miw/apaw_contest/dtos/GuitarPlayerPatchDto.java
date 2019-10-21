package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.exceptions.BadRequestException;

public class GuitarPlayerPatchDto {

    private String path;

    private String newValue;

    public GuitarPlayerPatchDto() {
        // For framework
    }

    public GuitarPlayerPatchDto(String path, String newValue) {
        this.path = path;
        this.newValue = newValue;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void validate() {
        if (this.path == null || this.path.isEmpty()) {
            throw new BadRequestException("Incomplete GuitarPlayerPatchDto");
        }
    }

    @Override
    public String toString() {
        return "GuitarPlayerPatchDto{" +
                "path='" + path + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
