package es.upm.miw.apaw_contest.business_controllers;

import es.upm.miw.apaw_contest.daos.GuitarContestDao;
import es.upm.miw.apaw_contest.daos.GuitarPlayerDao;
import es.upm.miw.apaw_contest.documents.GuitarContest;
import es.upm.miw.apaw_contest.documents.GuitarPlayer;
import es.upm.miw.apaw_contest.dtos.GuitarContestBasicDto;
import es.upm.miw.apaw_contest.dtos.GuitarContestCreationDto;
import es.upm.miw.apaw_contest.dtos.GuitarPlayerCreationDto;
import es.upm.miw.apaw_contest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GuitarContestBusinessController {

    private GuitarContestDao guitarContestDao;

    private GuitarPlayerDao guitarPlayerDao;

    @Autowired
    public GuitarContestBusinessController(GuitarContestDao guitarContestDao, GuitarPlayerDao guitarPlayerDao) {
        this.guitarContestDao = guitarContestDao;
        this.guitarPlayerDao = guitarPlayerDao;
    }

    public GuitarContestBasicDto create(GuitarContestCreationDto guitarContestCreationDto) {
        GuitarContest guitarContest = new GuitarContest(guitarContestCreationDto.getDate(), guitarContestCreationDto.getAddress(), guitarContestCreationDto.getCountry());
        this.guitarContestDao.save(guitarContest);
        return new GuitarContestBasicDto(guitarContest);
    }

    public GuitarPlayerCreationDto createWithId(String id, GuitarPlayerCreationDto guitarPlayerCreationDto) {
        GuitarContest guitarContest = findGuitarContestById(id);
        GuitarPlayer guitarPlayer = new GuitarPlayer(guitarPlayerCreationDto.getName(), guitarPlayerCreationDto.getSurname(), guitarPlayerCreationDto.getHasOwnGuitar(), guitarPlayerCreationDto.getPhone());
        this.guitarPlayerDao.save(guitarPlayer);
        guitarContest.getGuitarPlayerList().add(guitarPlayer);
        this.guitarContestDao.save(guitarContest);
        return new GuitarPlayerCreationDto(guitarPlayer);
    }

    private GuitarContest findGuitarContestById(String id) {
        return this.guitarContestDao.findById(id).orElseThrow(() -> new NotFoundException("Guitar Contest id not found: " + id));
    }

    public List<GuitarPlayerCreationDto> findGuitarPlayers(String id) {
        List<GuitarPlayer> guitarPlayerList = findGuitarContestById(id).getGuitarPlayerList();
        return guitarPlayerList.stream().map(GuitarPlayerCreationDto::new).collect(Collectors.toList());
    }

}
