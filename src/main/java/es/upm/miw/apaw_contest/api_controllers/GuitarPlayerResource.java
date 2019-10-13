package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarPlayerBusinessController;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GuitarPlayerResource.GUITARPLAYERS)
public class GuitarPlayerResource {
    public static final String GUITARPLAYERS = "/guitar-players";
    static final String ID_ID = "/{id}";
    static final String NAME = "/name";

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

    @GetMapping(value = ID_ID + NAME)
    public GuitarPlayerBasicDto readName(@PathVariable String id) {
        return this.guitarPlayerBusinessController.readName(id);
    }

    @PutMapping(value = ID_ID + NAME)
    public void updateName(@PathVariable String id, @RequestBody GuitarPlayerBasicDto guitarPlayerBasicDto) {
        guitarPlayerBasicDto.validateName();
        this.guitarPlayerBusinessController.updateName(id, guitarPlayerBasicDto.getName());
    }
}
