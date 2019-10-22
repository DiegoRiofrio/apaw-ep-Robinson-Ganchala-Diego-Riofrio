package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.GuitarPlayerDao;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerPatchDto;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;
import es.upm.miw.apaw_contest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GuitarPlayerBusinessController {

    private GuitarPlayerDao guitarPlayerDao;

    @Autowired
    public GuitarPlayerBusinessController(GuitarPlayerDao guitarPlayerDao) {
        this.guitarPlayerDao = guitarPlayerDao;
    }

    public GuitarPlayerBasicDto create(GuitarPlayerCreationDto guitarPlayerCreationDto) {
        GuitarPlayer guitarPlayer = new GuitarPlayer(guitarPlayerCreationDto.getName(), guitarPlayerCreationDto.getSurname(), guitarPlayerCreationDto.getHasOwnGuitar(), guitarPlayerCreationDto.getPhone());
        this.guitarPlayerDao.save(guitarPlayer);
        return new GuitarPlayerBasicDto(guitarPlayer);
    }

    public GuitarPlayerCreationDto getGuitarPlayerById(String id) {
        return new GuitarPlayerCreationDto(
                this.guitarPlayerDao.findById(id).orElseThrow(() -> new NotFoundException("GuitarPlayer id not found: " + id))
        );
    }

    private GuitarPlayer findGuitarPlayerById(String id) {
        return this.guitarPlayerDao.findById(id).orElseThrow(() -> new NotFoundException("GuitarPlayer id not found: " + id));
    }

    public GuitarPlayerCreationDto readSurname(String id){
        return new GuitarPlayerCreationDto(this.findGuitarPlayerById(id));
    }

    public void updateSurname(String id, String surname){
        GuitarPlayer guitarPlayer = this.findGuitarPlayerById(id);
        guitarPlayer.setSurname(surname);
        this.guitarPlayerDao.save(guitarPlayer);
    }

    public void patch(String id, GuitarPlayerPatchDto guitarPlayerpatchDto) {
        GuitarPlayer guitarPlayer = findGuitarPlayerById(id);
        switch (guitarPlayerpatchDto.getPath()) {
            case "name":
                guitarPlayer.setName(guitarPlayerpatchDto.getNewValue());
                break;
            case "surname":
                guitarPlayer.setSurname(guitarPlayerpatchDto.getNewValue());
                break;
            case "hasOwnGuitar":
                guitarPlayer.setHasOwnGuitar(Boolean.parseBoolean(guitarPlayerpatchDto.getNewValue()));
                break;
            case "phone":
                guitarPlayer.setPhone(Integer.parseInt(guitarPlayerpatchDto.getNewValue()));
                break;
            default:
                throw new BadRequestException("GuitarPlayerPatchDto is invalid");
        }
        this.guitarPlayerDao.save(guitarPlayer);
    }
}
