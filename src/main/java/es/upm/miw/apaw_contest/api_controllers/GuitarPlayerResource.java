package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarPlayerBusinessController;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerPatchDto;
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
        guitarPlayerCreationDto.validateSurname();
        this.guitarPlayerBusinessController.updateSurname(id, guitarPlayerCreationDto.getSurname());
    }

    @PatchMapping(value = ID_ID)
    public void patch(@PathVariable String id, @RequestBody GuitarPlayerPatchDto guitarPlayerPatchDto) {
        guitarPlayerPatchDto.validate();
        this.guitarPlayerBusinessController.patch(id, guitarPlayerPatchDto);
    }

}
