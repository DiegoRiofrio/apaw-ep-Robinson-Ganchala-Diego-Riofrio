package es.upm.miw.apaw_contest.api_controllers;

import es.upm.miw.apaw_contest.business_controllers.SponsorBusinessController;
import es.upm.miw.apaw_contest.dtos.SponsorBasicDto;
import es.upm.miw.apaw_contest.dtos.SponsorCreationDto;
import com.google.common.base.Strings;
import es.upm.miw.apaw_contest.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.util.List;

@RestController
@RequestMapping(SponsorResource.SPONSORS)
public class SponsorResource {

    public static final String SPONSORS = "/sponsors";
    static final String ID_ID = "/{id}";
    static final String TYPE = "/sponsor-type";

    private SponsorBusinessController sponsorBusinessController;

    private EmitterProcessor<String> emitterProcessor;

    @Autowired
    public SponsorResource(SponsorBusinessController sponsorBusinessController) {
        this.sponsorBusinessController = sponsorBusinessController;
        this.emitterProcessor = EmitterProcessor.create();
    }

    public Flux<String> publisher() {
        return this.emitterProcessor;
    }

    @PostMapping
    public SponsorBasicDto create(@RequestBody SponsorCreationDto sponsorCreationDto) {
        sponsorCreationDto.validate();
        SponsorBasicDto sponsorBasicDtoSaved = this.sponsorBusinessController.create(sponsorCreationDto);
        this.emitterProcessor.onNext(sponsorBasicDtoSaved.getName());
        return sponsorBasicDtoSaved;
    }

    @GetMapping(value = ID_ID)
    public SponsorCreationDto get(@PathVariable String id) {
        return this.sponsorBusinessController.getSponsorById(id);
    }

    @GetMapping(value = ID_ID + TYPE)
    public SponsorCreationDto readType(@PathVariable String id) {
        return this.sponsorBusinessController.readType(id);
    }

    @PutMapping(value = ID_ID + TYPE)
    public void updateType(@PathVariable String id, @RequestBody SponsorCreationDto sponsorCreationDto) {
        sponsorCreationDto.validateType();
        this.sponsorBusinessController.updateType(id, sponsorCreationDto.getSponsorType());
    }

    @DeleteMapping(value = ID_ID)
    public void delete(@PathVariable String id) {
        this.sponsorBusinessController.delete(id);
    }

    @PatchMapping()
    public void patch(@RequestBody List<SponsorBasicDto> sponsorBasicDtoList) {
        if (sponsorBasicDtoList.stream().anyMatch(value -> Strings.isNullOrEmpty(value.getId()) || Strings.isNullOrEmpty(value.getName())) || sponsorBasicDtoList.isEmpty()) {
            throw new BadRequestException("Parameters not found");
        }
        this.sponsorBusinessController.patch(sponsorBasicDtoList);
    }
}
