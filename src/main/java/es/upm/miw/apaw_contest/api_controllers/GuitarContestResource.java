package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.GuitarContestBusinessController;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GuitarContestResource.GUITARCONTEST)
public class GuitarContestResource {
    public static final String GUITARCONTEST = "/guitar-contest";

    private GuitarContestBusinessController guitarContestBusinessController;

    @Autowired
    public GuitarContestResource(GuitarContestBusinessController guitarContestBusinessController) {
        this.guitarContestBusinessController = guitarContestBusinessController;
    }

    @PostMapping
    public GuitarContestBasicDto create(@RequestBody GuitarContestCreationDto guitarContestCreationDto){
        guitarContestCreationDto.validate();
        return this.guitarContestBusinessController.create(guitarContestCreationDto);
    }
}
