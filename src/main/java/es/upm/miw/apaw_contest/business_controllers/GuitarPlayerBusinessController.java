package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.GuitarPlayerDao;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
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
}
