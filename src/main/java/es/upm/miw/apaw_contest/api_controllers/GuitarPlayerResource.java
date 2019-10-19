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
}
