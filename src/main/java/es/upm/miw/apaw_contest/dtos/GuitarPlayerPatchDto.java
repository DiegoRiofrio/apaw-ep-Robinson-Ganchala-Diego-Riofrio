package es.upm.miw.apaw_contest.dtos;

public class GuitarPlayerPatchDto {

    private String path;

    private String newValue;

    public GuitarPlayerPatchDto() {
        // Empty for framework
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

    @Override
    public String toString() {
        return "GuitarPlayerPatchDto{" +
                "path='" + path + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}
