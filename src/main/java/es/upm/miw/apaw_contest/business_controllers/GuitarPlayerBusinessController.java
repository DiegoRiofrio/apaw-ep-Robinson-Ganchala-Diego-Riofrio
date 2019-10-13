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
        GuitarPlayer guitarPlayer = new GuitarPlayer(guitarPlayerCreationDto.getName(), guitarPlayerCreationDto.getSurname(), guitarPlayerCreationDto.getHasOwnerGuitar(), guitarPlayerCreationDto.getPhone());
        this.guitarPlayerDao.save(guitarPlayer);
        return new GuitarPlayerBasicDto(guitarPlayer);
    }

    public GuitarPlayerBasicDto readName(String id) {
        return new GuitarPlayerBasicDto(this.findGuitarPlayerByIdAssured(id));
    }

    private GuitarPlayer findGuitarPlayerByIdAssured(String id) {
        return this.guitarPlayerDao.findById(id).orElseThrow(() -> new NotFoundException("Guitar Player id: " + id));
    }

    public void updateName(String id, String name) {
        GuitarPlayer guitarPlayer = this.findGuitarPlayerByIdAssured(id);
        guitarPlayer.setName(name);
        this.guitarPlayerDao.save(guitarPlayer);
    }
}
