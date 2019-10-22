package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarContestBusinessController;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(GuitarContestResource.GUITAR_CONTESTS)
public class GuitarContestResource {
    public static final String GUITAR_CONTESTS = "/guitar-contests";
    static final String ID_ID = "/{id}";
    static final String GUITAR_PLAYERS = "/guitar-players";

    private GuitarContestBusinessController guitarContestBusinessController;

    @Autowired
    public GuitarContestResource(GuitarContestBusinessController guitarContestBusinessController) {
        this.guitarContestBusinessController = guitarContestBusinessController;
    }

    @PostMapping
    public GuitarContestBasicDto create(@RequestBody GuitarContestCreationDto guitarContestCreationDto) {
        guitarContestCreationDto.validate();
        return this.guitarContestBusinessController.create(guitarContestCreationDto);
    }

    @PostMapping(value = ID_ID + GUITAR_PLAYERS)
    public GuitarPlayerCreationDto createContestWithGuitarPlayers(@PathVariable String id, @RequestBody GuitarPlayerCreationDto guitarPlayerCreationDto) {
        guitarPlayerCreationDto.validate();
        GuitarPlayerCreationDto guitarPlayerCreationDto1 = this.guitarContestBusinessController.createWithId(id, guitarPlayerCreationDto);
        return guitarPlayerCreationDto1;
    }

    @GetMapping(value = ID_ID + GUITAR_PLAYERS)
    public List<GuitarPlayerCreationDto> readAllGuitarPlayers(@PathVariable String id) {
        return this.guitarContestBusinessController.findGuitarPlayers(id);
    }
}
