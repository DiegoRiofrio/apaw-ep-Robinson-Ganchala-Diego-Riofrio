package es.upm.miw.apaw_contest.dtos;

import es.upm.miw.apaw_contest.exceptions.BadRequestException;

public class JuryDto {

    private String veredict;

    public JuryDto() {
        //Empty for framework
    }

    public JuryDto(String veredict) {
        this.veredict = veredict;
    }

    public String getVeredict() {
        return veredict;
    }

    public void validate() {
        if (this.veredict.isEmpty()) {
            throw new BadRequestException("Incomplete veredict at JuryDto");
        }
    }

    @Override
    public String toString() {
        return "JuryDto{" +
                "veredict='" + veredict + '\'' +
                '}';
    }
}
