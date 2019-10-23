package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarContestBusinessController;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.JuryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(GuitarContestResource.GUITARCONTEST)
public class GuitarContestResource {
    public static final String GUITARCONTEST = "/guitar-contest";
    static final String ID_ID = "/{id}";
    static final String JURY = "/jury";
    static final String VEREDICT = "/veredict";

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

    @GetMapping(value = ID_ID + JURY + VEREDICT)
    public JuryDto readVeredict(@PathVariable String id) {
        return this.guitarContestBusinessController.processVeredict(id);
    }
}
