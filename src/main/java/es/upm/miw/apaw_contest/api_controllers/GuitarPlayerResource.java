package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarPlayerBusinessController;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GuitarPlayerResource.GUITARPLAYERS)
public class GuitarPlayerResource {
    public static final String GUITARPLAYERS = "/guitar-players";
    static final String ID_ID = "/{id}";
    static final String SURNAME = "/surname";

    private GuitarPlayerBusinessController guitarPlayerBusinessController;

    @Autowired
    public GuitarPlayerResource(GuitarPlayerBusinessController guitarPlayerBusinessController) {
        this.guitarPlayerBusinessController = guitarPlayerBusinessController;
    }

    @PostMapping
    public GuitarPlayerBasicDto create(@RequestBody GuitarPlayerCreationDto guitarPlayerCreationDto) {
        guitarPlayerCreationDto.validate();
        return this.guitarPlayerBusinessController.create(guitarPlayerCreationDto);
    }

    @GetMapping(value = ID_ID)
    public GuitarPlayerCreationDto get(@PathVariable String id) {
        return this.guitarPlayerBusinessController.getGuitarPlayerById(id);
    }

    @GetMapping(value = ID_ID + SURNAME)
    public GuitarPlayerCreationDto readSurname(@PathVariable String id) {
        return this.guitarPlayerBusinessController.readSurname(id);
    }

    @PutMapping(value = ID_ID + SURNAME)
    public void updateSurname(@PathVariable String id, @RequestBody GuitarPlayerCreationDto guitarPlayerCreationDto) {
        guitarPlayerCreationDto.valdateSurname();
        this.guitarPlayerBusinessController.updateSurname(id, guitarPlayerCreationDto.getSurname());
    }

}
