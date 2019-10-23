package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.GuitarContestDao;
import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.JuryDto;
import es.upm.miw.apaw_contest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GuitarContestBusinessController {

    private GuitarContestDao guitarContestDao;

    @Autowired
    public GuitarContestBusinessController(GuitarContestDao guitarContestDao) {
        this.guitarContestDao = guitarContestDao;
    }

    public GuitarContestBasicDto create(GuitarContestCreationDto guitarContestCreationDto) {
        GuitarContest guitarContest = new GuitarContest(guitarContestCreationDto.getDate(), guitarContestCreationDto.getAddress(), guitarContestCreationDto.getCountry(), guitarContestCreationDto.getJury());
        this.guitarContestDao.save(guitarContest);
        return new GuitarContestBasicDto(guitarContest);
    }

    private GuitarContest findGuitarContestByIdAssured(String id) {
        return this.guitarContestDao.findById(id).orElseThrow(() -> new NotFoundException("Guitar Contest id: " + id));
    }

    public JuryDto processVeredict(String id) {
        GuitarContest guitarContest = this.findGuitarContestByIdAssured(id);
        return new JuryDto(this.veredict(guitarContest));

    }

    private String veredict(GuitarContest guitarContest) {
        return guitarContest.getJury().getVeredict();
    }

}
